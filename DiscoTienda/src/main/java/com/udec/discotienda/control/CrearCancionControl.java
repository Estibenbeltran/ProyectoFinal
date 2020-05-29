/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import com.udec.discotienda.model.CancionModel;
import com.udec.discotienda.model.DiscoModel;
import com.udec.discotienda.pojo.Disco;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase crear cancion.
 * @author dairo
 */
@Named(value = "crearCancionControl")
@RequestScoped
public class CrearCancionControl {
    /*** variable nombre*/
    private String nombre;
    /*** variable duracion*/
    private String duracion;
    /*** variable disco*/
    private String disco;
    /*** variable precio*/
    private float precio;
    /*** modelo cancion*/
    CancionModel cancion=new CancionModel();
    /*** modelo discoconsulta*/
    DiscoModel discosconsulta;
    /*** lista de discos*/
    List<Disco> listaDiscos;
    /*** lista nombre de discos*/
    List<String> nombreDiscos;
    
    /**
     * postconstruct clase
     */
    @PostConstruct
    public void init(){
        llenarListaDiscos();
    
    }
    /**
     * constructor de la clase
     */
    public CrearCancionControl() {
        
        
    }
    /**
     * metodo crear cancion
     */
      public void crearCancion(){
        try {
            int idDiscoSel;
            idDiscoSel=obtenerIdDisco();
              cancion.registrar(nombre, duracion, idDiscoSel,precio);
              FacesMessage msg = new FacesMessage("Agregado con exito"," ");
              FacesContext.getCurrentInstance().addMessage(null, msg);
              
        } catch (Exception e) {
            throw e;
        }
    }
      /**
       * metodo llenar lista discos
       */
      public void llenarListaDiscos(){
          discosconsulta=new DiscoModel();
          listaDiscos=new ArrayList<>();
          nombreDiscos= new ArrayList<>();
          listaDiscos=discosconsulta.consultaDiscos();
          String nom;
          
          for (Disco discos : listaDiscos) {
              nom=discos.getNombre();
              nombreDiscos.add(nom);
          }
      }
      /**
       * metodo obtener id del disco
       * @return id disco
       */
      public int obtenerIdDisco(){
          int idDisco=0;
          String nombreDisco=disco;
          
          for (Disco listaDisco : listaDiscos) {
              if(listaDisco.getNombre().equals(nombreDisco)){
                  idDisco=listaDisco.getId();
              }
          }
      return idDisco;
      }

    public List<String> getNombreDiscos() {
        return nombreDiscos;
    }

    public void setNombreDiscos(List<String> nombreDiscos) {
        this.nombreDiscos = nombreDiscos;
    }

    public List<Disco> getListaDiscos() {
        return listaDiscos;
    }

    public void setListaDiscos(List<Disco> listaDiscos) {
        this.listaDiscos = listaDiscos;
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

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
