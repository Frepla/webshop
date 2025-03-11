package com.wigell.webshop.view;

import com.wigell.webshop.model.Customer;
import com.wigell.webshop.model.order.Order;
import com.wigell.webshop.model.Receipt;
import com.wigell.webshop.service.OrderService;
import com.wigell.webshop.controller.OrderController;

import java.time.LocalDate;
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

        String name = getValidatedInput("Name (First Last): ", "^[A-Za-z]+ [A-Za-z]+$", "Invalid name. Please enter your first and last name.");
        String address = getValidatedInput("Address (Street Name House Number): ", "^[A-Za-zåäöÅÄÖ]+(?: [A-Za-zåäöÅÄÖ]+)* \\d+$", "Invalid address. Please enter your address in the format 'Street Name House Number'.");
        String email = getValidatedInput("Email (name@mail.com): ", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", "Invalid email format.");

        Customer customer = new Customer(name, address, email);

        String orderName = name + " - " + LocalDate.now();

        Order newOrder = new Order(customer, orderName);
        orderService.placeOrder(newOrder);

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
            System.out.println((i + 1) + ". " + orders.get(i).getCustomer().getName() + " - " + orders.get(i).getCustomer().getMail());
        }
    }

    private void printReceipts() {
        List<Receipt> receipts = orderService.getAllReceipts();
        if (receipts.isEmpty()) {
            System.out.println("\nNo receipts available to print.");
            return;
        }

        System.out.println("\nChoose an order to print a receipt:");

        for (int i = 0; i < receipts.size(); i++) {
            System.out.println((i + 1) + ". " + receipts.get(i).getOrder().getCustomer().getName() + " - " + receipts.get(i).getOrder().getCustomer().getMail());
        }

        System.out.print("Enter the receipt number to print: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > receipts.size()) {
            System.out.println("Invalid choice. Please try again.");
        } else {
            Receipt selectedReceipt = receipts.get(choice - 1);
            selectedReceipt.printReceipt();
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}