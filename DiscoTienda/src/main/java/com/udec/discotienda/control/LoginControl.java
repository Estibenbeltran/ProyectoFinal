/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import com.udec.discotienda.model.Sesion;
import com.udec.discotienda.model.UsuarioModel;
import com.udec.discotienda.pojo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase para controlar el login
 * @author ASUS
 */
@Named(value = "loginControl")
@SessionScoped
public class LoginControl implements Serializable{
    
    /*** variable usuario*/
    private String usuario;
    /*** variable contraseña*/
    private String contrasena;
    /*** modelo usuario*/
    private Usuario usuarioLogin;
    
    /*** variable nombre*/
    private String nombre;
    /*** variable cedula*/
    private int cedula;
    /*** variable usuario nuevo*/
    private String usernuevo;
    /*** variable clave*/
    private String clave;
    /*** modelo usuariomodel*/
    private UsuarioModel modUsuario;
    /*** variable localidad*/
    private String localidad;
    /*** mapa para idioma*/
    private Map<String, Object> listaPaises;
    
    /**
     * postconstruct clase
     */
   @PostConstruct
   public void init(){
        listaPaises = new LinkedHashMap<>();        
        Locale espanol = new Locale("ES");
        listaPaises.put("Español", espanol);
        listaPaises.put("English", Locale.ENGLISH);
   }
        /**
         * metodo localidad cambiada
         * @param e 
         */
    public void localidadChanged(ValueChangeEvent e) {
        String newLanguage = e.getNewValue().toString();
        
        for (Map.Entry<String, Object> entrySet : listaPaises.entrySet()) {
            if(entrySet.getValue().toString().equals(newLanguage)) {
                FacesContext.getCurrentInstance().getViewRoot()
                        .setLocale((Locale)entrySet.getValue());
            }
        }
    }
    /**
     * metodo sobrecargado localidad cambiada para templates
     */
    public void localidadChanged() {
        String newLanguage = localidad; 
        for (Map.Entry<String, Object> entrySet : listaPaises.entrySet()) {
            if(entrySet.getValue().toString().equals(newLanguage)) {
                FacesContext.getCurrentInstance().getViewRoot()
                        .setLocale((Locale)entrySet.getValue());
            }
        }
    }
    
    /**
     * metodo iniciar sesion
     * @return String redireccion
     */
    public String iniciarSesion() {
        String redireccion = null;
        Sesion sesion = new Sesion();
        usuarioLogin = sesion.validarSesion(usuario, contrasena);
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuarioLogin != null) {
            context.getExternalContext().getSessionMap().put("usuario", usuarioLogin);
            if (usuarioLogin.getIdrol()==1) {
                redireccion = "/inicioAdmin?faces-redirect=true";
            } else if(usuarioLogin.getIdrol()==2) {
                redireccion = "/inicioCliente?faces-redirect=true";
            }
            
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Acceso denegado", "Usuario y/o contraseña incorrecta"));
        }
        return redireccion;
    }
    
    
    /**
     * metodo validar permisos
     */
    public void validarPermisoAdmin() {
        FacesContext context = FacesContext.getCurrentInstance();
        localidadChanged();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        usuarioLogin = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if (!req.getRequestURI().contains("/faces/login.xhtml")) {
            if (req.getRequestURI().contains("/inicioAdmin.xhtml")) {
                if (usuarioLogin == null || usuarioLogin.getIdrol()!=1) {
                    try {
                        context.getExternalContext().redirect("login.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    /**
     * metodo registrar usuario nuevo
     */
    public void registrarNuevoUsuario(){
        try {
            modUsuario=new UsuarioModel();
             modUsuario.registrarUsuario(nombre, cedula, usernuevo, clave);
             FacesMessage msg = new FacesMessage("Registro exitoso"," ");
             FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error en el registro","intente de nuevo mas tarde");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
    /**
     * metodo validar permisos de cliente
     */
    public void validarPermisoCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        localidadChanged();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        usuarioLogin = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if (!req.getRequestURI().contains("/faces/login.xhtml")) {
            if (req.getRequestURI().contains("/inicioCliente.xhtml")) {
                if (usuarioLogin == null || usuarioLogin.getIdrol()!=2) {
                    try {
                        context.getExternalContext().redirect("login.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public Map<String, Object> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(Map<String, Object> listaPaises) {
        this.listaPaises = listaPaises;
    }



    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public UsuarioModel getModUsuario() {
        return modUsuario;
    }

    public void setModUsuario(UsuarioModel modUsuario) {
        this.modUsuario = modUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getUsernuevo() {
        return usernuevo;
    }

    public void setUsernuevo(String usernuevo) {
        this.usernuevo = usernuevo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
    
}
