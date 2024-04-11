package com.client;

import java.util.Scanner;

import com.servicei.RBI;
import com.serviceimpl.SBI;

public class Test {
public static void main(String[] args) throws Exception {
		
		RBI bank=new SBI();
		
		Scanner sc=new Scanner(System.in);
		
		while(true) 
		{
			System.out.println("\n...Enter your CHOICE...\n");
			System.out.println("Press 1 to create Account");
			System.out.println("Press 2 to Display detail");
			System.out.println("Press 3 to Deposit Money");
			System.out.println("Press 4 to withdraw Money");
			System.out.println("Press 5 to check balance");
					
			int choice=sc.nextInt();
			switch(choice) 
			{
			case 1:
				bank.createAccount();
				break;
			case 2:
				bank.displayAllDetails();
				break;
			case 3:
				bank.depositMoney();
				break;
			case 4:
				bank.withdrawal();
				break;
			case 5:
				bank.balanceCheck();
				break;
			default : System.out.println("INVALID CHOICE");
				break;
			}
			
		}
	}
}