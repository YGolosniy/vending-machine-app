package com.techelevator;

public class Candy extends Product {
    public Candy() {
        super();
    }

    public Candy(String slot, String name, double price) {
        super(slot, name, price);
    }

    @Override
    public String toString() {
        return "Munch Munch, Yum!";
    }
}
