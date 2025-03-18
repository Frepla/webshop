package com.wigell.webshop.builder;

import com.wigell.webshop.model.product.Pants;

public class PantsBuilder {
    private final Pants pants = new Pants();

    public PantsBuilder() {
    }

    public PantsBuilder addSize(String size) {
        if (!size.equals("Small") && !size.equals("Medium") && !size.equals("Large")) {
            throw new IllegalArgumentException("Unavailable size");
        }
        pants.setSize(size);
        return this;
    }

    public PantsBuilder addMaterial(String material) {
        if (!material.equals("Cotton") && !material.equals("Polyester") && !material.equals("Wool")) {
            throw new IllegalArgumentException("Unavailable material");
        }
        pants.setMaterial(material);
        return this;
    }

    public PantsBuilder addColor(String color) {
        if (!color.equals("Red") && !color.equals("Blue") && !color.equals("Black")) {
            throw new IllegalArgumentException("Unavailable color");
        }
        pants.setColor(color);
        return this;
    }

    public Pants build() {
        if (pants.getSize() == null || pants.getMaterial() == null || pants.getColor() == null) {
            throw new IllegalStateException("Pants must have size, material, and color set before building.");
        }
        return pants;
    }
}
