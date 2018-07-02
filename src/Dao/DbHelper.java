package Dao;

import java.sql.*;


public class DbHelper {
	
	public static Connection getConnectionToDb (){
	      Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      if (c != null) {
				System.out.println("Connection made to DB!");
			}
	      return c;
	}
}
