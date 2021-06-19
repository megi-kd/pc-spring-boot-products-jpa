package com.pc.product;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllProducts() throws Exception {
		this.mockMvc.perform(get("/product"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(100)))
				.andExpect(jsonPath("$.data[0].type", is("phone")))
				.andExpect(jsonPath("$.data[0].price", equalTo("277.00")));

	}
	
	@Test
	public void testFindProductsByTypeCityAndMaxPrice() throws Exception {
		this.mockMvc.perform(get("/product?type=subscription&city=Stockholm&max_price=1000"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(25)))
				.andExpect(jsonPath("$.data[?(@.price > 1000)]", empty())) //there is no product which price is more than 1000
				.andExpect(jsonPath("$.data[1].properties", is("gb_limit:50")));

	}

	@Test
	public void testFindProductsByTypeCityAndMinPrice() throws Exception {
		this.mockMvc.perform(get("/product?type=subscription&city=Stockholm&min_price=700"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(9)))
				.andExpect(jsonPath("$.data[?(@.price < 700)]",  empty())) //there is no product which price is less than 700
				.andExpect(jsonPath("$.data[1].properties", is("gb_limit:50")));

	}
	
	@Test
	public void testFindProductsByTypeAndProperty() throws Exception {
		this.mockMvc.perform(get("/product?type=subscription&property=gb_limit"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(58)))
				.andExpect(jsonPath("$.data[0].properties", is("gb_limit:50")));

	}
	
	@Test
	public void testFindProductsByTypeAndPropertyGbLimitMin() throws Exception {
		this.mockMvc.perform(get("/product?type=subscription&property.gb_limit_min=30"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data", hasSize(34)))
				.andExpect(jsonPath("$.data[?(@.properties == 'gb_limit:50')]", hasSize(34)))
				.andExpect(jsonPath("$.data[?(@.properties == 'gb_limit:10')]", empty()));

	}

}
