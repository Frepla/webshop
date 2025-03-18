package com.wigell.webshop.command;

import com.wigell.webshop.model.product.Pants;
import com.wigell.webshop.model.product.Product;

public class PantsCommand implements Command {
    private final Pants pants;
    private final String fit;
    private final String length;

    public PantsCommand(Pants pants, String fit, String length) {
        this.pants = pants;
        this.fit = fit;
        this.length = length;
    }

    @Override
    public void execute() {
        if (!isValidFit(fit) || !isValidLength(length)) {
            throw new IllegalArgumentException("Invalid fit or length for Pants.");
        }

        this.pants.setFit(fit);
        this.pants.setLength(length);
    }

    private boolean isValidFit(String fit) {
        return fit.equalsIgnoreCase("Slim") || fit.equalsIgnoreCase("Regular");
    }

    private boolean isValidLength(String length) {
        return length.equalsIgnoreCase("Short") || length.equalsIgnoreCase("Long");
    }

    @Override
    public Product getProduct() {
        return pants;
    }
}
