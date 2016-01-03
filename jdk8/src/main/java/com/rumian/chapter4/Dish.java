package com.rumian.chapter4;

public class Dish {
    public enum Type {
        MEAT, FISH, OTHER
    }
    private String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    
    public Dish(String name, boolean vergetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vergetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }
    
    @Override
    public String toString() {
        //return name;
        return new StringBuilder()
                .append(name).append(" : ")
                .append(vegetarian).append(" : ")
                .append(calories).append(" : ")
                .append(type).toString();
    }
}
