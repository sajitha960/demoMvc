package com.faith.lib;

import java.util.ArrayList;
import java.util.Scanner;

import com.faith.bean.Bank;

public class BankMenu {

	// store all records
		static ArrayList<Bank> bankArrList = new ArrayList<Bank>();

		// instance variable
		static double amount;

		// Menu Driven
		public static void menu() {

			System.out.println("-------------ATM--------------");
			System.out.println("1.List all Customers\n" + "2. Add Customer\n"
					+ "3.Update Customer \n" + "4.Deleting customer \n"
					+ "5.Seach Customer \n" + "6.Deposit \n" + "7.Withdraw\n"
					+ "8.Show Balance\n" + "9.Transfer Money\n Enter your choice");

			// input from user
			Scanner sc = new Scanner(System.in);

			switch (sc.next().charAt(0)) {
			case '1':
				listAllCustomers();
				break;
			case '2':
				addCustomer();
				break;
			case '3':
				updateCustomer();
				break;
			case '4':
				deleteCustomer();
				break;
			case '5':
				searchCustomer();

				break;
			case '6':
				deposit();
				break;
			case '7':
				withdraw();
				break;
			case '8':
				showBalance();
				break;
			case '9':
				transferMoney();
				break;

			default:
				System.out.println("Invalid Input");
			}
		}

		// list all the Customers
		private static void listAllCustomers() {
			System.out.println("listing all availble customer");
			for (Bank bank : bankArrList) {
				System.out.println(bank.toString());
			}
			// Calling menu
			menu();
		}

		// Add a customer
		private static void addCustomer() {
			System.out.println("adding a customer");

			char choice = 'n';
			try {
				do {
					// creating Bank object
					Bank bank = new Bank();

					// get input
					Scanner obj = new Scanner(System.in);

					// auto generate Account Number
					bank.setAccountNumber(Bank.generateAccountNumber());

					System.out.println("Enter Customer Name:");
					bank.setCustomerName(obj.next());

					System.out.println("Enter Account Type(Savings/Fixed)");
					bank.setAccountType(obj.next());

					System.out.println("Enter Mobile number");
					bank.setMobileNo(obj.nextLong());

					System.out.println("Enter email Id");
					bank.setEmailId(obj.next());
					
					bank.setAtmPin((int) Bank.generatePin());


					// add to global arrayList
					bankArrList.add(bank);

					System.out.println("Do you want to continue(y/n):");
					choice = obj.next().charAt(0);

				} while (choice == 'y' || choice == 'Y');

			} catch (Exception e) {
				System.out.println("sorry something wrong!");
			}

			// go to menu
			menu();

		}

		// Update customer
		private static void updateCustomer() {
			System.out.println("Update Customer");

			char choiceupdate = 'n';// for continuing the loop
			long updateAccountNo; // for searching
			Scanner scUpdate = new Scanner(System.in);
			do {
				

				// user input account no for comparing
				System.out.println("Enter Account number to update");
				updateAccountNo = scUpdate.nextLong();

				// menu for update
				System.out
						.println("1.Mobile Number\n"
								+ "2.email ID\nEnter the choice which one you want to update");
				int choice = scUpdate.nextInt();

				if (choice == 1) {
					// user input -value to be updated
					System.out
							.println("Enter the Mobile number want to be update:");
					long mobileno = scUpdate.nextLong();

					for (Bank bank : bankArrList) {
						// check condition
						if (bank.getAccountNumber() == updateAccountNo) {
							bank.setMobileNo(mobileno);
							System.out.println(bank.toString());
						}
					}
				} else {
					// user input -value to be updated
					System.out.println("Enter the Email Id want to be update");
					String email = scUpdate.nextLine();
					for (Bank bank : bankArrList) {
						// check condition
						if (bank.getAccountNumber() == updateAccountNo) {
							bank.setEmailId(email);
							System.out.println(bank.toString());
						}
					}
				}

				System.out.println("Do you want to continue (y/n):");
				choiceupdate = scUpdate.next().charAt(0);

			} while (choiceupdate == 'y' || choiceupdate == 'Y');
			menu();

		}

		// delete customer
		private static void deleteCustomer() {
			System.out.println("delete customer");

			char choicedelete = 'n';
			long deleteAccountNo;
			do {
				Scanner scDelete = new Scanner(System.in);

				// user input-account no for comparing
				System.out.println("Enter Account number to delete");
				deleteAccountNo = scDelete.nextLong();
				try {
					for (Bank bank : bankArrList) {
						// check condition
						if (bank.getAccountNumber() == deleteAccountNo) {
							bankArrList.remove(bank);
							System.out.println("Deleted successfully");
						}
					}
				} catch (Exception e) {
					System.out.println();
				}
				System.out.println("Do you want to continue (y/n):");
				choicedelete = scDelete.next().charAt(0);

			} while (choicedelete == 'y' || choicedelete == 'Y');
			menu();
		}

		// searching Customer
		private static void searchCustomer() {
			System.out.println("Serach a customer");
			char choiceYN = 'y';
			int searchId;
			// search by Account number
			do {

				// search scanner
				Scanner scSearch = new Scanner(System.in);

				System.out.println("Enter Accounct number to search");
				searchId = Integer.parseInt(scSearch.nextLine());

				try {
					for (Bank bank : bankArrList) {

						if (bank.getAccountNumber() == searchId) {
							System.out.println(bank.toString());
						} else {
							System.out.println("Account number not found..!!!");
						}

						System.out.println("Do you want to continue (y/n):");
						choiceYN = scSearch.next().charAt(0);
					}
				} catch (Exception e) {
					System.out.println();
				}
			} while (choiceYN == 'y' || choiceYN == 'Y');
			menu();
		}
		
		

		// Deposit amount and update the balance
		private static void deposit() {

			Bank bank = new Bank();
			// Scanner class
			Scanner scDeposit = new Scanner(System.in);

			System.out.println("deposit the money");
			System.out.println("Enter the amount to be deposited:");
			amount = scDeposit.nextDouble();

			amount = amount + bank.getBalance();
			System.out.println("amount is deposited" + amount);

			menu();
		}



		// withdraw
		private static void withdraw() {
			System.out.println("Withdraw money");
			// Scanner class
					Scanner scWithdraw = new Scanner(System.in);
					System.out.println("Enter amount to  be withdrawn");
					double withdrawAmount = scWithdraw.nextDouble();
					
					System.out.println("Withdrawing Amount : " + withdrawAmount);
					if (amount >= withdrawAmount) {
						amount = amount - withdrawAmount;
						System.out.println("Please collect your Cash");
						//System.out.println("Current Balance : " + amount);
						showBalance();
					} else {
						System.out.println("Sorry! Insufficient Funds");
						System.out.println();
					}
			menu();
		}
		
		// show balance
			private static void showBalance() {
				System.out.println("Current Balance : " + amount);
				System.out.println();
				menu();
			}
		
		

		// transfer money
		private static void transferMoney() {
			System.out.println("money transfered");

		}

	}


