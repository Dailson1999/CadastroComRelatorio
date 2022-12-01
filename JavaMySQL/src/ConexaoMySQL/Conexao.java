package ConexaoMySQL;

import java.sql.Connection; 
import java.sql.DriverManager; 
 
public class Conexao { 
 
 public static Connection fazCon() { 
  String driver = "com.mysql.cj.jdbc.Driver"; 
  String url = "jdbc:mysql://localhost:3306/db_javaEmpresa"; 
  String user = "root"; 
  String password = ""; 
  Connection con = null; 
         try { 
          Class.forName(driver); 
          con = DriverManager.getConnection(url, user, password); 
          System.out.println("Conexão Realizada!"); 
         } 
         catch (Exception e) { 
        	 System.out.println("Problemas na conexão!"); 
        	 e.printStackTrace(); 
  }
		return con;
 } 
 }