package com.pc.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResource {

	@JsonProperty("type")
	private String type;

	@JsonProperty("properties")
	private String properties;

	@JsonProperty("price")
	private String price;

	@JsonProperty("store_address")
	private String storeAddress;

	public ProductResource() {

	}

	public ProductResource(String type, String properties, String price, String storeAddress) {
		super();
		this.type = type;
		this.properties = properties;
		this.price = price;
		this.storeAddress = storeAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(" ProductResource {");
		sb.append("type=").append(type);
		sb.append(", properties='").append(properties).append("'");
		sb.append(", price=").append(price);
		sb.append(", storeAddress='").append(storeAddress).append("'");
		sb.append("}");
		return sb.toString();
	}
}
