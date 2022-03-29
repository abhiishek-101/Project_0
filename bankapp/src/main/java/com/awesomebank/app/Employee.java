package com.awesomebank.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.awesomebank.db.DbService;

public class Employee {
	
	public int employeeCredentialCheck(int empId, String pwd) {
		int flag=0;
		
		try {
			Connection con = DbService.createDBConection();
			Statement st= con.createStatement();
			
			ResultSet rs= st.executeQuery("Select * from employee where empid ="+empId+" and pwd='"+pwd+"';");
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
