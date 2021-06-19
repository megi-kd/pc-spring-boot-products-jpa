package com.pc.product.model;

import java.util.List;

public class ProductResourceList {

	private List<ProductResource> data;

	public ProductResourceList() {

	}

	public ProductResourceList(List<ProductResource> data) {
		super();
		this.data = data;
	}

	public List<ProductResource> getData() {
		return data;
	}

	public void setData(List<ProductResource> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("data =" + data);
		return sb.toString();
	}

}
