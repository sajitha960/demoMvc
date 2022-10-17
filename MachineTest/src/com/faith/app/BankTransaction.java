package com.faith.app;

import java.util.Scanner;

import com.faith.lib.BankMenu;

public class BankTransaction {

	public static void main(String[] args) {
		mainMenu();
	}
	
	public static void mainMenu() {

		System.out.println("Welcome to Bank Transactions App");
		System.out.println("1.Use Atm\n2.Exit");

		// input from the user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice:");
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			BankMenu.menu();
			break;
		case 2:
			System.out.println("Thanks for using Bank App");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid input");
		}

	}

}
