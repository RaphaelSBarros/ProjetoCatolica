package db;
import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
public class Conexao {
	
	    
	    
	       public static void main(String[] args) {
	           try
	           {
	               Class.forName("com.mysql.jdbc.Driver");
	               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetoCat1","root","root");
	               Statement stmt=con.createStatement();  
	               ResultSet rs=stmt.executeQuery("show databases;");
	               System.out.println("Connected teste");  
	           }
	           catch(Exception e)
	           {
	               System.out.println(e);
	           }
	       }  
	   }
