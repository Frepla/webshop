package com.wigell.webshop.model.product;

public class TShirt extends Product {
    private String sleeves;
    private String neck;

    public TShirt() {
        super("T-Shirt", 199.0, "", "", "");
        this.sleeves = "";
        this.neck = "";
    }

    public TShirt(String size, String material, String color) {
        super("T-Shirt", 199.0, size, material, color);
        this.sleeves = "";
        this.neck = "";
    }

    public String getSleeves() {
        return sleeves;
    }

    public void setSleeves(String sleeves) {
        this.sleeves = sleeves;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", sleeves='%s', neck='%s'}", sleeves, neck);
    }
}
