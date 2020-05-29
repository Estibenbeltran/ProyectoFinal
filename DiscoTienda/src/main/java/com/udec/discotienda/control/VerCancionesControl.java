/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import com.udec.discotienda.model.CancionModel;
import com.udec.discotienda.model.DiscoModel;
import com.udec.discotienda.pojo.Cancion;
import com.udec.discotienda.pojo.Disco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 * Clase para administrar ver canciones
 * @author dairo
 */
@Named(value = "verCancionesControl")
@RequestScoped
public class VerCancionesControl implements Serializable {
    /*** lista de canciones*/
    private List<Cancion> canciones;
    /*** lista filtro*/
    private List<Cancion> cancionFiltro;
    /*** modelo cancionmodel*/
    private CancionModel conCanciones;
    /*** lista discos*/
    private List<Disco> discos;
    /*** lista nombre discos*/
    private List<String> nomDiscos;
    /*** modelo disco*/
    private DiscoModel conDisco;
    /*** variables id,id de disco*/
    private int id,idDisco;
    /*** variables nombre,nombre,duracion, disco*/
    private String nombre,duracion,disco;
    /*** variable precio*/
    private float precio;
    /*** variable seleccionado*/
    private boolean seleccion;
    /*** lista canciones eliminadas*/
    private List<Cancion> cancionesEliminadas; 

    /**
     * constructor de la clase
     */
    public VerCancionesControl() {
         canciones=new ArrayList<>();
         nomDiscos=new ArrayList<>();
         conDisco=new DiscoModel();
         cancionesEliminadas=new ArrayList<>();
    }
    /**
     * postconstruct clase
     */
     @PostConstruct
    public void init(){
        llenarListaCanciones();
        llenarDiscos();
    }
    /**
     * metodo llenar lista de canciones
     */
     public void llenarListaCanciones(){
         conCanciones= new CancionModel();
         canciones=conCanciones.consultaCanciones();
     }
     /**
      * metodo llenar lista discos
      */
     public void llenarDiscos(){
         discos=conDisco.consultaDiscos();
            for (Disco dis : discos) {
                nomDiscos.add(dis.getNombre());
         }
     }
     /**
      * metodo filtro precio
      * @param value
      * @param filter
      * @param locale
      * @return 
      */
     public boolean filterNum(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
    
    /**
     * metodo editar
     * @param event 
     */
    public void onRowEdit(RowEditEvent event) {
        id=((Cancion)event.getObject()).getIdCancion();
        nombre=((Cancion)event.getObject()).getNombre();
        duracion=((Cancion)event.getObject()).getDuracion();
        disco=((Cancion)event.getObject()).getNombreDisco();
        precio=((Cancion)event.getObject()).getPrecio();
        idDisco=traerIdDisco(disco);
        
        conCanciones.modificar(id, nombre, duracion, idDisco, precio);
       
        FacesMessage msg = new FacesMessage("Cancion editada con exito",((Cancion)event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
     * metodo traer id disco
     * @param nombredis
     * @return id disco
     */
    public int traerIdDisco(String nombredis){
           for (Disco vardisco : discos) {
               if(vardisco.getNombre().equals(nombredis)){ 
                   return vardisco.getId();
               }
        }
    return 0;
    }
     /**
      * metodo cancelar edicion
      * @param event 
      */
    public void onRowCancel(RowEditEvent event) {
        try {
             FacesMessage msg = new FacesMessage("Edición Cancelada: ", ((Cancion)event.getObject()).getNombre());
             FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            Logger.getLogger(VerArtistasControl.class.getName()).log(Level.SEVERE, 
                "error onRowCancel:"+VerArtistasControl.class.getName()+" "+e,e);
        }
       
    }/**
     * metodo eliminar
     */
    public void eliminar(){
             for(Cancion can: canciones){
            if(can.isSeleccion()){
                cancionesEliminadas.add(can);
            }
        }
        if(!cancionesEliminadas.isEmpty()){
            for (Cancion c : cancionesEliminadas) {
                conCanciones.eliminar(c.getIdCancion());  
            }
            canciones.removeAll(cancionesEliminadas);
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

    public List<Cancion> getCancionesEliminadas() {
        return cancionesEliminadas;
    }

    public void setCancionesEliminadas(List<Cancion> cancionesEliminadas) {
        this.cancionesEliminadas = cancionesEliminadas;
    }

    public int getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(int idDisco) {
        this.idDisco = idDisco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public CancionModel getConCanciones() {
        return conCanciones;
    }

    public void setConCanciones(CancionModel conCanciones) {
        this.conCanciones = conCanciones;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }

    public List<String> getNomDiscos() {
        return nomDiscos;
    }

    public void setNomDiscos(List<String> nomDiscos) {
        this.nomDiscos = nomDiscos;
    }

    public DiscoModel getConDisco() {
        return conDisco;
    }

    public void setConDisco(DiscoModel conDisco) {
        this.conDisco = conDisco;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<Cancion> getCancionFiltro() {
        return cancionFiltro;
    }

    public void setCancionFiltro(List<Cancion> cancionFiltro) {
        this.cancionFiltro = cancionFiltro;
    }
    
    
    
}
