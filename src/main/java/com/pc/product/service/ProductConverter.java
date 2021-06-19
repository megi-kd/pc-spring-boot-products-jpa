package com.pc.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pc.product.entity.Product;
import com.pc.product.model.ProductResource;

@Component
public class ProductConverter{

	public ProductResource toResource(Product entity) {
		ProductResource resource = new ProductResource();
		resource.setType(entity.getType().name());
		if(entity.getPropertyName() == Product.PropertyName.color) {
			resource.setProperties(entity.getPropertyName() +":" + entity.getPropertyColor());
		} else {
			resource.setProperties(entity.getPropertyName() +":" + entity.getPropertyLimit());
		}
		
		resource.setPrice(String.format("%.2f", entity.getPrice()));
		resource.setStoreAddress(entity.getStoreAddress());
		return resource;
	}
	
	public List<ProductResource> toResources(List<Product> entities) {
		List<ProductResource> result = new ArrayList<>();
		for (Product entity : entities) {
			result.add(toResource(entity));
		}

		return result;
	}

}
