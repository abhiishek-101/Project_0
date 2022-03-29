package com.awesomebank.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.awesomebank.db.DbService;

public class AccountApplication {

	public int application(CustomerApplication ca) {
		int flag=0;
		
		Connection con= DbService.createDBConection();
		
		try {
			String insertQuery= "insert into customers (firstname, lastname, pan, password, balance, accstatus, acctype) values(?, ?, ?, ?, ?, ?, ?); ";
			PreparedStatement st= con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, ca.getFirstName());
			st.setString(2, ca.getLastName());
			st.setString(3, ca.getPan());
		//	st.setString(4, ca.getUsername());
			st.setString(4, ca.getPassword());
			st.setDouble(5, ca.getIntialDeposit());
			st.setString(6, ca.getAccStatus());
			st.setString(7, ca.getAccType());
			flag= st.executeUpdate();
			ResultSet res= st.getGeneratedKeys();
			
			DisplayHeader dh= new DisplayHeader();
			
			System.out.println();
			System.out.println("Remember! Your Login id is: ");
			while(res.next()) {
			dh.displayHeader(res.getString(1));	
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return flag;
		
	}
	
}
