package com.wigell.webshop.model.product;

public class Pants extends Product {
    private String fit;
    private String length;

    public Pants() {
        super("Pants", 499.0, "", "", "");
        this.fit = "";
        this.length = "";
    }

    public Pants(String size, String material, String color) {
        super("Pants", 499.0, size, material, color);
        this.fit = "";
        this.length = "";
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", fit='%s', length='%s'}", fit, length);
    }
}
