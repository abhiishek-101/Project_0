package com.awesomebank.app;

public class DisplayHeader {

	public void  displayHeader(String message) {
		System.out.println();
		int width= message.length() + 6;
		StringBuilder sb= new StringBuilder();
		sb.append("+");
		for(int i=0; i<width;++i) {
			sb.append("-");
		}
		sb.append("+");
		System.out.println(sb.toString());
		System.out.println("|   "+message+"   |");
		System.out.println(sb.toString());
	}
}
