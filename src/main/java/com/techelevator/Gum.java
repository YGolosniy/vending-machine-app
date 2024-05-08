package com.techelevator;

public class Gum extends Product {
    public Gum() {
        super();
    }

    public Gum(String slot, String name, double price) {
        super(slot, name, price);
    }

    @Override
    public String toString() {
        return "Chew Chew, Yum!";
    }
}
