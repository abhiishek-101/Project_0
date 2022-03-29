package com.awesomebank.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.awesomebank.app.Account;

public class DbService {
	private static String url= "jdbc:postgresql://localhost:5432/bankdb";
	private static	String user= "postgres";
	private static String password= "admin@123";
	

	public static Connection createDBConection() {
		Connection con=null;
		
			try {
				Class.forName("org.postgresql.Driver");
				con= DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException  | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return con;
	}
}
	
	
	
