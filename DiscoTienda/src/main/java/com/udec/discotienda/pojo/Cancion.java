/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.pojo;

/**
 *  Clase cancion
 * @author dairo
 */
public class Cancion {
    /**
     * variables de atributos
     */
    private int idCancion;
    private String nombre;
    private String duracion;
    private int disco;
    private float precio;
    private String nombreDisco;
    private boolean seleccion;
    
/**
 * constructor vacio
 */
    public Cancion() {
    }
/**
 * constructor con atributos
 * @param idCancion
 * @param nombre
 * @param duracion
 * @param disco
 * @param precio
 * @param nombreDisco 
 */
    public Cancion(int idCancion, String nombre, String duracion, int disco, float precio, String nombreDisco) {
        this.idCancion = idCancion;
        this.nombre = nombre;
        this.duracion = duracion;
        this.disco = disco;
        this.precio = precio;
        this.nombreDisco = nombreDisco;
    }

   /**
    * constructor con atributos
    * @param nombre
    * @param duracion
    * @param disco
    * @param precio 
    */
    /**
     * constructor atributos 
     * @param nombre
     * @param duracion
     * @param disco
     * @param precio 
     */
    public Cancion(String nombre, String duracion, int disco, float precio) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.disco = disco;
        this.precio = precio;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public void setNombreDisco(String nombreDisco) {
        this.nombreDisco = nombreDisco;
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

    public int getDisco() {
        return disco;
    }

    public void setDisco(int disco) {
        this.disco = disco;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
