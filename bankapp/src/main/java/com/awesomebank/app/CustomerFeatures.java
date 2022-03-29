package com.awesomebank.app;

import java.util.Scanner;

public class CustomerFeatures {
	
	DisplayHeader dh= new DisplayHeader();
	Account account= new Account();
	Scanner sc= new Scanner(System.in);

	
	public String getaccountType() {
		String accountType="";
		boolean valid= false;
		while(!valid) {
			accountType= askQuestion("Please enter an account type: (current/savings) ");
			
			if(accountType.equalsIgnoreCase("current")|| accountType.equalsIgnoreCase("savings")) {
				valid = true;
				
			}else {
				System.out.println("Invalid account type entered. Please Enter Current or Savings");
				
			}
		}
		return accountType;
	}

	
	public double getDeposit(String accountType) {
		double initialDeposit=0;
		Boolean valid=false;
		while(!valid) {
			System.out.print("Please Enter an initial Deposite: ");
			try {
				initialDeposit= Double.parseDouble(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Deposit must be a number");
			}
			if(accountType.equalsIgnoreCase("current")) {
				if(initialDeposit<1000) {
					System.out.println("Current accounts require a minimum of 1000 INR to open.");
					
				}else {
					valid= true;
				}
			}else if(accountType.equalsIgnoreCase("savings")) {
				if(initialDeposit<500) {
					System.out.println("Savings accounts require a minimum of 500 INR to open.");
					
				}else {
					valid= true;
				}
			}
		}
		return initialDeposit;
	}

	public String askQuestion(String question) {
		String answer="";
		Scanner input= new Scanner (System.in); // took a new scanner to prevent the corruption of data.
		System.out.print(question);
		answer= input.nextLine();
		return answer;
	}
	
	public void createAccount() throws InvalidAccountTypeException {
		dh.displayHeader("Create an Account");
		
		CustomerApplication ca=new CustomerApplication();
		
		String accType= getaccountType();
		ca.setAccType(accType);
		
		String firstName =askQuestion("Please Enter your First name: ");
		ca.setFirstName(firstName);
		
		String lastName =askQuestion("Please Enter your Last name: ");
		ca.setLastName(lastName);
		
		String pan =askQuestion("Please Enter your PAN: ");
		ca.setPan(pan);
		
		String password = askQuestion("Enter a Password: ");
		ca.setPassword(password);
		
		double intialDeposit= getDeposit(accType);
		ca.setIntialDeposit(intialDeposit);
		
		
		AccountApplication accApp= new AccountApplication();
		int applied = accApp.application(ca); //input has been passed by reference.
		
		if(applied!=0) {
			System.out.println();
			System.out.println("Your Account has been successfully created. Wait Until your account gets active.");
		}else {
			System.out.println("Account creation failed!");
			System.out.println("Press 1 to enter the details again.");
			int retry = sc.nextInt();
			if(retry ==1) {
				createAccount();
			}else {
				System.out.println("You have entered Wrong choice. You will be redirected to main menu.");
				Menu m= new Menu();
				m.runMenu();
			}
		}
	}
	
	
	
	public void listBalance(String loginId) {
		String isCredible=account.checkCredibility(loginId);
		if(isCredible.equalsIgnoreCase("active")) {
			
			dh.displayHeader("Account Details");
			double balance =account.accBalance(loginId);
			
			dh.displayHeader("Your balance is: "+balance+ " INR");
			
		}else {
			System.out.println("You can not use this feature because your Account is not approved yet. Wait until our Employee approves it. ");
			logout();
		}
		}
	
	public int getAmount(String question) {
		System.out.println(question);
		int amount= 0;
		try {
			amount = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e){
			amount= 0;
		}
		return amount;
	}
	
	
	public void makeDeposit(String loginId) {
		
		String isCredible=account.checkCredibility(loginId);
		
		if(isCredible.equalsIgnoreCase("active")) {
			dh.displayHeader("Make a Deposit");
			
			int amount=getAmount("How much would you like to deposit?");
			
		
			 account.deposit(loginId, amount);
		}else {
			System.out.println("You can not use this feature because your Account is not approved yet. Wait until our Employee approves it. ");
			logout();
		}
	
		
		
	}
	
	public void makeWithdrawl(String loginId) {
		String isCredible=account.checkCredibility(loginId);
		if(isCredible.equalsIgnoreCase("active")) {
			
			dh.displayHeader("Make a Withdraw");
			
			double amount=getAmount("How much would you like to withdraw?");
			account.withdraw(loginId, amount);
		}else {
			System.out.println("You can not use this feature because your Account is not approved yet. Wait until our Employee approves it. ");
			logout();
		}
		}
	


	public void transfer(String loginId) {
		String isCredible=account.checkCredibility(loginId);
		if(isCredible.equalsIgnoreCase("active")) {
			
			dh.displayHeader("Transfer Money");
			
			int recieverId = getId("Customer ID of the person, you want to transfer money into: ");
			
			
			double amount = getAmount("How much would you like to transfer?");
			
			
			account.transfer(loginId, recieverId, amount);
		}else {
			System.out.println("You can not use this feature because your Account is not approved yet. Wait until our Employee approves it. ");
			logout();
		}
		
		
		
	}
	 public int getId(String question) {
	 System.out.println(question);
		int id= 0;
		try {
			id = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e){
			id= 0;
		}
		return id;
	 }

	public void logout() {
		Menu m= new Menu();
		m.runMenu();
		
	}
	

}

