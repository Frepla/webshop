package com.wigell.webshop.service;

import com.wigell.webshop.model.order.Order;
import com.wigell.webshop.model.Receipt;
import com.wigell.webshop.model.Customer;
import com.wigell.webshop.model.product.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderService {
    private static OrderService instance;
    private final Map<Integer, Order> ordersMap;
    private final List<Receipt> receipts;

    private OrderService() {
        ordersMap = new HashMap<>();
        receipts = new ArrayList<>();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void createOrder(Customer customer) {
        String orderName = generateOrderName(customer);
        Order newOrder = new Order(customer, orderName);
        placeOrder(newOrder);
    }

    public String generateOrderName(Customer customer) {
        return "Order_" + customer.getName() + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
    }

    public void placeOrder(Order order) {
        ordersMap.put(order.getCustomer().getId(), order);
    }

    public Order getOrder(Customer customer) {
        return ordersMap.get(customer.getId());
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(ordersMap.values());
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
