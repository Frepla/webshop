package com.wigell.webshop.model.product;

public abstract class Product {
    private static int idCounter = 1;

    private final int id;
    private String name;
    private double price;
    private String size;
    private String material;
    private String color;

    public Product() {
        this.id = idCounter++;
        this.name = "";
        this.price = 0.0;
        this.size = "";
        this.material = "";
        this.color = "";
    }

    protected Product(String name, double price, String size, String material, String color) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.size = size;
        this.material = material;
        this.color = color;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Product.idCounter = idCounter;
    }

    public int getId() {
        return id;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s{id=%d, name='%s', price=%.2f, size='%s', material='%s', color='%s'}",
                getClass().getSimpleName(), id, name, price, size, material, color);
    }
}