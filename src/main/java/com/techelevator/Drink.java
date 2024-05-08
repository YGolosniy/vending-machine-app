package com.techelevator;

public class Drink extends Product {
    public Drink() {
        super();
    }

    public Drink(String slot, String name, double price) {
        super(slot, name, price);
    }

    @Override
    public String toString() {
        return "Glug Glug, Yum!";
    }
}
