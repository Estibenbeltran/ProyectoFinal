/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.pojo;

/**
 *
 * @author dairo
 */
public class Usuario {
    private int id;
    private String nombre;
    private int cedula;
    private String usuario;
    private String clave;
    private int idrol;

    public Usuario() {
    }

    public Usuario(String nombre, int cedula, String usuario, String clave) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Usuario(int id, String nombre, int cedula, String usuario, String clave, int idrol) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.usuario = usuario;
        this.clave = clave;
        this.idrol = idrol;
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

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }
    
    
    
    
}
