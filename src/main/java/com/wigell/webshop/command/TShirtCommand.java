package com.wigell.webshop.command;

import com.wigell.webshop.model.product.Product;
import com.wigell.webshop.model.product.TShirt;

public class TShirtCommand implements Command {
    private final TShirt tShirt;
    private final String sleeves;
    private final String neck;

    public TShirtCommand(TShirt tShirt, String sleeves, String neck) {
        this.tShirt = tShirt;
        this.sleeves = sleeves;
        this.neck = neck;
    }

    @Override
    public void execute() {
        if (!isValidSleeves(sleeves) || !isValidNeck(neck)) {
            throw new IllegalArgumentException("Invalid sleeves or neck for TShirt.");
        }

        tShirt.setSleeves(sleeves);
        tShirt.setNeck(neck);
    }

    private boolean isValidSleeves(String sleeves) {
        return sleeves.equalsIgnoreCase("Short") || sleeves.equalsIgnoreCase("Long");
    }

    private boolean isValidNeck(String neck) {
        return neck.equalsIgnoreCase("Round") || neck.equalsIgnoreCase("V-Neck");
    }

    @Override
    public Product getProduct() {
        return tShirt;
    }
}