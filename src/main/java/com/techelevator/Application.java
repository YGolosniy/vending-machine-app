package com.techelevator;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Application {
	public static double moneyProvided = 0.00;
	static String messageYourSelection = "Your selection: ";
	static String messageIncorrectInput = "Incorrect input! Please repeat you selection!";

	public static void main(String[] args) {
		VendingMachine.loadFileToInventory();
		showMainMenu();

		boolean isExit = false;

		while (!isExit) {
			System.out.print(messageYourSelection);
			String userInput = readUserInput();

			switch (userInput) {
				case "1":
					VendingMachine.displayVendingMachineItems();
					showMainMenu();
					break;
				case "2":
					showPurchaseMenu();
					break;
				case "3":
					System.out.println("Goodbye!");
					isExit = true;
					break;
				case "4":
					reportSales();
					break;
				default:
					System.out.println(messageIncorrectInput);
					showMainMenu();
			}
		}
	}

	public static void showMainMenu() {
		System.out.println("\n*************** Main menu ***************");
		System.out.println("(1) Display Vending Machine Items\n" + "(2) Purchase\n" + "(3) Exit");
		System.out.println("*****************************************");

	}

	public static void showPurchaseMenu() {
		System.out.println("\n************* Purchase menu *************");

		System.out.println("Current Money Provided: $" + moneyProvided);
		System.out.println();

		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		System.out.println("*****************************************");

		System.out.print(messageYourSelection);
		String userInput = readUserInput();

			if (userInput.equals("1")) {
				feedMoney();
			} else if (userInput.equals("2")) {
				System.out.println();
				System.out.println("Vending Machine Items:");
				selectProduct();
			} else if (userInput.equals("3")) {
				finishTransaction();
			} else {
				System.out.println("Incorrect data!");
				showPurchaseMenu();
			}
	}

	public static void finishTransaction() {

		System.out.println("Transaction completed!");
		String logReturnChange = "GIVE CHANGE: $" + moneyProvided;
		VendingMachine.returnChangeToUser();
		moneyProvided = getMoneyProvidedAfterChange();
		logReturnChange += " $" + moneyProvided;
		System.out.println();
		//LOG FOR GIVING CHANGE
		Log.logTransaction(logReturnChange);
		showMainMenu();
	}

	public static double getMoneyProvidedAfterChange() {
		moneyProvided = Math.round((moneyProvided - VendingMachine.quaters * VendingMachine.QUATER - VendingMachine.dimes * VendingMachine.DIME - VendingMachine.nickels * VendingMachine.NICKEL) * 100.0) / 100.0;
		return moneyProvided;
	}

	public static void selectProduct() {
		VendingMachine.displayVendingMachineItems();
		System.out.println();
		System.out.println("Please enter the slot of the selected item: ");
		String nameOfSlot = readUserInput();

		VendingMachine.selectProductInVendingMachine(nameOfSlot);
	}

	public static void feedMoney() {
		System.out.println("Please insert a money:");
		double feedMoney = Double.parseDouble(readUserInput());
		addMoneyToMoneyProvided(feedMoney);
		//LOG FOR FEEDING MONEY
		Log.logTransaction("FEED MONEY: $" + feedMoney + " $" + moneyProvided);
		showPurchaseMenu();
	}

	public static double getMoneyProvided() {
		return moneyProvided;
	}

	public static void addMoneyToMoneyProvided(double feedMoney) {
		moneyProvided += feedMoney;
	}

	private static String readUserInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine().trim();
		return input;
	}

	public static void reportSales() {
		System.out.println("*** SALES REPORT CREATED ***");
		System.out.println();
		SalesReport newSalesReport = new SalesReport(VendingMachine.getListOfProductForSale(), VendingMachine.getTotalProfit());
		newSalesReport.writeReport();
		System.out.print("Would you like to view the sales Report before returning to the menu? (Y/N): ");
		String answer = readUserInput();
		if (answer.equalsIgnoreCase("y")) {
			List<String> slots = new ArrayList<>(newSalesReport.getSalesInventory().keySet());
			Collections.sort(slots);
			for (String slot : slots) {
				System.out.println(slot + " | " + newSalesReport.getSalesInventory().get(slot));
			}
			System.out.println();
			System.out.println("*** Total Sales *** $" + VendingMachine.getTotalProfit());
			System.out.println();
			showMainMenu();
		} else if (answer.equalsIgnoreCase("n")) {
			System.out.println();
			showMainMenu();
		} else {
			System.out.println("INPUT NOT VALID, USER RETURNED TO MENU");
			System.out.println();
			showMainMenu();
		}
	}

}
