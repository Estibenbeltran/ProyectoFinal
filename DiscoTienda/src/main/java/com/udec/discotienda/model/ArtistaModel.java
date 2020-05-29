/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.model;

import com.udec.discotienda.pojo.Artista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Clase artista
 * @author dairo
 */
public class ArtistaModel {
    /**
     * variables de conexion
     */
         Connection conn = null; 
         Statement stm;  
         ResultSet rs;   
         int resultUpdate = 0;
         
         Artista artista;
         String nombre, apellido,nacionalidad,fecha;
         Date fechanacimiento;
         int id;
         /**
          * metodo registrar
          * @param nombre
          * @param apellido
          * @param fechanac
          * @param nacion 
          */
         public void registrar(String nombre,String apellido, String fechanac, String nacion) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("insert into artista(nombre,apellido,fecha,nacion) values('"+nombre+"','"+apellido+"','"+fechanac+"','"+nacion+"');"); 
             
             if(resultUpdate != 0){    
                 ConectaBD.cerrar(); 
             }else{     
                 ConectaBD.cerrar();     
             }                 
         }catch (Exception e) {   
             System.out.println("Error en la base de datos.");     
         }    
     }
         /**
          * metodo consultar
          * @return 
          */
      public ArrayList<Artista> consultaArtistas(){  
           ArrayList<Artista> artistas = new ArrayList(); 
           try{ 
               conn = ConectaBD.abrir();
               stm = conn.createStatement(); 
               rs = stm.executeQuery("select * from artista"); 
               if(!rs.next()){    
                   System.out.println(" No se encontraron registros");  
                   ConectaBD.cerrar();
                   return null;  
               }else{       
                   do{
                       id=rs.getInt("idartista");
                       nombre = rs.getString("nombre"); 
                       apellido = rs.getString("apellido");  
                       fechanacimiento = rs.getDate("fecha");
                       nacionalidad = rs.getString("nacion");
                       fecha=getMyFormattedDate(fechanacimiento);
                       artista = new Artista(id,nombre,apellido,fechanacimiento,nacionalidad,fecha); 
                       artistas.add(artista);        
                   }while(rs.next());     
                   ConectaBD.cerrar();
                   return artistas;
               }  
    }catch(Exception e){ 
        System.out.println("Error en la base de datos.");     
        return null;  
    }
  }
      /**
       * metodo modificar
       * @param id
       * @param nombre
       * @param apellido
       * @param fechanac
       * @param nacion 
       */
      public void modificar(int id,String nombre,String apellido, String fechanac, String nacion) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("update artista a\n"
                     + "set a.nombre='"+nombre+"',a.apellido='"+apellido+"',a.fecha='"+fechanac+"',a.nacion='"+nacion+"'\n"
                     + "where a.idartista='"+id+"'");
             
             if(resultUpdate != 0){    
                 ConectaBD.cerrar(); 
             }else{     
                 ConectaBD.cerrar();     
             }                 
         }catch (Exception e) {   
             System.out.println("Error en la base de datos.");     
         }    
     }
      /**
       * metodo formato de fecha
       * @param fecha
       * @return 
       */
       public String getMyFormattedDate(Date fecha) {
        return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }
       /**
        * metodo eliminar
        * @param id 
        */
        public void eliminar(int id) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("delete from artista where idartista='"+id+"';"); 
             
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
