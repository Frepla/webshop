package com.wigell.webshop.service;

import com.wigell.webshop.model.order.Order;
import com.wigell.webshop.model.Receipt;
import com.wigell.webshop.model.Customer;
import com.wigell.webshop.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final OrderService instance = new OrderService();
    private final List<Order> orders;
    private final List<Receipt> receipts;

    private OrderService() {
        orders = new ArrayList<>();
        receipts = new ArrayList<>();
    }

    public static OrderService getInstance() {
        return instance;
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public Order getOrder(Customer customer) {
        for (Order order : orders) {
            if (order.getCustomer().getId() == customer.getId()) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void addProductToOrder(Order order, Product product) {
        order.addProduct(product);
    }

    public double calculateTotal(Order order) {
        double total = 0.0;
        for (Product product : order.getProducts()) {
            total += product.getPrice();
        }
        return total;
    }

    public Receipt getReceiptForOrder(Order order) {
        for (Receipt receipt : receipts) {
            if (receipt.getOrder().equals(order)) {
                return receipt;
            }
        }
        return null;
    }

    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    public List<Receipt> getAllReceipts() {
        return receipts;
    }
}