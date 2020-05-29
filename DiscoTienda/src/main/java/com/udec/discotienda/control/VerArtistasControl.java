/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import com.udec.discotienda.model.ArtistaModel;
import com.udec.discotienda.pojo.Artista;
import com.udec.discotienda.pojo.Cancion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;


/**
 * Clase ver artistas
 * @author ASUS
 */
@Named(value = "verArtistasControl")
@SessionScoped
public class VerArtistasControl implements Serializable{
    /*** lista artistas*/
    private List<Artista> artistas;
    /*** lista filtro*/
    private List<Artista> artistasFiltrados;
    /*** consulta artistas*/
    private ArtistaModel conArtistas;
   /*** variable id*/
    private int id;
    /*** variable fecha*/
    private Date fecha;
    /*** variables nombre, apellido, nacionalidad, fecha nacimiento*/
    private String nombre, apellido, nacionalidad,fechanaci;
    /*** variable seleccionado*/
    private boolean seleccion;
    /*** lista eliminados*/
    private List<Artista> artistasEliminados;
    
    /**
     * constructor de la clase
     */
    public VerArtistasControl() {
        artistas=new ArrayList<>();
        conArtistas= new ArtistaModel();
        artistasEliminados= new ArrayList<>();
    }
    /**
     * postconstruct clase
     */
    @PostConstruct
    public void init(){
        llenarListaArtistas();
    
    }
    /**
     * metodo llenar lista artistas
     */
     public void llenarListaArtistas(){ 
         artistas=conArtistas.consultaArtistas();
     }
     /**
      * metodo cambiar formato de fecha
      * @param fecha
      * @return string fecha
      */
      public String getMyFormattedDate(Date fecha) {
        return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }
      /**
       * metodo editar
       * @param event 
       */
        public void onRowEdit(RowEditEvent event) {
        id=((Artista)event.getObject()).getId();
        nombre=((Artista)event.getObject()).getNombre();
        apellido=((Artista)event.getObject()).getApellido();
        fecha=((Artista)event.getObject()).getFechanacimiento();
        nacionalidad=((Artista)event.getObject()).getNacionalidad();
        fechanaci=getMyFormattedDate(fecha);
        
        conArtistas.modificar(id, nombre, apellido, fechanaci, nacionalidad);
        llenarListaArtistas();
        FacesMessage msg = new FacesMessage("Artista Editado con exito"," ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
       
     /**
      * metodo cancelar edicion
      * @param event 
      */
    public void onRowCancel(RowEditEvent event) {
        try {
             String nameArtis=((Artista)event.getObject()).getNombre()+" "+((Artista)event.getObject()).getApellido();
             FacesMessage msg = new FacesMessage("Edición Cancelada: ", nameArtis);
             FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            Logger.getLogger(VerArtistasControl.class.getName()).log(Level.SEVERE, 
                "error onRowCancel:"+VerArtistasControl.class.getName()+" "+e,e);
        }
       
    }
    /**
     * Metodo eliminar
     */
     public void eliminar(){
         
        for(Artista artis: artistas){
            if(artis.isSeleccion()){
                artistasEliminados.add(artis);
            }
        }
        if(!artistasEliminados.isEmpty()){
            for (Artista artista: artistasEliminados) {
                conArtistas.eliminar(artista.getId());  
            }
            artistas.removeAll(artistasEliminados);
        }   
        FacesMessage msg = new FacesMessage("Eliminados con exito"," ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
           
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public List<Artista> getArtistasEliminados() {
        return artistasEliminados;
    }

    public void setArtistasEliminados(List<Artista> artistasEliminados) {
        this.artistasEliminados = artistasEliminados;
    }

    public String getFechanaci() {
        return fechanaci;
    }

    public void setFechanaci(String fechanaci) {
        this.fechanaci = fechanaci;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public ArtistaModel getConArtistas() {
        return conArtistas;
    }

    public void setConArtistas(ArtistaModel conArtistas) {
        this.conArtistas = conArtistas;
    }

    public List<Artista> getArtistasFiltrados() {
        return artistasFiltrados;
    }

    public void setArtistasFiltrados(List<Artista> artistasFiltrados) {
        this.artistasFiltrados = artistasFiltrados;
    }
     

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
    
}
