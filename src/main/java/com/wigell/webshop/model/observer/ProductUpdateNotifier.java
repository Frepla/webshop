package com.wigell.webshop.model.observer;

import java.util.HashSet;
import java.util.Set;

public class ProductUpdateNotifier {
    private static ProductUpdateNotifier instance;
    private Set<Observer> observers = new HashSet<>();

    private ProductUpdateNotifier() {
        CEO ceo = new CEO("Wigell");
        addObserver(ceo);
    }

    public static ProductUpdateNotifier getInstance() {
        if (instance == null) {
            instance = new ProductUpdateNotifier();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void productManufactured(String productName) {
        notifyObservers("Product is being manufactured: " + productName);
    }

    public void productReady(String productName) {
        notifyObservers("Product is completed and ready for delivery: " + productName);
    }
}