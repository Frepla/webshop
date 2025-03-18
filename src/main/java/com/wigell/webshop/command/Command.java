package com.wigell.webshop.command;

import com.wigell.webshop.model.product.Product;

public interface Command {
    void execute();
    Product getProduct();
}
