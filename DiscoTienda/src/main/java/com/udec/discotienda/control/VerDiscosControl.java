/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import com.udec.discotienda.model.ArtistaModel;
import com.udec.discotienda.model.DiscoModel;
import com.udec.discotienda.pojo.Artista;
import com.udec.discotienda.pojo.Disco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *Clase para administrar ver discos
 * @author ASUS
 */
@Named(value = "verDiscosControl")
@RequestScoped
public class VerDiscosControl implements Serializable {
    /*** lista discos*/
    private List<Disco> discos;
    /*** lista filtro*/
    private List<Disco> discosFiltrados;
    /*** modelo consulta discos*/
    private DiscoModel conDiscos;
    /*** lista artistas*/
    private List<Artista> artistas;
    /*** lista nombre de artistas*/
    private List<String> nomArtistas;
    /*** modelo consulta artistas*/
    private ArtistaModel conArtistas;
    /*** variables nombre disco, nombre artista*/
    private String nombredisco,nomartista;
    /*** variables id disco, id artista*/
    private int idisco,idartista;
    /*** variable seleccion*/
    private boolean seleccion;
    /*** lista discos eliminados*/
    private List<Disco> discosEliminados;
    
    /**
     * constructor de la clase
     */
    public VerDiscosControl() {
        discos=new ArrayList<>();
        discosEliminados=new ArrayList<>();
        conArtistas=new ArtistaModel();
        nomArtistas=new ArrayList<>();
    }
    /**
     * post construct 
     */
    @PostConstruct
    public void init(){
        llenarArtistas();
        llenarListaDiscos();
    }
    /**
     * metodo llenar lista de discos
     */
     public void llenarListaDiscos(){
         conDiscos= new DiscoModel();
         discos=conDiscos.consultaDiscos();
     }
     /**
      * metodo llenar lista de artistas
      */
     public void llenarArtistas(){
         artistas=conArtistas.consultaArtistas();
            for (Artista art : artistas) {
                nomArtistas.add(art.getNombre()+" "+art.getApellido());
         }
            
     
     }
     /**
      * metodo editar
      * @param event 
      */
     public void onRowEdit(RowEditEvent event) {
        try{
            idisco=((Disco)event.getObject()).getId();
            nombredisco=((Disco)event.getObject()).getNombre();
            nomartista=((Disco)event.getObject()).getNombreArtista();
            for (Artista ar : artistas) {
                if((ar.getNombre()+" "+ar.getApellido()).equals(nomartista)){
                    idartista=ar.getId();
                }
            }
            conDiscos.modificar(idisco, nombredisco, idartista);
            
        FacesMessage msg = new FacesMessage("Disco editado con exito",((Disco)event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        }catch (Exception e) {
            Logger.getLogger(VerArtistasControl.class.getName()).log(Level.SEVERE, 
                "error onRowEdit:"+VerArtistasControl.class.getName()+" "+e,e);
        }
    }
     /**
      * metodo cancelar
      * @param event 
      */
    public void onRowCancel(RowEditEvent event) {
        try {
             FacesMessage msg = new FacesMessage("Edición Cancelada: ", ((Disco)event.getObject()).getNombre());
             FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            Logger.getLogger(VerArtistasControl.class.getName()).log(Level.SEVERE, 
                "error onRowCancel:"+VerArtistasControl.class.getName()+" "+e,e);
        }
       
    }
    /**
     * metodo eliminar
     */
    public void eliminar(){
            for(Disco d: discos){
            if(d.isSeleccion()){
                discosEliminados.add(d);
            }
        }
        if(!discosEliminados.isEmpty()){
            for (Disco d : discosEliminados) {
                conDiscos.eliminar(d.getId());
            } 
            discos.removeAll(discosEliminados);
        }
         FacesMessage msg = new FacesMessage("Eliminados con exito"," ");
         FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }

    public List<Disco> getDiscosEliminados() {
        return discosEliminados;
    }

    public void setDiscosEliminados(List<Disco> discosEliminados) {
        this.discosEliminados = discosEliminados;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public int getIdisco() {
        return idisco;
    }

    public void setIdisco(int idisco) {
        this.idisco = idisco;
    }

    public String getNomartista() {
        return nomartista;
    }

    public void setNomartista(String nomartista) {
        this.nomartista = nomartista;
    }

    public String getNombredisco() {
        return nombredisco;
    }

    public void setNombredisco(String nombredisco) {
        this.nombredisco = nombredisco;
    }

    public int getIdartista() {
        return idartista;
    }

    public void setIdartista(int idartista) {
        this.idartista = idartista;
    }

    public ArtistaModel getConArtistas() {
        return conArtistas;
    }

    public void setConArtistas(ArtistaModel conArtistas) {
        this.conArtistas = conArtistas;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<String> getNomArtistas() {
        return nomArtistas;
    }

    public void setNomArtistas(List<String> nomArtistas) {
        this.nomArtistas = nomArtistas;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }

    public List<Disco> getDiscosFiltrados() {
        return discosFiltrados;
    }

    public void setDiscosFiltrados(List<Disco> discosFiltrados) {
        this.discosFiltrados = discosFiltrados;
    }

    public DiscoModel getConDiscos() {
        return conDiscos;
    }

    public void setConDiscos(DiscoModel conDiscos) {
        this.conDiscos = conDiscos;
    }
    
}
