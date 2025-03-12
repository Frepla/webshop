package com.wigell.webshop.model.observer;

public class CEO implements Observer {
    private String name;
    private static int idCounter = 1;
    private final int id;

    public CEO() {
        this.id = idCounter++;
    }

    public CEO(String name) {
        this.name = name;
        this.id = idCounter++;
    }

    @Override
    public void update(String message) {
        System.out.println("<< Message sent to " + name + " (CEO): " + message + ">>");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CEO{id=" + id + ", name='" + name + "'}";
    }
}