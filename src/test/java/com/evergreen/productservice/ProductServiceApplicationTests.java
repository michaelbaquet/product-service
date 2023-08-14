package com.evergreen.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.evergreen.productservice.dto.ProductRequest;
import com.evergreen.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
// @Testcontainers
@AutoConfigureMockMvc
public class ProductServiceApplicationTests {

	// @Container
	// static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	// @Autowired
	ProductRepository productRepository = new ProductRepository();

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		// dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@BeforeEach
	public void before() {
	}

	@AfterAll
    static void tearDown() {
        // Stop and remove the MongoDB container
        // mongoDBContainer.stop();
    }

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Test should pass when POST request receives HTTP 201 response and GET request returns 1 item")
	void shouldCreateProductSuccess() throws Exception {
		ProductRequest productRequest = ProductRequest.builder()
				.name("Sega Genesis")
				.description("Vintage Console")
				.price(BigDecimal.valueOf(69.99))
				.build();

		String productString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productString))
				.andExpect(status().isCreated());

		Assertions.assertEquals(1, productRepository.findAll().size());

	}

}
