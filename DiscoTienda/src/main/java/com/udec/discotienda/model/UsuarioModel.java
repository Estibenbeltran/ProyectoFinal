/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * clase usuario
 * @author ASUS
 */
public class UsuarioModel {
         Connection conn = null; 
         Statement stm;  
         ResultSet rs;   
         int resultUpdate = 0;
         
         /**
          * metodo registrar user
          * @param nombre
          * @param cedula
          * @param username
          * @param clave 
          */
         public void registrarUsuario(String nombre,int cedula, String username, String clave) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("insert into persona(nombre,cedula,usuario,clave) values('"+nombre+"','"+cedula+"','"+username+"','"+clave+"');"); 
             
             if(resultUpdate != 0){    
                 ConectaBD.cerrar(); 
             }else{     
                 ConectaBD.cerrar();     
             }                 
         }catch (Exception e) {   
             System.out.println("Error en la base de datos.");     
         }    
     }
}
