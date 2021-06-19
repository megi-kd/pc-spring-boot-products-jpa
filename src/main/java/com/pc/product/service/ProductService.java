package com.pc.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pc.product.entity.Product;
import com.pc.product.model.ProductResourceList;
import com.pc.product.repository.ProductRepository;
import com.pc.product.repository.ProductSpecifications;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductConverter productConverter;

	public ProductResourceList findProducts(Product.ProductType type, Double minPrice, Double maxPrice, String city,
			Product.PropertyName property, String color, Integer gbLimitMin, Integer gbLimitMax) {

		Specification<Product> specs = null;

		if (type != null) {
			specs = Specification.where(ProductSpecifications.hasType(type));
		}

		if (minPrice != null) {
			specs = specs != null ? specs.and(ProductSpecifications.hasMinPrice(minPrice))
					: Specification.where(ProductSpecifications.hasMinPrice(minPrice));
		}

		if (maxPrice != null) {
			specs = specs != null ? specs.and(ProductSpecifications.hasMaxPrice(maxPrice))
					: Specification.where(ProductSpecifications.hasMaxPrice(maxPrice));
		}

		if (StringUtils.hasLength(city)) {
			specs = specs != null ? specs.and(ProductSpecifications.hasCity(city))
					: Specification.where(ProductSpecifications.hasCity(city));
		}

		if (property != null) {
			specs = specs != null ? specs.and(ProductSpecifications.hasPropertyName(property))
					: Specification.where(ProductSpecifications.hasPropertyName(property));
		}

		if (StringUtils.hasLength(color)) {
			specs = specs != null ? specs.and(ProductSpecifications.hasPropertyValueColor(color))
					: Specification.where(ProductSpecifications.hasPropertyValueColor(color));
		}

		if (gbLimitMin != null) {
			specs = specs != null ? specs.and(ProductSpecifications.hasPropertyGbLimitMin(gbLimitMin))
					: Specification.where(ProductSpecifications.hasPropertyGbLimitMin(gbLimitMin));
		}

		if (gbLimitMax != null) {
			specs = specs != null ? specs.and(ProductSpecifications.hasPropertyGbLimitMax(gbLimitMax))
					: Specification.where(ProductSpecifications.hasPropertyGbLimitMax(gbLimitMax));
		}
		List<Product> productEntities = new ArrayList<>();
		if (specs != null) {
			productEntities = productRepository.findAll(specs);
		} else {
			productEntities = productRepository.findAll();
		}

		return new ProductResourceList(productConverter.toResources(productEntities));
	}

}
