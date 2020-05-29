/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;
import com.udec.discotienda.model.ArtistaModel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;


/**
 * Clase para administrar crear artista
 * @author dairo
 */
@Named(value = "crearArtistaControl")
@RequestScoped
public class CrearArtistaControl implements Serializable{
    /*** variable nombre*/
    private String nombre;
    /*** variable apellido*/
    private String apellido;
    /*** variable fecha nacimiento*/
    @NotNull
    private Date fechanacimiento;
    /*** variable nacionalidad*/
    private String nacionalidad;
    /*** Modelo Artista*/
    ArtistaModel artista=new ArtistaModel();
    
    /*** Constructor de la clase*/
    public CrearArtistaControl() {
        fechanacimiento = new Date();
    }
    /*** Cambiar formato fecha*/
     public String getMyFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(fechanacimiento);
    }
     /*** metodo crear artista*/
    public void crearArtista(){
        try {
            String fecha=getMyFormattedDate();
             artista.registrar(nombre, apellido, fecha,nacionalidad);  
        } catch (Exception e) {
            throw e;
        }
        FacesMessage message = new FacesMessage("Registro exitoso",nombre+" "+apellido);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public ArtistaModel getArtista() {
        return artista;
    }

    public void setArtista(ArtistaModel artista) {
        this.artista = artista;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    
    
}
