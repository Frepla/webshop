package com.wigell.webshop.command;

import com.wigell.webshop.model.product.Pants;
import com.wigell.webshop.model.product.Product;
import com.wigell.webshop.model.observer.ProductUpdateNotifier;

public class PantsCommand implements Command {
    private final String fit;
    private final String length;
    private final ProductUpdateNotifier notifier = new ProductUpdateNotifier();

    public PantsCommand(String fit, String length) {
        this.fit = fit;
        this.length = length;
    }

    @Override
    public void execute(Product product) {
        if (product instanceof Pants pants) {
            if (!isValidFit(fit) || !isValidLength(length)) {
                throw new IllegalArgumentException("Invalid fit or length for Pants.");
            }

            pants.setFit(fit);
            pants.setLength(length);
            notifier.notifyObservers("Message to CEO: Pants are ready for delivery.");
        } else {
            throw new IllegalArgumentException("Invalid product type. Expected Pants.");
        }
    }

    private boolean isValidFit(String fit) {
        return fit.equalsIgnoreCase("Slim") || fit.equalsIgnoreCase("Regular");
    }

    private boolean isValidLength(String length) {
        return length.equalsIgnoreCase("Short") || length.equalsIgnoreCase("Long");
    }
}