package com.awesomebank.app;

public class InvalidAccountTypeException extends Exception {
	public InvalidAccountTypeException() {
		super("Invalid Account Type Selected.");
	}
}