/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.control;


import com.udec.discotienda.model.ArtistaModel;
import com.udec.discotienda.model.DiscoModel;
import com.udec.discotienda.pojo.Artista;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.primefaces.model.UploadedFile;

/**
 * Clase crear cancion
 * @author ASUS
 */
@Named(value = "crearDiscoControl")
@RequestScoped
public class CrearDiscoControl implements Serializable{
    /*** variable nombre*/
    private String nombre;
    /*** variable apellido*/
    private String artista;
    /*** variable archivo subido*/
    private UploadedFile file;
    /*** modelo disco*/
    private DiscoModel disco=new DiscoModel();
    /*** lista artistas*/
    private List<Artista> artistas;
    /*** liste nombre artistas*/
    private List<String> artistaNombre;
    /*** modelo artistamodel*/
    private ArtistaModel consulta;
    
    /**
     * postconstruct clase
     */
     @PostConstruct
    public void init() {
        listadoArtistas();
    }
    /**
     * constructor de la clase
     */
    public CrearDiscoControl() {
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    /**
     * metodo crear disco
     */
    public void crearDisco() {
            String ruta="C:/Users/ASUS/Documents/NetBeansProjects/DiscoTienda/src/main/webapp/resources/imagenes/";
            String rutaimg;
            String rutadef;
            try {
                InputStream input=file.getInputstream();
                rutaimg=ruta+file.getFileName();
                OutputStream out = new FileOutputStream(new File(rutaimg));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                int idart=obtenerIdArtista();
                rutadef=rutaimg.substring(69);
                disco.registrar(rutadef, nombre, idart);
                FacesMessage message = new FacesMessage("Registro exitoso",nombre);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (IOException ex) {
                Logger.getLogger(CrearDiscoControl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /**
     * metodo llenar lista de artistas
     */
    public void listadoArtistas(){
        consulta=new ArtistaModel();
        artistas=new ArrayList<>();
        artistaNombre=new ArrayList<>();
        String nom;
        artistas=consulta.consultaArtistas();
        for (Artista art : artistas) {
            
            nom=art.getNombre()+" "+art.getApellido();
            artistaNombre.add(nom); 
        }
    }
    /**
     * metodo obtener id artista
     * @return id artista
     */
    public int obtenerIdArtista(){
        int idartista=0;
        String nombrecom=artista;
        String[] partes = nombrecom.split(" ");
        String nom = partes[0];
        String ape = partes[1];
        
        for (Artista art : artistas) {
            if(art.getNombre().equals(nom) && art.getApellido().equals(ape)){
                idartista=art.getId();
                return idartista;
            }
        }
        return idartista;
        
    }

    public DiscoModel getDisco() {
        return disco;
    }

    public void setDisco(DiscoModel disco) {
        this.disco = disco;
    }

    public ArtistaModel getConsulta() {
        return consulta;
    }

    public void setConsulta(ArtistaModel consulta) {
        this.consulta = consulta;
    }

    public List<String> getArtistaNombre() {
        return artistaNombre;
    }

    public void setArtistaNombre(List<String> artistaNombre) {
        this.artistaNombre = artistaNombre;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
    
    
    
}
