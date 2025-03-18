package com.wigell.webshop.builder;

import com.wigell.webshop.model.product.TShirt;

public class TShirtBuilder {
    private final TShirt tShirt = new TShirt();

    public TShirtBuilder() {
    }

    public TShirtBuilder addSize(String size) {
        if (!size.equals("Small") && !size.equals("Medium") && !size.equals("Large")) {
            throw new IllegalArgumentException("Unavailable size");
        }
        tShirt.setSize(size);
        return this;
    }

    public TShirtBuilder addMaterial(String material) {
        if (!material.equals("Cotton") && !material.equals("Polyester") && !material.equals("Wool")) {
            throw new IllegalArgumentException("Unavailable material");
        }
        tShirt.setMaterial(material);
        return this;
    }

    public TShirtBuilder addColor(String color) {
        if (!color.equals("Red") && !color.equals("Blue") && !color.equals("Black")) {
            throw new IllegalArgumentException("Unavailable color");
        }
        tShirt.setColor(color);
        return this;
    }

    public TShirt build() {
        if (tShirt.getSize() == null || tShirt.getMaterial() == null || tShirt.getColor() == null) {
            throw new IllegalStateException("TShirt must have size, material, and color set before building.");
        }
        return tShirt;
    }
}
