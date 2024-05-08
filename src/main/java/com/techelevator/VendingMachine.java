package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.techelevator.Application.moneyProvided;
import static com.techelevator.Application.showPurchaseMenu;

public class VendingMachine {
    public static final double QUATER = 0.25;
    public static final double DIME = 0.1;
    public static final double NICKEL = 0.05;
    public static List<Product> listOfProductForSale;
    public static int quaters, dimes, nickels;
    private static double totalProfit = 0.00;

    public static double getTotalProfit() {
        return totalProfit;
    }

    public static void loadFileToInventory() {
        String filePath = "vendingmachine.csv";
        File vendingMachine = new File(filePath);
        try (Scanner fileInput = new Scanner(vendingMachine)) {
            listOfProductForSale = createListOfProducts(fileInput);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            throw new RuntimeException(e);
        }
    }

    public static List<Product> getListOfProductForSale() {
        return listOfProductForSale;
    }

    public static int getQuaters() {
        return quaters;
    }

    public static int getDimes() {
        return dimes;
    }

    public static int getNickels() {
        return nickels;
    }

    private static List<Product> createListOfProducts(Scanner fileInput) {
        String[] lineOfParameters;
        List<Product> productInVendingMachine = new ArrayList<>();

        while (fileInput.hasNextLine()) {
            String lineOfText = fileInput.nextLine();
            lineOfParameters = lineOfText.split("\\|");

            if (lineOfParameters[3].equalsIgnoreCase("Chip")) {
                productInVendingMachine.add(new Chip(lineOfParameters[0], lineOfParameters[1], Double.parseDouble(lineOfParameters[2])));
            } else if (lineOfParameters[3].equalsIgnoreCase("Candy")) {
                productInVendingMachine.add(new Candy(lineOfParameters[0], lineOfParameters[1], Double.parseDouble(lineOfParameters[2])));
            } else if (lineOfParameters[3].equalsIgnoreCase("Drink")) {
                productInVendingMachine.add(new Drink(lineOfParameters[0], lineOfParameters[1], Double.parseDouble(lineOfParameters[2])));
            } else if (lineOfParameters[3].equalsIgnoreCase("Gum")) {
                productInVendingMachine.add(new Gum(lineOfParameters[0], lineOfParameters[1], Double.parseDouble(lineOfParameters[2])));
            }
        }
        return productInVendingMachine;
    }

    public static void returnChangeToUser() {
        quaters = (int) (moneyProvided / QUATER);
        dimes = (int) (moneyProvided % QUATER / DIME);
        nickels = (int) (Math.round(((moneyProvided % QUATER % DIME) / NICKEL) * 100.0) / 100.0);
        System.out.println("Your change: $" + moneyProvided);
        System.out.println("quaters - " + quaters + ", dimes - " + dimes + ", nickels - " + nickels);

    }

    public static void selectProductInVendingMachine(String nameOfSlot) {
        boolean isCorrectSlot = false;
        for (int i = 0; i < VendingMachine.listOfProductForSale.size(); i++) {
            if (nameOfSlot.equalsIgnoreCase(VendingMachine.listOfProductForSale.get(i).getSlot())) {

                isCorrectSlot = true;

                if (VendingMachine.listOfProductForSale.get(i).getLeftItemInSlot() == 0) {
                    System.out.println("The product is currently sold out");
                    showPurchaseMenu();
                } else {
                    if (moneyProvided - VendingMachine.listOfProductForSale.get(i).getPrice() >= 0) {
                        dispensingItem(i);
                    } else {
                        System.out.println();
                        System.out.println("You don't have enough money");
                    }
                    showPurchaseMenu();
                }
            }
        }
        if (!isCorrectSlot) {
            System.out.println("The product code doesn't exist");
            showPurchaseMenu();
        }
    }

    public static void dispensingItem(int i) {
        moneyProvided -= VendingMachine.listOfProductForSale.get(i).getPrice();
        Log.logTransaction(VendingMachine.listOfProductForSale.get(i).getName() + " " + VendingMachine.listOfProductForSale.get(i).getSlot() + " $" + VendingMachine.listOfProductForSale.get(i).getPrice() + " $" + moneyProvided);
        moneyProvided = Math.round(moneyProvided * 100.0) / 100.0;
        System.out.println();
        System.out.println("Please pick up your item:");
        System.out.println();
        System.out.println("Name of product: " + VendingMachine.listOfProductForSale.get(i).getName() +
                ", cost: $" + VendingMachine.listOfProductForSale.get(i).getPrice() +
                ", the money remaining: $" + moneyProvided);
        System.out.println(VendingMachine.listOfProductForSale.get(i));
        int qty = VendingMachine.listOfProductForSale.get(i).getLeftItemInSlot() - 1;
        VendingMachine.listOfProductForSale.get(i).setLeftItemInSlot(qty);
        totalProfit += listOfProductForSale.get(i).getPrice();
    }

    public static void displayVendingMachineItems() {
        System.out.println();

        for (int i = 0; i < listOfProductForSale.size(); i++) {
            String quantity = "";
            if (listOfProductForSale.get(i).getLeftItemInSlot() == 0) {
                quantity = ", SOLD OUT";
            } else {
                quantity = ", quantity: " + listOfProductForSale.get(i).getLeftItemInSlot();
            }

            System.out.println("Name of product: " + listOfProductForSale.get(i).getName() +
                    ", price: $" + listOfProductForSale.get(i).getPrice() +
                    ", slot: " + listOfProductForSale.get(i).getSlot() +
                    quantity);
        }
    }
}
