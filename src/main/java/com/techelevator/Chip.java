package com.techelevator;

public class Chip extends Product {
    public Chip() {
        super();
    }

    public Chip(String slot, String name, double price) {
        super(slot, name, price);
    }

    @Override
    public String toString() {
        return "Crunch Crunch, Yum!";
    }
}
