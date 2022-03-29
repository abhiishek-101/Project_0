package com.awesomebank.app;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
 //instance variable
	Scanner sc= new Scanner(System.in);
	DisplayHeader dh= new DisplayHeader();
	
	boolean exit;
	
	public static void main(String[] args) {
		Menu menu= new Menu();
		menu.runMenu();
	}
	public void runMenu() {
		printHeader();
		while(!exit) {
			
			mainMenu();
			int mainMenuChoice=getMainMenuInput();
			performAction(mainMenuChoice);
		
		}
	}
	private void printHeader() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|                                             |");
		System.out.println("|         Welcome to the Awesome Bank App     |");
		System.out.println("|                                             |");
		System.out.println("+---------------------------------------------+");
		
	}
	
	
	
	public void mainMenu() {
		dh.displayHeader("Please Make a selection");
		System.out.println("1. Employee Login");
		System.out.println("2. Customer Login");
		System.out.println("3. Create an Account");
		System.out.println("0. Exit");
	
	}
	
	
	private int getMainMenuInput() {
		int choice =-1;
		do {
			System.out.println("Enter your choice: ");
		try {
			choice = Integer.parseInt(sc.nextLine());
			
		}catch(NumberFormatException e) {
			System.out.println("Invalid Selection. Please Enter numbers only.");
		}
		if(choice<0|| choice >3) {
			System.out.println("choice outside of range, Please choose again!");
		}
		}while(choice<0|| choice >3);
		return choice;
		
	}
	

	private void performAction(int mainMenuchoice) {
	switch(mainMenuchoice) {
	case 0:
		System.out.println("Thank you for using our App!");
		System.exit(0);
		break;
	case 1:
		employeeLogin();
	
		break;
	case 2:
		customerLogin();
		break;
	case 3: 
		CustomerFeatures cf= new CustomerFeatures();
		try {
			cf.createAccount();
		} catch (InvalidAccountTypeException e) {
		
			e.printStackTrace();
		}
		break;
		
	default:
			System.out.println("Unknow error has occured.");
			break;
	}
		
	}
	
	//-----------------------Employee Part Starts here-----------------------//
	
	public void employeeLogin() {
		dh.displayHeader("Welcome to Employee Login");
		System.out.println("Enter Employee ID: ");
		int empId= sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Employee Password: ");
		String pwd=sc.nextLine();
		
		Employee e= new Employee();
		int check= e.employeeCredentialCheck(empId, pwd);
		if(check!=0) {
			
			System.out.println("1. View Customer Details");
			System.out.println("2. Show Pending Account Requests");
			System.out.println("3. Show all the Transactions");
			System.out.println("0. Exit");
			
			EmployeeDuties ed= new EmployeeDuties();
			
			int choice =getEmployeeInput();
			
				switch(choice) {
				case 0: 
					System.out.println("Thank you. You will be returned to main Menu.");
					runMenu();
					break;
				case 1:
					ed.showCustomers();
					break;
				case 2:
					ed.pendingReqs();
					break;
				case 3:
					ed.viewTransactions();
					break;
				default:
					System.out.println("Unknow error has occured.");
					runMenu();
					break;
					
				}
			
		}else {
			System.out.println("You have Entered worng Credentials. You will be returned back to main menu.");
			runMenu();
			
		}
	
	}
	
	//-----------------------Employee Part Ends here-----------------------//
	//-----------------------Customer Part Starts here--------------------//
	
	
	
	private void customerLogin() {
		String loginId; // did this, so that I can access loginId and password in makeDeposit and withdrawal methods.
		String password;
		dh.displayHeader("Welcome to Customer Login");
		System.out.println("Enter Your Generated Login ID: ");
		 loginId= sc.nextLine();
		
		System.out.println("Enter Password: ");
		  password= sc.nextLine();
		
		Customer c= new Customer();
		int check= c.customerCredentialCheck(loginId, password);
		if(check!=0) {
			printCustomerMenu();
			CustomerFeatures cf= new CustomerFeatures();
			
			int choice= getCustomerInput();
			
			switch(choice) {
			case 0:
				cf.logout();
				break;
			case 1:
					cf.makeDeposit(loginId);
				break;
			case 2:
				cf.makeWithdrawl(loginId);
				break;
				
			case 3:
				cf.listBalance(loginId);
				break;
			case 4:
				cf.transfer(loginId);
				break;
				
			default:
					System.out.println("Unknow error has occured.");
					runMenu();
					break;
			}
		}else {
			System.out.println("You have Entered worng Credentials. You will be returned back to main menu.");
			mainMenu();
		}	
	}
	

	private void logout() {
		System.out.println("Thank You for using our App!");
		runMenu();
		
	}
	private void printCustomerMenu() {
		dh.displayHeader("Please make a selection");
		System.out.println("1. Make a deposit");
		System.out.println("2. Make a withdrawl");
		System.out.println("3. View Account balance");
		System.out.println("4. Transfer the money");
		System.out.println("0. Logout (Pressing this will directly take you to the Main Menu.)");
		
		
		
	}	
	private int getCustomerInput() {
		int choice =-1;
		do {
			System.out.println("Enter your choice: ");
		try {
			choice = Integer.parseInt(sc.nextLine());
			
		}catch(NumberFormatException e) {
			System.out.println("Invalid Selection. Please Enter numbers only.");
		}
		if(choice<0|| choice >4) {
			System.out.println("choice outside of range, Please choose again!");
		}
		}while(choice<0|| choice >4);
		return choice;
	}
	
	
	private int getEmployeeInput() {
		int choice =-1;
		do {
			System.out.println("Enter your choice: ");
		try {
			choice = Integer.parseInt(sc.nextLine());
			
		}catch(NumberFormatException e) {
			System.out.println("Invalid Selection. Please Enter numbers only.");
		}
		if(choice<0|| choice >3) {
			System.out.println("choice outside of range, Please choose again!");
		}
		}while(choice<0|| choice >3);
		return choice;
	}
	


	

	
	
	
	
	
	
	

	

	
	
	
	
	
	
	
	
	
	
	
}
		
	
	
		
	
	
	
	
	
	
	
	
	
	
	

