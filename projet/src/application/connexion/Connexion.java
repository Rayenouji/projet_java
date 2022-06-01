package application.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class Connexion {
		private static String login = "root";
	    private static String password = "";
	    private static String url = "jdbc:mysql://localhost/entreprise_project";
	    private static Connection cn;
	    static {
	        try {
	           
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("connexion");
	          
	            cn = DriverManager.getConnection(url, login, password);
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Impossible de charger le driver");
	        } catch (SQLException ex) {
	            System.out.println("Erreur de connexion");
	        }
	    }
	    
	public static Connection getCn() {
	
		return cn;
	}
	}