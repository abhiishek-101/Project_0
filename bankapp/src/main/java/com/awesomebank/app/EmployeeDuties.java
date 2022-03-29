package com.awesomebank.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.awesomebank.db.DbService;

public class EmployeeDuties {
	Menu m= new Menu();
	public void showCustomers() {
		
		Connection con = DbService.createDBConection();
		try {
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery("select * from customers order by id ASC;");
			
			while(rs.next()) {
				System.out.println("*******************************************************************************");
				System.out.println("Customer ID: "+rs.getInt(1));
				System.out.println("Name: "+rs.getString(2)+ " " + rs.getString(3));
				System.out.println("PAN: "+rs.getString(4));
				System.out.println("Balance: "+rs.getString(6));
				System.out.println("Status: "+rs.getString(7));
				System.out.println("*******************************************************************************");
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	public void pendingReqs() {
		Connection con = DbService.createDBConection();
		Scanner sc= new Scanner(System.in);
		ArrayList <Integer> listId= new ArrayList<Integer>();
		try {
			Statement st= con.createStatement();
			Statement st2= con.createStatement();
			Statement st3= con.createStatement();
			Statement st4=con.createStatement();
			Statement st5=con.createStatement();
			Statement st6=con.createStatement();
			ResultSet rs= st.executeQuery("select * from customers where accstatus='pending' order by id ASC;");
			
			
			
			while(rs.next()) {
				int id= rs.getInt(1);
				System.out.println("*******************************************************************************");
				listId.add(rs.getInt(1));
				System.out.println("Customer ID: "+rs.getInt("id"));
				System.out.println("Name: "+rs.getString(2)+ " " + rs.getString(3));
				System.out.println("PAN: "+rs.getString(4));
				System.out.println("Balance: "+rs.getString(6));
				System.out.println("Account Type: "+rs.getString(8));
				System.out.println("Account Status: "+ rs.getString(7));
				System.out.println("*******************************************************************************");
				System.out.println();
				System.out.println();
			}
			//System.out.println(listId);
			
			System.out.println("Enter customer id you would like to approve: ");
			int input= sc.nextInt(); //takes input of desired customer id.
			if(listId.contains(input)) {
				System.out.println("Press 1 and Enter to Accept/ Press 2 to Reject / Press any other key to keep in Pending");
				int choice= sc.nextInt(); //takes input of whether to accept, reject or pending
				int check=0;
				if(choice==1) {
					check =st2.executeUpdate("update customers set accstatus='active' where id="+input);
					if(check!=0) {
							 st6.executeUpdate("insert into active_accounts values("+ input+ ");"); //inserting into activated accounts table for reference.
						System.out.println("Customer id: "+input+" is successfully Approved.");
						System.out.println();
					}
							System.out.println("Press 1 to show more pending requests/ Press Any other key to exit. ");
							int response=sc.nextInt();
							if(response==1) {
								
								pendingReqs();
							}else {
								m.employeeLogin();
							}
					
				} else if(choice==2) {
					st3.executeUpdate("update customers set accstatus ='reject' where id="+input);
					System.out.println("Customer id: "+input+" is successfully Rejected.");
							System.out.println("Press 1 to show more pending requests/ Press Any other key to exit. ");
							int response=sc.nextInt();
							if(response==1) {
								
								pendingReqs();
							}else {
								m.employeeLogin();
							}
					
				}else {
					st4.executeUpdate("update customers set accstatus ='pending' where id="+input);
					System.out.println("Customer id: "+input+" is kept in pending.");
							System.out.println("Press 1 to show more pending requests/ Press Any other key to exit. ");
							int response=sc.nextInt();
							if(response==1) {
								
								pendingReqs();
							}else {
								m.employeeLogin();
							}
					
				}
			}else {
				System.out.println("The customer id you entered is either already approved or doesn't exist.");
							System.out.println("Press 1 to show more pending requests/ Press Any other key to exit. ");
							int response=sc.nextInt();
							if(response==1) {
								
								pendingReqs();
							}else {
								m.employeeLogin();
							}
			}

				
				//----make a security, that works like, when a customer tries to function their account, their account status should be
				//checked, if its active, they can function their account, else they can't.
			
			} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}



	public void viewTransactions() {
		
		Connection con = DbService.createDBConection();
		
		try {
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("Select * from transactions order by cust_id ASC;");
			
			while(rs.next()) {
				System.out.println("*************************************");
				System.out.println();
				System.out.println(" Customer id: "+rs.getInt("cust_id"));
				System.out.println(" Transaction Type: "+rs.getString("transaction_type"));
				System.out.println(" Amount: "+rs.getDouble("amount"));
				System.out.println(" Sent To: "+rs.getInt("sent_to"));
				System.out.println(" Received From: "+rs.getInt("received_from"));
				System.out.println();
				System.out.println("*************************************");
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
}
