package com.wigell.webshop.view;

import com.wigell.webshop.model.Customer;
import com.wigell.webshop.model.order.Order;
import com.wigell.webshop.model.Receipt;
import com.wigell.webshop.service.OrderService;
import com.wigell.webshop.controller.OrderController;

import java.util.List;
import java.util.Scanner;

public class MenuView {
    private static final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService = OrderService.getInstance();

    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Wigell Webshop Menu ===");
            System.out.println("1. Create Order");
            System.out.println("2. View Orders");
            System.out.println("3. Print Receipts");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clearScreen();
                    createOrder();
                    break;
                case 2:
                    clearScreen();
                    viewOrders();
                    break;
                case 3:
                    clearScreen();
                    printReceipts();
                    break;
                case 4:
                    running = false;
                    clearScreen();
                    System.out.println("Exiting Webshop. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createOrder() {
        System.out.println("\nEnter customer details:");

        String name = getValidatedInput("Name (First and last): ", "^[A-Za-z]+ [A-Za-z]+$", "Invalid name. Please enter your first and last name.");
        String address = getValidatedInput("Address (Street and number): ", "^[A-Za-zåäöÅÄÖ]+(?: [A-Za-zåäöÅÄÖ]+)* \\d+$", "Invalid address. Please enter your address in the format 'Street Name House Number'.");
        String email = getValidatedInput("Email (name@mail.com): ", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "Invalid email format.");

        Customer customer = new Customer(name, address, email);

        orderService.createOrder(customer);

        OrderController orderController = new OrderController(customer);
        orderController.addProductsToOrder();

        System.out.println("Order successfully created for " + name);
    }

    private String getValidatedInput(String prompt, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    private void viewOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("\nNo orders available.");
            return;
        }

        System.out.println("\nList of Orders:");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". Order ID: " + order.getId() + " | Customer: " + order.getCustomer().getName()
                    + " | Order Date: " + order.getOrderDate());
        }
    }

    private void printReceipts() {
        List<Order> orders = orderService.getAllOrders();
        List<Receipt> receipts = orderService.getAllReceipts();
        if (receipts.isEmpty()) {
            System.out.println("\nNo receipts available to print.");
            return;
        }

        System.out.println("\nChoose an order to print a receipt:");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". Order ID: " + order.getId() + " | Customer: " + order.getCustomer().getName()
                    + " | Order Date: " + order.getOrderDate());
        }

        int choice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.print("Enter the receipt number to print: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > receipts.size()) {
                System.out.println("Invalid choice. Please try again.");
            } else {
                validChoice = true;
            }
        }

        Receipt selectedReceipt = receipts.get(choice - 1);
        selectedReceipt.printReceipt();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
