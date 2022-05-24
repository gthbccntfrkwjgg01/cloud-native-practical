package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resource.ShoppingListNaming;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerWebMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateShoppingList() throws Exception{

        String newShoppingListName = "WebMvcTest_shopping-list-name";
        String inputBody = "{\"name\": \"" + newShoppingListName + "\"}";

        mockMvc.perform(
                        post("/shopping-lists")
                                .content(inputBody)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())                                      // response status = 201
                .andExpect(header().exists("Location"))                         // response header "Location" exists
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))         // contentType equals "application/json"
                .andExpect(jsonPath("$.shoppingListId").exists())           // response body should have on the root level the field "shoppingListId
                .andExpect(jsonPath("$.name").value(newShoppingListName))   // response body should have on the root level the field "name" with value the name of the new shopping list
        ;
        /* Example:
Request:
      HTTP Method = POST
      Request URI = /shopping-lists
       Parameters = {}
          Headers = [Content-Type:"application/json;charset=UTF-8", Accept:"application/json", Content-Length:"41"]
             Body = {"name": "WebMvcTest_shopping-list-name"}
    Session Attrs = {}
Response:
           Status = 201
    Error message = null
          Headers = [Location:"http://localhost/shopping-lists/62bcb031-3ee1-42fa-9dec-5b8aae98a951", Content-Type:"application/json"]
     Content type = application/json
             Body = {"shoppingListId":"62bcb031-3ee1-42fa-9dec-5b8aae98a951","name":"WebMvcTest_shopping-list-name"}
    Forwarded URL = null
   Redirected URL = http://localhost/shopping-lists/62bcb031-3ee1-42fa-9dec-5b8aae98a951
          Cookies = []
        */
    }


}
