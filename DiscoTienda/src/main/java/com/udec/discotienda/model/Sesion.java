/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.model;

import com.udec.discotienda.pojo.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase sesion
 * @author ASUS
 */
public class Sesion implements Serializable{
    
         Connection conn = null; 
         Statement stm;  
         ResultSet rs;   
         int resultUpdate = 0;
         
         Usuario user;
         ArrayList<Usuario> usuarios;
         
        int id;
        String nombre;
        int cedula;
        String usuario;
        String clave;
        int idrol;
        /**
         * constructor sesion
         */
        public Sesion() {
        usuarios = new ArrayList();
        usuarios=consultaUsuarios();
        }
        /**
         * metodo consulta
         * @return 
         */
          public final ArrayList<Usuario> consultaUsuarios(){  
           try{ 
               conn = ConectaBD.abrir();
               stm = conn.createStatement(); 
               rs = stm.executeQuery("select * from persona"); 
               if(!rs.next()){    
                   System.out.println(" No se encontraron registros");  
                   ConectaBD.cerrar();
                   return null;  
               }else{       
                   do{
                       id=rs.getInt("idpersona");
                       nombre = rs.getString("nombre"); 
                       cedula=rs.getInt("cedula");
                       usuario = rs.getString("usuario"); 
                       clave = rs.getString("clave"); 
                       idrol=rs.getInt("idrolusuario");
                       
                       user = new Usuario(id, nombre, cedula, usuario, clave, idrol); 
                       usuarios.add(user);        
                   }while(rs.next());     
                   ConectaBD.cerrar();
                   return usuarios;
               }  
    }catch(Exception e){ 
        System.out.println("Error en la base de datos.");     
        return null;  
    }
  }
          /**
           * metodo validar sesion
           * @param usuario
           * @param contrasena
           * @return 
           */
       public Usuario validarSesion(String usuario, String contrasena) {
        Usuario usuarioLogeado = null;
        for (Usuario usu : usuarios) {
            if(usu.getUsuario().equals(usuario) && usu.getClave().equals(contrasena)){
                usuarioLogeado = usu;
                return usuarioLogeado;
            }
        }
        return usuarioLogeado;
    }
}
