package com.wigell.webshop.model;

public class Customer {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private String address;
    private String mail;

    public Customer() {
        this.id = idCounter++;
    }

    public Customer(String name, String address, String mail) {
        this();
        this.name = name;
        this.address = address;
        this.mail = mail;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}