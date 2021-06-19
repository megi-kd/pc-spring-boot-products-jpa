package com.pc.product.repository;

import org.springframework.data.jpa.domain.Specification;

import com.pc.product.entity.Product;

public class ProductSpecifications {

	public static Specification<Product> hasType(Product.ProductType type) {
		return (product, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.equal(product.get("type"), type);
	}

	public static Specification<Product> hasMinPrice(Double minPrice) {
		return (product, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.greaterThanOrEqualTo(product.get("price"), minPrice);
	}

	public static Specification<Product> hasMaxPrice(Double maxPrice) {
		return (product, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.lessThanOrEqualTo(product.get("price"), maxPrice);
	}

	public static Specification<Product> hasCity(String city) {
		return (product, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.equal(product.get("city"), city);
	}

	public static Specification<Product> hasPropertyName(Product.PropertyName propertyName) {
		return (product, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.equal(product.get("propertyName"), propertyName);
	}

	public static Specification<Product> hasPropertyValueColor(String propertyColor) {
		return (product, criteriaQuery, criteriaBuilder) -> 
				criteriaBuilder.equal(product.get("propertyColor"), propertyColor);
	}
	
	public static Specification<Product> hasPropertyGbLimitMin(Integer gbLimitMin) {
		return (product, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThanOrEqualTo(product.get("propertyLimit"), gbLimitMin);
			
	}
	
	public static Specification<Product> hasPropertyGbLimitMax(Integer gbLimitMax) {
		return (product, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.lessThanOrEqualTo(product.get("propertyLimit"), gbLimitMax);
	}


}
