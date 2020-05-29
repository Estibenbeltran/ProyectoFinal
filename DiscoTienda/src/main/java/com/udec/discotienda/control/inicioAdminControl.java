/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 * Clase controlar template del admin
 * @author ASUS
 */
@Named(value = "inicioAdminControl")
@SessionScoped
public class inicioAdminControl implements Serializable {
    /**
     * variable pagina seleccionada
     */
    private String seleccion="PaginasAdmin/bienvenidoAdmin.xhtml";
    
    /**
     * constructor de la clase
     */
    public inicioAdminControl() {
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
    /**
     * metodo cambiar seleccion de pagina artista
     */
    public void cartista(){
        seleccion="PaginasAdmin/crearArtista.xhtml";
    }
    /**
     * metodo cambiar seleccion de pagina disco
     */
    public void cdisco(){
        seleccion="PaginasAdmin/crearDisco.xhtml";
    }
    /**
     * metodo cambiar seleccion de pagina cancion
     */
    public void ccancion(){
        seleccion="PaginasAdmin/crearCancion.xhtml";
    }
    /**
     * metodo cambiar seleccion de pagina ver artistas
     */
    public void vartistas(){
        seleccion="PaginasAdmin/verArtista.xhtml";
    }
    /**
     * metodo cambiar seleccion de pagina ver discos
     */
    public void vdiscos(){
        seleccion="PaginasAdmin/verDiscos.xhtml";
    }
    /**
     * metodo cambiar seleccion de pagina ver canciones
     */
    public void vcanciones(){
        seleccion="PaginasAdmin/verCancion.xhtml";
    }
    /**
     * metodo cerrar sesion
     * @return string redireccion
     */
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../login.xhtml";
    }
}
