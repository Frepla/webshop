package com.wigell.webshop.command;

import com.wigell.webshop.model.product.Skirt;
import com.wigell.webshop.model.product.Product;
import com.wigell.webshop.model.observer.ProductUpdateNotifier;

public class SkirtCommand implements Command {
    private final String waistline;
    private final String pattern;
    private final ProductUpdateNotifier notifier = new ProductUpdateNotifier();

    public SkirtCommand(String waistline, String pattern) {
        this.waistline = waistline;
        this.pattern = pattern;
    }

    @Override
    public void execute(Product product) {
        if (product instanceof Skirt skirt) {
            if (!isValidWaistline(waistline) || !isValidPattern(pattern)) {
                throw new IllegalArgumentException("Invalid waistline or pattern for Skirt.");
            }
            skirt.setWaistline(waistline);
            skirt.setPattern(pattern);
            notifier.notifyObservers("Message to CEO: Skirt is ready for delivery.");
        } else {
            throw new IllegalArgumentException("Invalid product type. Expected Skirt.");
        }
    }

    private boolean isValidWaistline(String waistline) {
        return waistline.equalsIgnoreCase("High") || waistline.equalsIgnoreCase("Low");
    }

    private boolean isValidPattern(String pattern) {
        return pattern.equalsIgnoreCase("Striped") || pattern.equalsIgnoreCase("Solid") || pattern.equalsIgnoreCase("Checked");
    }
}