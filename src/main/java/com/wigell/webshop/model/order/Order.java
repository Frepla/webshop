package com.wigell.webshop.model.order;

import com.wigell.webshop.command.Command;
import com.wigell.webshop.model.Customer;
import com.wigell.webshop.model.product.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private Customer customer;
    private List<Product> products;
    private List<Command> commands;
    private final LocalDate orderDate;
    private boolean isCompleted;

    public Order() {
        this.id = idCounter++;
        this.products = new ArrayList<>();
        this.orderDate = LocalDate.now();
        this.isCompleted = false;
    }

    public Order(Customer customer, String name) {
        this();
        this.customer = customer;
        this.name = name;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customer=" + customer.getName() +
                ", productsCount=" + products.size() +
                ", orderDate=" + orderDate +
                ", isCompleted=" + isCompleted +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}