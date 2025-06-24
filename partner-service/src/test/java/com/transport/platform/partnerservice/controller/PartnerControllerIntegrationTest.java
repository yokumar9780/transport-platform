package com.transport.platform.partnerservice.controller;

import com.transport.platform.partnerservice.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PartnerControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // Injected by @AutoConfigureMockMvc

    @Test
    @DisplayName("POST /api/partners/buyers - Should create and return Buyer")
    void create_buyer() throws Exception {

        String json = """
                    {
                      "name": "Test Buyer",
                      "email": "buyer@test.com",
                      "organization": {
                        "id": "test-org",
                        "name": "Test Org",
                        "type": "EXTERNAL"
                      },
                      "contact": {
                        "email": "buyer@test.com",
                        "phone": "+46700000000"
                      },
                      "address": {
                        "city": "Gothenburg",
                        "country": "SE",
                        "postalCode": "41101",
                        "street": "Main Street"
                      }
                    }
                """;

        mockMvc.perform(post("/api/partners/buyers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON content
                .andExpect(jsonPath("$.id").exists()); // Assert JSON path 'id' matches
        //.andExpect(jsonPath("$.message").value("Sample Data for " + id)); // Assert JSON path 'message'
    }
}
