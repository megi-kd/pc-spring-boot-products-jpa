package com.pc.product;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pc.product.controller.ProductController;
import com.pc.product.entity.Product;
import com.pc.product.model.ProductResource;
import com.pc.product.model.ProductResourceList;
import com.pc.product.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerServiceMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	private static ProductResourceList mockResultData;

	@BeforeAll
	public static void setupResult() {

		ProductResource resource1 = new ProductResource("phone", "color:rosa", "705.00", "Larsson vägen, Malmö");
		ProductResource resource2 = new ProductResource("phone", "color:indigo", "345.00", "Glen allén, Stockholm");
		ProductResource resource3 = new ProductResource("subscription", "gb_limit:50", "636.00", "Alvina gränden, Stockholm");

		List<ProductResource> productResources = new ArrayList<>();
		productResources.add(resource1);
		productResources.add(resource2);
		productResources.add(resource3);
		mockResultData = new ProductResourceList(productResources);

	}

	@Test
	public void testGetAllProducts() throws Exception {

		when(productService.findProducts(null, null, null, null, null, null, null, null))
					.thenReturn(mockResultData);

		this.mockMvc.perform(get("/product"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.data", hasSize(3)))
					.andExpect(jsonPath("$.data[0].properties", is("color:rosa")));
		
	}

	@Test
	public void testFindProductsByType() throws Exception {

		List<ProductResource> filteredMockData = mockResultData.getData().stream()
				.filter(p -> p.getType().equals("phone")).collect(Collectors.toList());

		when(productService.findProducts(Product.ProductType.phone, null, null, null, null, null, null, null))
					.thenReturn(new ProductResourceList(filteredMockData));

		this.mockMvc.perform(get("/product?type=phone"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.data", hasSize(2)));
	}

	@Test
	public void testFindProductsByCity() throws Exception {

		List<ProductResource> filteredMockData = mockResultData.getData().stream()
				.filter(p -> p.getStoreAddress().contains("Stockholm")).collect(Collectors.toList());

		when(productService.findProducts(null, null, null, "Stockholm", null, null, null, null))
				.thenReturn(new ProductResourceList(filteredMockData));

		this.mockMvc.perform(get("/product?city=Stockholm"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.data", hasSize(2)))
					.andExpect(jsonPath("$.data[1].type", is("subscription")));
	}
	

	@Test
	public void testFindProductsByCityAndMinPrice() throws Exception {

		List<ProductResource> filteredMockData = mockResultData.getData().stream()
				.filter(p -> p.getStoreAddress().contains("Stockholm"))
				.filter(p -> Double.valueOf(p.getPrice()) >= 350)
				.collect(Collectors.toList());

		when(productService.findProducts(null, 350d, null, "Stockholm", null, null, null, null))
				.thenReturn(new ProductResourceList(filteredMockData));

		this.mockMvc.perform(get("/product").param("city", "Stockholm").param("min_price", "350"))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.data", hasSize(1)))
					.andExpect(jsonPath("$.data[0].type", is("subscription")))
					.andExpect(jsonPath("$.data[0].price", is("636.00")));
	}

}
