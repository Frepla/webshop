package com.wigell.webshop.model.observer;

import java.util.ArrayList;
import java.util.List;

public class ProductUpdateNotifier {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
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