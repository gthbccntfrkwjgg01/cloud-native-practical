package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resource.CocktailResource;
import com.ezgroceries.shoppinglist.resource.ShoppingListNaming;
import com.ezgroceries.shoppinglist.resource.ShoppingListResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;


@RestController
public class ShoppingListController {

    Map<UUID, ShoppingListResource> shoppingListMap = new HashMap<>();


    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<ShoppingListResource> createShoppingList(@RequestBody ShoppingListNaming shoppingListName) {
        System.out.println(shoppingListName.toString());

        ShoppingListResource shoppingListResource = new ShoppingListResource(shoppingListName.getName());
        System.out.println(shoppingListResource.toString());

        shoppingListMap.put(shoppingListResource.getShoppingListId(),shoppingListResource);
        System.out.println(shoppingListMap.toString());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{resourceId}")
                .buildAndExpand(shoppingListResource.getShoppingListId())
                .toUri();

        return ResponseEntity.created(location).body(shoppingListResource);
    }

    @PostMapping(value = "/shopping-lists/{uuid}/cocktails")
    //public ResponseEntity <CocktailResource> addCocktailsToShoppingList(@PathVariable UUID uuid, @RequestBody List<CocktailResource> cocktailResourceList) {
    public ResponseEntity <List<CocktailResource>> addCocktailsToShoppingList(@PathVariable UUID uuid, @RequestBody List<CocktailResource> cocktailResourceList) {
        System.out.println(cocktailResourceList.toString());

        ShoppingListResource shoppingListResource = shoppingListMap.get(uuid);
        shoppingListResource.setCocktails(cocktailResourceList);
        System.out.println(shoppingListResource.toString());
        System.out.println(shoppingListMap.toString());

        return ResponseEntity.ok().body(shoppingListResource.getCocktails());
    }


    @GetMapping(value = "/shopping-lists/{uuid}")
    public ResponseEntity<ShoppingListResource> getShoppingList(@PathVariable UUID uuid) {
        System.out.println(uuid);

        ShoppingListResource shoppingListResource = shoppingListMap.get(uuid);
        System.out.println(shoppingListResource.toString());

        return ResponseEntity.ok().body(shoppingListResource);
    }

    @GetMapping(value = "/shopping-lists")
    public ResponseEntity<List<ShoppingListResource>> getAllShoppingLists() {

        List<ShoppingListResource> shoppingListResourceList = new ArrayList<ShoppingListResource>(shoppingListMap.values());

        System.out.println(shoppingListResourceList.toString());

        return ResponseEntity.ok().body(shoppingListResourceList);
    }

}
