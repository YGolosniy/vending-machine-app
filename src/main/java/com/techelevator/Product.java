package com.techelevator;

public class Product {
    private final int MAX_NUMBER_OF_ITEM_IN_SLOT = 5;
    private String slot;
    private String name;
    private double price;
    private int leftItemInSlot;

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getLeftItemInSlot() {
        return leftItemInSlot;
    }

    public void setLeftItemInSlot(int leftItemInSlot) {
        this.leftItemInSlot = leftItemInSlot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product() {
    }

    public Product(String slot, String name, double price) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.leftItemInSlot = MAX_NUMBER_OF_ITEM_IN_SLOT;
    }
}
