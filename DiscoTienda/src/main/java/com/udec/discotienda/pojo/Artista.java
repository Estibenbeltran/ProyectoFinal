/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.pojo;

import java.util.Date;


/**
 * Clase artista
 * @author dairo
 */
public class Artista {
    /**
     * variables atributos
     */
    private int id;
    private String nombre;
    private String apellido;
    private Date fechanacimiento;
    private String nacionalidad,fecha;
    private boolean seleccion;

    /**
     * constructor vacio
     */
    public Artista() {
    }
    /**
     * constructor atributos
     * @param id
     * @param nombre
     * @param apellido
     * @param fechanacimiento
     * @param nacionalidad
     * @param fecha 
     */
    public Artista(int id, String nombre, String apellido, Date fechanacimiento, String nacionalidad, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.nacionalidad = nacionalidad;
        this.fecha = fecha;
    }
    /**
     * constructor atributos
     * @param id
     * @param nombre
     * @param apellido
     * @param fechanacimiento
     * @param nacionalidad 
     */
    public Artista(int id, String nombre, String apellido, Date fechanacimiento, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento=fechanacimiento;
        this.nacionalidad = nacionalidad;
    }
    /**
     * constructor Aatributos
     * @param nombre
     * @param apellido
     * @param fechanacimiento
     * @param nacionalidad 
     */
    public Artista(String nombre, String apellido, Date fechanacimiento, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento=fechanacimiento;
        this.nacionalidad = nacionalidad;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    
    
}
