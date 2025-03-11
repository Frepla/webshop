package com.wigell.webshop.builder;

import com.wigell.webshop.model.product.Skirt;

public class SkirtBuilder {
    private Skirt skirt = new Skirt();

    public SkirtBuilder() {
    }

    public SkirtBuilder addSize(String size) {
        if (!size.equals("Small") && !size.equals("Medium") && !size.equals("Large")) {
            throw new IllegalArgumentException("Unavailable size");
        }
        skirt.setSize(size);
        return this;
    }

    public SkirtBuilder addMaterial(String material) {
        if (!material.equals("Cotton") && !material.equals("Polyester") && !material.equals("Wool")) {
            throw new IllegalArgumentException("Unavailable material");
        }
        skirt.setMaterial(material);
        return this;
    }

    public SkirtBuilder addColor(String color) {
        if (!color.equals("Red") && !color.equals("Blue") && !color.equals("Black")) {
            throw new IllegalArgumentException("Unavailable color");
        }
        skirt.setColor(color);
        return this;
    }

    public Skirt build() {
        if (skirt.getSize() == null || skirt.getMaterial() == null || skirt.getColor() == null) {
            throw new IllegalStateException("Skirt must have size, material, and color set before building.");
        }
        return skirt;
    }
}