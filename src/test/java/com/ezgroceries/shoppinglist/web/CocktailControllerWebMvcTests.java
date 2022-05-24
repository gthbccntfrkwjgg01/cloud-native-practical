package com.ezgroceries.shoppinglist.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(CocktailController.class)
public class CocktailControllerWebMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCocktails() throws Exception{
        mockMvc.perform(
                        get("/cocktails")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cocktailId").value("23b3d85a-3928-41c0-a533-6538a71e17c4"))
                .andExpect(jsonPath("$[1].cocktailId").value("d615ec78-fe93-467b-8d26-5d26d8eab073"))
                .andExpect(jsonPath("$", hasSize(2)))
        ;
        /* body response should be:
        [{"cocktailId":"23b3d85a-3928-41c0-a533-6538a71e17c4"},{"cocktailId":"d615ec78-fe93-467b-8d26-5d26d8eab073"}]

        To test size of array: jsonPath("$", hasSize(4))
        To count members of object: jsonPath("$.*", hasSize(4))
        */
    }

}
