/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 * Clase para administrar template cliente
 * @author dairo
 */
@Named(value = "inicioClienteControl")
@SessionScoped
public class inicioClienteControl implements Serializable {
    /**
     * variable seleccion de pagina
     */
    private String seleccionado="PaginasCliente/carritoCompras.xhtml";
    
    /**
     * Constructor de la clase
     */
    public inicioClienteControl() {
    }
    /**
     * metodo cerrar sesion
     * @return redireccion de la pagina
     */
     public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../login.xhtml";
    }

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }
     
}
