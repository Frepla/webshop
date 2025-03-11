package com.wigell.webshop.model;

import com.wigell.webshop.model.order.Order;
import com.wigell.webshop.model.product.Pants;
import com.wigell.webshop.model.product.Product;
import com.wigell.webshop.model.product.Skirt;
import com.wigell.webshop.model.product.TShirt;
import com.wigell.webshop.service.OrderService;

import java.util.List;

public class Receipt {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private Order order;

    public Receipt() {
        this.id = idCounter++;
    }

    public Receipt(String name, Order order) {
        this();
        this.name = name;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void printReceipt() {
        System.out.println("----------- RECEIPT -----------");
        System.out.println("Receipt Name: " + name);
        System.out.println("Receipt ID: " + id);
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Address: " + order.getCustomer().getAddress());
        System.out.println("Email: " + order.getCustomer().getMail());
        System.out.println("Order Date: " + order.getOrderDate());
        System.out.println("Products in the order:");

        List<Product> products = order.getProducts();
        for (Product product : products) {
            System.out.println("Product: " + product.getName());
            System.out.println("Price: " + product.getPrice() + " SEK");

            if (product instanceof Pants) {
                Pants pants = (Pants) product;
                System.out.println("Size: " + pants.getSize());
                System.out.println("Material: " + pants.getMaterial());
                System.out.println("Color: " + pants.getColor());
                System.out.println("Fit: " + pants.getFit());
                System.out.println("Length: " + pants.getLength());
            } else if (product instanceof TShirt) {
                TShirt tShirt = (TShirt) product;
                System.out.println("Size: " + tShirt.getSize());
                System.out.println("Material: " + tShirt.getMaterial());
                System.out.println("Color: " + tShirt.getColor());
                System.out.println("Sleeves: " + tShirt.getSleeves());
                System.out.println("Neck: " + tShirt.getNeck());
            } else if (product instanceof Skirt) {
                Skirt skirt = (Skirt) product;
                System.out.println("Size: " + skirt.getSize());
                System.out.println("Material: " + skirt.getMaterial());
                System.out.println("Color: " + skirt.getColor());
                System.out.println("Waistline: " + skirt.getWaistline());
                System.out.println("Pattern: " + skirt.getPattern());
            }
        }

        double totalPrice = OrderService.getInstance().calculateTotal(order);
        System.out.println("Total Price: " + totalPrice + " SEK");
        System.out.println("-----------------------------");
    }
}