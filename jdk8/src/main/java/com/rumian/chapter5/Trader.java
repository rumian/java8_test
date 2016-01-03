package com.rumian.chapter5;

public class Trader {
    private String name;
    private String city;
    
    public Trader(String n, String c) {
        this.setName(n);
        this.setCity(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public String toString() {
        return "Trader : " + this.name + " in " + this.city;
    }
}
