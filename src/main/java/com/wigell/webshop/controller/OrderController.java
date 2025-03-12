package com.wigell.webshop.controller;

import com.wigell.webshop.model.Customer;
import com.wigell.webshop.model.product.*;
import com.wigell.webshop.model.order.Order;
import com.wigell.webshop.builder.PantsBuilder;
import com.wigell.webshop.builder.TShirtBuilder;
import com.wigell.webshop.builder.SkirtBuilder;
import com.wigell.webshop.command.*;
import com.wigell.webshop.model.Receipt;
import com.wigell.webshop.service.OrderService;
import com.wigell.webshop.model.observer.ProductUpdateNotifier;
import com.wigell.webshop.model.observer.CEO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderController {
    private static final Scanner scanner = new Scanner(System.in);
    private Order currentOrder;
    private final OrderService orderService;
    private final ProductUpdateNotifier productUpdateNotifier;

    public OrderController(Customer customer) {
        this.orderService = OrderService.getInstance();
        this.currentOrder = orderService.getOrder(customer);
        if (this.currentOrder == null) {
            String orderName = orderService.generateOrderName(customer);
            this.currentOrder = new Order(customer, orderName);
            orderService.placeOrder(currentOrder);
        }

        this.productUpdateNotifier = new ProductUpdateNotifier();
        CEO ceo = new CEO("Wigell");
        productUpdateNotifier.addObserver(ceo);
    }

    public void addProductsToOrder() {
        System.out.println("\nCreating a new order for " + currentOrder.getCustomer().getName());
        boolean addMoreProducts = true;

        while (addMoreProducts) {
            System.out.println("\nChoose a product to order:");
            System.out.println("1. Pants");
            System.out.println("2. T-shirt");
            System.out.println("3. Skirt");
            System.out.println("4. Finish order");
            System.out.print("Enter your choice: ");
            int productChoice = getValidChoice(1, 4);

            if (productChoice == 4) {
                System.out.println("Finishing the order.");
                addMoreProducts = false;
            } else {
                processProductChoice(productChoice);
            }
        }
        System.out.println("\nThank you for your order! Generating receipt...");
        sleepWithHandling();
        clearScreen();
        printReceipt();
    }

    private void processProductChoice(int productChoice) {
        String size = getProductInput("Choose size (1. Small, 2. Medium, 3. Large): ", 1, 3, new String[]{"Small", "Medium", "Large"});
        String material = getProductInput("Choose material (1. Cotton, 2. Polyester, 3. Wool): ", 1, 3, new String[]{"Cotton", "Polyester", "Wool"});
        String color = getProductInput("Choose color (1. Red, 2. Blue, 3. Black): ", 1, 3, new String[]{"Red", "Blue", "Black"});

        switch (productChoice) {
            case 1:
                processPants(size, material, color);
                break;
            case 2:
                processTShirt(size, material, color);
                break;
            case 3:
                processSkirt(size, material, color);
                break;
            default:
                System.out.println("Invalid product choice.");
        }

        System.out.println("Product added to your order.");
    }

    private void processPants(String size, String material, String color) {
        Pants pants = new PantsBuilder()
                .addSize(size)
                .addMaterial(material)
                .addColor(color)
                .build();

        productUpdateNotifier.productManufactured("Pants");
        sleepWithHandling();

        String fit = getProductInput("Choose fit (1. Slim, 2. Regular): ", 1, 2, new String[]{"Slim", "Regular"});
        String length = getProductInput("Choose length (1. Short, 2. Long): ", 1, 2, new String[]{"Short", "Long"});

        orderService.addProductToOrder(currentOrder, pants);

        Command pantsCommand = new PantsCommand(fit, length);
        pantsCommand.execute(pants);

        productUpdateNotifier.productReady("Pants.");
        sleepWithHandling();
    }

    private void processTShirt(String size, String material, String color) {
        TShirt tShirt = new TShirtBuilder()
                .addSize(size)
                .addMaterial(material)
                .addColor(color)
                .build();

        productUpdateNotifier.productManufactured("TShirt");
        sleepWithHandling();

        String sleeves = getProductInput("Choose sleeves (1. Short, 2. Long): ", 1, 2, new String[]{"Short", "Long"});
        String neck = getProductInput("Choose neck (1. Round, 2. V-neck): ", 1, 2, new String[]{"Round", "V-neck"});

        orderService.addProductToOrder(currentOrder, tShirt);

        Command tShirtCommand = new TShirtCommand(sleeves, neck);
        tShirtCommand.execute(tShirt);

        productUpdateNotifier.productReady("TShirt");
        sleepWithHandling();
    }

    private void processSkirt(String size, String material, String color) {
        Skirt skirt = new SkirtBuilder()
                .addSize(size)
                .addMaterial(material)
                .addColor(color)
                .build();

        productUpdateNotifier.productManufactured("Skirt");
        sleepWithHandling();

        String waistline = getProductInput("Choose waistline (1. High, 2. Low): ", 1, 2, new String[]{"High", "Low"});
        String pattern = getProductInput("Choose pattern (1. Striped, 2. Solid): ", 1, 2, new String[]{"Striped", "Solid"});

        orderService.addProductToOrder(currentOrder, skirt);

        Command skirtCommand = new SkirtCommand(waistline, pattern);
        skirtCommand.execute(skirt);

        productUpdateNotifier.productReady("Skirt");
        sleepWithHandling();
    }

    private String getProductInput(String prompt, int min, int max, String[] options) {
        System.out.print(prompt);
        int choice = getValidChoice(min, max);
        return options[choice - 1];
    }

    public void printReceipt() {
        if (currentOrder.getProducts() == null || currentOrder.getProducts().isEmpty()) {
            System.out.println("\nNo order placed.");
            return;
        }

        Receipt existingReceipt = orderService.getReceiptForOrder(currentOrder);

        if (existingReceipt == null) {
            String receiptName = "Receipt_" + currentOrder.getCustomer().getName() + "_" + currentOrder.getId() + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            existingReceipt = new Receipt(receiptName, currentOrder);
            orderService.addReceipt(existingReceipt);
        }

        existingReceipt.printReceipt();
    }

    private int getValidChoice(int min, int max) {
        int choice;
        while (true) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= min && choice <= max) {
                break;
            } else {
                System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
            }
        }
        return choice;
    }

    private void sleepWithHandling() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
