package com.wigell.webshop.command;

import com.wigell.webshop.model.product.Product;
import com.wigell.webshop.model.product.Skirt;

public class SkirtCommand implements Command {
    private final Skirt skirt;
    private final String waistline;
    private final String pattern;

    public SkirtCommand(Skirt skirt, String waistline, String pattern) {
        this.skirt = skirt;
        this.waistline = waistline;
        this.pattern = pattern;
    }

    @Override
    public void execute() {
        if (!isValidWaistline(waistline) || !isValidPattern(pattern)) {
            throw new IllegalArgumentException("Invalid waistline or pattern for Skirt.");
        }

        skirt.setWaistline(waistline);
        skirt.setPattern(pattern);
    }

    private boolean isValidWaistline(String waistline) {
        return waistline.equalsIgnoreCase("High") || waistline.equalsIgnoreCase("Low");
    }

    private boolean isValidPattern(String pattern) {
        return pattern.equalsIgnoreCase("Striped") || pattern.equalsIgnoreCase("Solid") || pattern.equalsIgnoreCase("Checked");
    }

    @Override
    public Product getProduct() {
        return skirt;
    }
}
