package com.wigell.webshop.command;

import com.wigell.webshop.model.product.TShirt;
import com.wigell.webshop.model.product.Product;
import com.wigell.webshop.model.observer.ProductUpdateNotifier;

public class TShirtCommand implements Command {
    private final String sleeves;
    private final String neck;
    private final ProductUpdateNotifier notifier = new ProductUpdateNotifier();

    public TShirtCommand(String sleeves, String neck) {
        this.sleeves = sleeves;
        this.neck = neck;
    }

    @Override
    public void execute(Product product) {
        if (product instanceof TShirt tShirt) {
            if (!isValidSleeves(sleeves) || !isValidNeck(neck)) {
                throw new IllegalArgumentException("Invalid sleeves or neck for TShirt.");
            }

            tShirt.setSleeves(sleeves);
            tShirt.setNeck(neck);

            notifier.notifyObservers("Message to CEO: TShirt is ready for delivery.");
        } else {
            throw new IllegalArgumentException("Invalid product type. Expected TShirt.");
        }
    }

    private boolean isValidSleeves(String sleeves) {
        return sleeves.equalsIgnoreCase("Short") || sleeves.equalsIgnoreCase("Long");
    }

    private boolean isValidNeck(String neck) {
        return neck.equalsIgnoreCase("Round") || neck.equalsIgnoreCase("V-Neck");
    }
}