package com.ezgroceries.shoppinglist.resource;

public class ShoppingListNaming {

    private String name;

    public ShoppingListNaming() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingListNaming{" +
                "name='" + name + '\'' +
                '}';
    }
}
