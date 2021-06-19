package com.pc.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	public enum ProductType {
		phone, subscription
	}

	public enum PropertyName {
		color, gb_limit
	}

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Column(name = "property_name")
	@Enumerated(EnumType.STRING)
	private PropertyName propertyName;

	@Column(name = "property_color")
	private String propertyColor;

	@Column(name = "property_limit")
	private Integer propertyLimit;

	@Column(name = "price")
	private Double price;

	@Column(name = "store_address")
	private String storeAddress;

	@Column(name = "city")
	private String city;

	public Product() {
	}

	public Product(Integer id, 
			ProductType type, 
			PropertyName propertyName, 
			String propertyColor, 
			Integer propertyLimit,
			Double price, 
			String storeAddress, 
			String city) {
		super();
		this.id = id;
		this.type = type;
		this.propertyName = propertyName;
		this.propertyColor = propertyColor;
		this.propertyLimit = propertyLimit;
		this.price = price;
		this.storeAddress = storeAddress;
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public PropertyName getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(PropertyName propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyColor() {
		return propertyColor;
	}

	public void setPropertyColor(String propertyColor) {
		this.propertyColor = propertyColor;
	}

	public Integer getPropertyLimit() {
		return propertyLimit;
	}

	public void setPropertyLimit(Integer propertyLimit) {
		this.propertyLimit = propertyLimit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(" Product {");
		sb.append(" id=").append(id);
		sb.append(", type=").append(type);
		sb.append(", propertyName=").append(propertyName);
		sb.append(", propertyColor=").append(propertyColor);
		sb.append(", propertyLimit=").append(propertyLimit);
		sb.append(", price=").append(price);
		sb.append(", city=").append(city);
		sb.append(", storeAddress='").append(storeAddress).append("'");
		sb.append("}");
		return sb.toString();
	}
}
