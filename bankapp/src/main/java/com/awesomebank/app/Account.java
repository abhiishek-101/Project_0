package com.awesomebank.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.awesomebank.db.DbService;

public class Account {
	
	
	
	public double accBalance(String loginId) {
		double balance =0;
		
		Connection con= DbService.createDBConection();
		try {
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select balance from customers where id="+loginId+";");
			if(rs.next()) {
			
				balance = rs.getDouble("balance");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return balance;
	}
	
	
	
	
	public int withdraw(String loginId,double amount) {
		if(amount > accBalance(loginId)) {
			System.out.println("You have insufficient funds.");
			System.out.println();
			//put exit option here
			//----exit code----//
			System.out.println("Enter 1 to exit and retry.");
			System.out.println();
			Scanner sc= new Scanner(System.in);
			int retry = sc.nextInt();
			if(retry ==1) {
				Menu m= new Menu();
				m.runMenu();
			}else {
				System.out.println("You will be redirected to main menu.");
				Menu m= new Menu();
				m.runMenu();
			}
			//---- exit code ends-----//
		} 
		int flag=0;
	 
	 Connection con= DbService.createDBConection();
	 try {
		Statement st= con.createStatement();
		flag = st.executeUpdate("update customers set balance = balance-" +amount+ "where id="+loginId+";");
		
		if(flag!=0) {
			
			PreparedStatement ps= con.prepareStatement("insert into transactions(cust_id, transaction_type, amount)values(?,?,?);");
			ps.setString(1, loginId);
			ps.setString(2, "Withdrawl");
			ps.setDouble(3, amount);
			ps.executeUpdate();
			
			
			Statement st2= con.createStatement();
			ResultSet rs= st2.executeQuery("select balance from customers where id="+loginId+";");
			
			
			System.out.println("You have withdrawn " + amount+ "INR successfully.");
			if(rs.next()) {
				System.out.println("Your closing balance is: INR " +rs.getString("balance"));
			}
		}
		
		
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
		
	 return flag;
}

	public String checkCredibility(String loginId){
		
		ResultSet valid;	
		String status="";
		Connection con= DbService.createDBConection();
		 
			try {
				Statement st= con.createStatement();
				valid = st.executeQuery("select accstatus from customers where id="+loginId+";");
				if(valid.next()) {
					status= valid.getString("accstatus");
				}

				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return status;
		
	}
	
	
	public int deposit(String loginId, double amount) {
			if(amount<=0) {
			System.out.println("You can not deposite zero or negative money.");
			System.out.println();
			//----exit code----//
			System.out.println("Enter 1 to exit and retry.");
			System.out.println();
			Scanner sc= new Scanner(System.in);
			int retry = sc.nextInt();
			if(retry ==1) {
				Menu m= new Menu();
				m.runMenu();
			}else {
				System.out.println("You will be redirected to main menu.");
				Menu m= new Menu();
				m.runMenu();
			}
			//---- exit code ends-----//
			
		}
			 int flag=0;
			
			 Connection con= DbService.createDBConection();
			 try {
				Statement st= con.createStatement();
				flag = st.executeUpdate("Update customers set balance = balance  + "+amount+" where id="+loginId+";");
				
				if(flag!=0) {
					
					PreparedStatement ps= con.prepareStatement("insert into transactions(cust_id, transaction_type, amount)values(?,?,?);");
					ps.setString(1, loginId);
					ps.setString(2, "Deposit");
					ps.setDouble(3, amount);
					ps.executeUpdate();
					
					
					
					Statement st2= con.createStatement();
					ResultSet rs= st2.executeQuery("select balance from customers where id="+loginId+";");
					
					System.out.println("You have deposited " + amount+ " INR successfully.");
					if(rs.next()) {
						
						System.out.println("Your closing balance is: INR " +rs.getString("balance"));
						
					}
				}
			
					
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}		
			 return flag;		
	}




	public int transfer(String loginId, int recieverId, double amount) {
		
		
		if(amount<=0 ) {
			System.out.println("You can not Transfer zero or negative money.");
			System.out.println();
			//----exit code----//
			System.out.println("Enter 1 to exit and retry.");
			System.out.println();
			Scanner sc= new Scanner(System.in);
			int retry = sc.nextInt();
			if(retry ==1) {
				Menu m= new Menu();
				m.runMenu();
				
			}else {
				System.out.println("You will be redirected to main menu.");
				Menu m= new Menu();
				m.runMenu();
			}
		}else if(amount > accBalance(loginId)) {
			
			System.out.println("You do not have sufficient much money in your account to transfer.");
			System.out.println();
			//----exit code----//
			System.out.println("Enter 1 to exit and retry.");
			System.out.println();
			Scanner sc= new Scanner(System.in);
			int retry = sc.nextInt();
			if(retry ==1) {
				Menu m= new Menu();
				m.runMenu();
			}else {
				System.out.println("You will be redirected to main menu.");
				Menu m= new Menu();
				m.runMenu();
			}
		}
		
		int flag=0;
		int check1=0;
		int check2=0;
		 Connection con= DbService.createDBConection();
		
			 try {
				Statement st= con.createStatement();
				check1= st.executeUpdate("update customers set balance = balance-" +amount+ "where id="+loginId+";");
				
				Statement st2= con.createStatement();
				check2= st2.executeUpdate("update customers set balance = balance+" +amount+ "where id="+recieverId+";");
				
				PreparedStatement p1= con.prepareStatement("insert into transactions(cust_id, transaction_type, amount, sent_to ) values (?,?,?,?)");
				p1.setString(1, loginId);
				p1.setString(2, "Sent");
				p1.setDouble(3, amount);
				p1.setInt(4, recieverId);
				p1.executeUpdate();
				
				PreparedStatement p2= con.prepareStatement("insert into transactions(cust_id, transaction_type, amount, received_from ) values (?,?,?,?)");
				p2.setInt(1, recieverId);
				p2.setString(2, "Received");
				p2.setDouble(3, amount);
				p2.setString(4, loginId);
				p2.executeUpdate();
				
				if((check1!=0) && (check2!=0)) {
					flag=1;
					
					Statement st3= con.createStatement();
					ResultSet rs= st3.executeQuery("select balance from customers where id="+loginId+";");
					
					
					System.out.println("You have Transferred " + amount+ "INR successfully.");
					if(rs.next()) {
						System.out.println("Your closing balance is: INR " +rs.getString("balance"));
					}
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			

		return flag;
	}
	

}
