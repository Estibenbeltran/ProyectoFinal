/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.pojo;

import java.io.Serializable;

/**
 *
 * @author dairo
 */
public class CancionCompra implements Serializable{

    private int id;
    private String nombrecan, duracion, nombreart, caratula, nombredisco;
    private float precio;
    private boolean seleccion;

    public CancionCompra() {
    }

    public CancionCompra(int id, String nombrecan, String duracion, String nombreart, String caratula, String nombredisco, float precio) {
        this.id = id;
        this.nombrecan = nombrecan;
        this.duracion = duracion;
        this.nombreart = nombreart;
        this.caratula = caratula;
        this.nombredisco = nombredisco;
        this.precio = precio;
    }

    public CancionCompra(int id, String nombrecan, String duracion, String nombreart, String caratula, String nombredisco, float precio, boolean seleccion) {
        this.id = id;
        this.nombrecan = nombrecan;
        this.duracion = duracion;
        this.nombreart = nombreart;
        this.caratula = caratula;
        this.nombredisco = nombredisco;
        this.precio = precio;
        this.seleccion = seleccion;
    }
    
    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public String getNombrecan() {
        return nombrecan;
    }

    public void setNombrecan(String nombrecan) {
        this.nombrecan = nombrecan;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNombreart() {
        return nombreart;
    }

    public void setNombreart(String nombreart) {
        this.nombreart = nombreart;
    }

    public String getNombredisco() {
        return nombredisco;
    }

    public void setNombredisco(String nombredisco) {
        this.nombredisco = nombredisco;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
