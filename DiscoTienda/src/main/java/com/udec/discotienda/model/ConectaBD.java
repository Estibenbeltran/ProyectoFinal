package com.udec.discotienda.model;
import java.sql.*; 
import java.sql.DriverManager; 
/**
 * clase conexion base de datos
 * @author dairo
 */
public class ConectaBD {
   static Connection conexion=null;
   private static final String url = "jdbc:mysql://localhost:3306/discotienda";
   private static final String usuario="root";
   private static final String contraseña="";
   /**
    * metodo abrir conexion
    * @return 
    */
   public static Connection abrir(){ 
     try {
        Class.forName("com.mysql.jdbc.Driver");
        conexion=DriverManager.getConnection(url,usuario,contraseña);
         System.out.println("Conexion establecida");
         
    } catch ( ClassNotFoundException | SQLException e ) {
         System.out.println("Error:"+ e);
    }
     return conexion;
}        
     /**
      * metodo cerrar conexion
      */
     public static void cerrar(){   
         try{         
             if(conexion != null)        
                 conexion.close();   
         } catch (Exception e){   
             System.out.println("Error: No se logro cerrar conexion:\n"+e);     
         }     
     }
}
