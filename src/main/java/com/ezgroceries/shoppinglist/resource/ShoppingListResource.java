package com.ezgroceries.shoppinglist.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;

    @JsonIgnore
    private List<CocktailResource> cocktails;

    public ShoppingListResource(String name) {
        this.setShoppingListId(UUID.randomUUID());
        this.name = name;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CocktailResource> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailResource> cocktails) {
        this.cocktails = cocktails;
    }

    @Override
    public String toString() {
        return "ShoppingListResource{" +
                "shoppingListId=" + shoppingListId +
                ", name='" + name + '\'' +
                ", cocktails=" + cocktails +
                '}';
    }
}

