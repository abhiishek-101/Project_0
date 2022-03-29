package com.awesomebank.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.awesomebank.db.DbService;

public class Customer {

	public int customerCredentialCheck(String loginId, String password) {
		
		int flag=0;
		
		try {
			Connection con = DbService.createDBConection();
			Statement st= con.createStatement();
			
			ResultSet rs= st.executeQuery("Select * from customers where id ='"+loginId+"' and password='"+password+"';");
			if(rs.next()) {
				flag=1;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

		
	
	
	
	
	

	

}
