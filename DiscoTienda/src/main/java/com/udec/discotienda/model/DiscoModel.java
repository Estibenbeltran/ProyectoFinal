/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.discotienda.model;


import com.udec.discotienda.pojo.Disco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * clase disco
 * @author dairo
 */
public class DiscoModel {
         Connection conn = null; 
         Statement stm;  
         ResultSet rs;   
         int resultUpdate = 0;
         
         Disco disco;
         String caratula,nombre;
         int idDisco,idartista;
         String nombreArtista;
         
         /**
          * metodo registrar
          * @param caratula
          * @param nombre
          * @param idartista 
          */
         public void registrar(String caratula,String nombre, int idartista) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("insert into disco(caratula,nombre,idartistafk) values('"+caratula+"','"+nombre+"','"+idartista+"');"); 
             
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
         public ArrayList<Disco> consultaDiscos(){  
           ArrayList<Disco> discos = new ArrayList(); 
           try{ 
               conn = ConectaBD.abrir();
               stm = conn.createStatement();
               rs = stm.executeQuery("select a.iddisco,a.caratula,a.nombre,a.idartistafk,concat(b.nombre,' ',b.apellido) as nombreCompleto\n"
                       + "from disco a, artista b\n"
                       + "where \n"
                       + "a.idartistafk=b.idartista");
               if(!rs.next()){    
                   System.out.println(" No se encontraron registros");  
                   ConectaBD.cerrar();
                   return null;  
               }else{       
                   do{
                       idDisco=rs.getInt("iddisco");
                       caratula=rs.getString("caratula");
                       nombre = rs.getString("nombre");
                       idartista=rs.getInt("idartistafk");
                       nombreArtista=rs.getString("nombreCompleto");
                       
                       disco = new Disco(idDisco,caratula,nombre,idartista,nombreArtista); 
                       discos.add(disco);        
                   }while(rs.next());     
                   ConectaBD.cerrar();
                   return discos;
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
          * @param idartista 
          */
         public void modificar(int id,String nombre, int idartista) { 
         try{    
             conn = ConectaBD.abrir(); 
             stm = conn.createStatement();  
             resultUpdate = stm.executeUpdate("update disco a\n"
                     + "set a.nombre='"+nombre+"',a.idartistafk='"+idartista+"'\n"
                     + "where\n"
                     + "a.iddisco='"+id+"'");
             
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
             resultUpdate = stm.executeUpdate("delete from disco where iddisco='"+id+"';"); 
             
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
