package com.wigell.webshop.model.product;

public class Skirt extends Product {
    private String waistline;
    private String pattern;

    public Skirt() {
        super("Skirt", 299.0, "", "", "");
        this.waistline = "";
        this.pattern = "";
    }

    public Skirt(String size, String material, String color) {
        super("Skirt", 299.0, size, material, color);
        this.waistline = "";
        this.pattern = "";
    }

    public String getWaistline() {
        return waistline;
    }

    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", waistline='%s', pattern='%s'}", waistline, pattern);
    }
}