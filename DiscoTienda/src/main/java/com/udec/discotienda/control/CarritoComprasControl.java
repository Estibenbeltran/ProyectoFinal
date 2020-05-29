/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import com.udec.discotienda.model.ArtistaModel;
import com.udec.discotienda.model.CancionModel;
import com.udec.discotienda.model.DiscoModel;
import com.udec.discotienda.pojo.Artista;
import com.udec.discotienda.pojo.CancionCompra;
import com.udec.discotienda.pojo.Disco;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * clase carrito de compras
 * @author dairo
 */
@Named(value = "carritoComprasControl")
@SessionScoped
public class CarritoComprasControl implements Serializable {
    /*** lista canciones.*/
    private List<CancionCompra> listaCanciones;
    /*** lista filtro.*/
    private List<CancionCompra> listaCancionesFiltro;
    /*** lista carritocompras.*/
    private List<CancionCompra> listaCarroCompras;
    
    /*** lista atristas.*/
    private List<Artista> artistas;
    /*** lista nombreartistas.*/
    private List<String> nomArtistas;
    
    /*** modelo artista.*/
    private ArtistaModel conArtistas;
    /*** modelo cancioncompra.*/
    private CancionModel conCancionesCompra;
    
    /*** lista discos.*/
    private List<Disco> discos; 
    /*** lista nombre discos.*/
    private List<String> nomDiscos;
    /*** modelo consulta discos.*/
    private DiscoModel consultaDiscos;
    /*** valor total*/
    private float valortotal;
    
    
    /**
     * constructor de la clase
     */
    public CarritoComprasControl() {
        listaCarroCompras=new ArrayList();
        artistas=new ArrayList();
        discos=new ArrayList();
    }
    
    /**
     * post construct
     */
    @PostConstruct
    public void init(){
    llenarListadoCanciones();
    }
    
    /**
     * metodo llenar lista canciones
     */
    public void llenarListadoCanciones(){
    conCancionesCompra=new CancionModel();
    listaCanciones=new ArrayList();
    listaCanciones=conCancionesCompra.consultaCancionesCompra();
    llenarArtistas();
    llenarDiscos();
    }
    /**
     * metodo agregar al carrito
     */
    public void agregarAlCarrito(){
        
        for(CancionCompra comp: listaCanciones){
            if(comp.isSeleccion()){
                listaCarroCompras.add(comp);
            }
        }
        if(!listaCarroCompras.isEmpty()){
            listaCanciones.removeAll(listaCarroCompras);
        }  
        valorTotal();
        
        FacesMessage msg = new FacesMessage("Se han agregado canciones al carrito"," ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
     * metodo llenar lista artistas
     */
    public void llenarArtistas(){
        conArtistas= new ArtistaModel();
        nomArtistas=new ArrayList();
         artistas=conArtistas.consultaArtistas();
            for (Artista art : artistas) {
                nomArtistas.add(art.getNombre()+" "+art.getApellido());
         }
     }
    /**
     * metodo llenar discos
     */
     public void llenarDiscos(){
        consultaDiscos= new DiscoModel();
        nomDiscos=new ArrayList();
         discos=consultaDiscos.consultaDiscos();
            for (Disco dis : discos) {
                nomDiscos.add(dis.getNombre());
         }
     }
     /**
      * metodo valor total compra
      */
     public void valorTotal(){
         valortotal=0;
         for (CancionCompra valor : listaCarroCompras) {
                valortotal=valortotal+valor.getPrecio();
         }
     
     }
     /**
      * metodo cancelar compra
      */
     public void cancelarCompra(){
         valortotal=0;
         listaCarroCompras.clear();
         listaCanciones.clear();
         conCancionesCompra=new CancionModel();
         listaCanciones=conCancionesCompra.consultaCancionesCompra();
     }
/**
 * metodo finalizar compra
 */
    public void finalizarCompra(){
        listaCarroCompras.removeAll(listaCarroCompras);
        FacesMessage msg = new FacesMessage("Compra realizada con exito"," ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public float getValortotal() {
        return valortotal;
    }

    public void setValortotal(float valortotal) {
        this.valortotal = valortotal;
    }

    public DiscoModel getConsultaDiscos() {
        return consultaDiscos;
    }

    public void setConsultaDiscos(DiscoModel consultaDiscos) {
        this.consultaDiscos = consultaDiscos;
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
    
    
    public List<String> getNomArtistas() {
        return nomArtistas;
    }

    public void setNomArtistas(List<String> nomArtistas) {
        this.nomArtistas = nomArtistas;
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

    public List<CancionCompra> getListaCarroCompras() {
        return listaCarroCompras;
    }

    public void setListaCarroCompras(List<CancionCompra> listaCarroCompras) {
        this.listaCarroCompras = listaCarroCompras;
    }

    public List<CancionCompra> getListaCancionesFiltro() {
        return listaCancionesFiltro;
    }

    public void setListaCancionesFiltro(List<CancionCompra> listaCancionesFiltro) {
        this.listaCancionesFiltro = listaCancionesFiltro;
    }

    public List<CancionCompra> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<CancionCompra> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }

    public CancionModel getConCancionesCompra() {
        return conCancionesCompra;
    }

    public void setConCancionesCompra(CancionModel conCancionesCompra) {
        this.conCancionesCompra = conCancionesCompra;
    }

    
}
