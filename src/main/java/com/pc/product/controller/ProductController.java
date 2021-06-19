package com.pc.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pc.product.entity.Product;
import com.pc.product.model.ProductResourceList;
import com.pc.product.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ProductResourceList getProducst(
			@RequestParam(required = false) Product.ProductType type,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) Product.PropertyName property,
			@RequestParam(value = "min_price", required = false) Double minPrice,
			@RequestParam(value = "max_price", required = false) Double maxPrice,
			@RequestParam(value="property.color", required = false) String color,
			@RequestParam(value="property.gb_limit_min", required = false) Integer gbLimitMin,
			@RequestParam(value="property.gb_limit_max", required = false) Integer gbLimitMax) {
	
		return productService.findProducts(type, minPrice, maxPrice, city, property, color, gbLimitMin, gbLimitMax);
		
	}
	
	
	

}
