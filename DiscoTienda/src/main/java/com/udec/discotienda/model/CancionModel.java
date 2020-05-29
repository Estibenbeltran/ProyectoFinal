/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.model;

import com.udec.discotienda.pojo.Cancion;
import com.udec.discotienda.pojo.CancionCompra;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * clase cancion
 * @author ASUS
 */
public class CancionModel {
         Connection conn = null; 
         Statement stm;  
         ResultSet rs;   
         int resultUpdate = 0;
         
         Cancion cancion;
         CancionCompra cancioncom;
         int idcancion,iddisco;
         float precio;
         String nombre, nombreDisco,duracion,nombreartis,caratula;
         
         
         /**
          * metodo registrar
          * @param nombre
          * @param duracion
          * @param disco
          * @param precio 
          */
        public void registrar(String nombre,String duracion, int disco, float precio) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("insert into cancion(nombre,duracion,iddiscofk,precio) values('"+nombre+"','"+duracion+"','"+disco+"','"+precio+"');"); 
             
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
        public ArrayList<Cancion> consultaCanciones(){  
           ArrayList<Cancion> canciones = new ArrayList(); 
           try{ 
               conn = ConectaBD.abrir();
               stm = conn.createStatement();
               rs = stm.executeQuery("select a.idcancion,a.nombre,a.duracion,a.iddiscofk,a.precio,b.nombre as nombreDisco\n"
                       + "from cancion a, disco b\n"
                       + "where \n"
                       + "a.iddiscofk=b.iddisco");
               if(!rs.next()){    
                   System.out.println(" No se encontraron registros");  
                   ConectaBD.cerrar();
                   return null;  
               }else{       
                   do{
                       idcancion=rs.getInt("idcancion");
                       nombre=rs.getString("nombre");
                       duracion=rs.getString("duracion");
                       iddisco=rs.getInt("iddiscofk");
                       precio=rs.getFloat("precio");
                       nombreDisco=rs.getString("nombreDisco");
                       
                       
                       cancion = new Cancion(idcancion, nombre, duracion, iddisco, precio, nombreDisco); 
                       canciones.add(cancion);        
                   }while(rs.next());     
                   ConectaBD.cerrar();
                   return canciones;
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
         * @param duracion
         * @param disco
         * @param precio 
         */
        public void modificar(int id, String nombre,String duracion, int disco, float precio) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("update cancion a\n"
                     + "set a.nombre='"+nombre+"',a.duracion='"+duracion+"',a.iddiscofk='"+disco+"',a.precio='"+precio+"'\n"
                     + "where\n"
                     + "a.idcancion='"+id+"'");
             
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
         * metodo eliminar
         * @param id 
         */
         public void eliminar(int id) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("delete from cancion where idcancion='"+id+"';"); 
             
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
          * metodo consulta canciones compra
          * @return 
          */
         public ArrayList<CancionCompra> consultaCancionesCompra(){  
           ArrayList<CancionCompra> CancionesVenta = new ArrayList(); 
           
           try{ 
               conn = ConectaBD.abrir();
               stm = conn.createStatement();
               rs = stm.executeQuery("select c.idcancion,c.nombre as nombrecancion,c.duracion,concat(a.nombre,' ',a.apellido)as nombreartista,b.caratula,b.nombre as nombredisco,c.precio\n"
                       + "from artista a, disco b, cancion c\n"
                       + "where \n"
                       + "b.iddisco=c.iddiscofk and\n"
                       + "b.idartistafk=a.idartista");
               if(!rs.next()){    
                   System.out.println(" No se encontraron registros");  
                   ConectaBD.cerrar();
                   return null;  
               }else{       
                   do{
                       idcancion=rs.getInt("idcancion");
                       nombre=rs.getString("nombrecancion");
                       duracion=rs.getString("duracion");
                       nombreartis=rs.getString("nombreartista");
                       caratula=rs.getString("caratula");
                       nombreDisco=rs.getString("nombredisco");
                       precio=rs.getFloat("precio");
    
                       cancioncom = new CancionCompra(idcancion, nombre, duracion, nombreartis,caratula,nombreDisco,precio); 
                       CancionesVenta.add(cancioncom);        
                   }while(rs.next());     
                   ConectaBD.cerrar();
                   return CancionesVenta;
               }  
    }catch(Exception e){ 
        System.out.println("Error en la base de datos.");     
        return null;  
    }
   }
}
