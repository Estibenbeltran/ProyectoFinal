/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.pojo;

/**
 *Clase disco
 * @author ASUS
 */
public class Disco {
    /**
     * variables atributos
     */
    private int id;
    private String caratula;
    private String nombre;
    private int idartista;
    private String nombreArtista;
    private boolean seleccion;
    /**
     * constructor vacio
     */
    public Disco() {
    }
    /**
     * constructor con atributos
     * @param id
     * @param caratula
     * @param nombre
     * @param idartista
     * @param nombreArtista 
     */
    public Disco(int id, String caratula, String nombre, int idartista, String nombreArtista) {
        this.id = id;
        this.caratula = caratula;
        this.nombre = nombre;
        this.idartista = idartista;
        this.nombreArtista = nombreArtista;
    }
/**
 * constructor con atributos
 * @param id
 * @param caratula
 * @param nombre
 * @param idartista 
 */
    public Disco(int id, String caratula, String nombre, int idartista) {
        this.id = id;
        this.caratula = caratula;
        this.nombre = nombre;
        this.idartista = idartista;
    }
  

    public Disco(String caratula, String nombre, int idartista) {
        this.caratula = caratula;
        this.nombre = nombre;
        this.idartista = idartista;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdartista() {
        return idartista;
    }

    public void setIdartista(int idartista) {
        this.idartista = idartista;
    }
    
    
}
