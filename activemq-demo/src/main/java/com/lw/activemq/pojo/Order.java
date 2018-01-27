package com.lw.activemq.pojo;

import java.io.Serializable;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int orderId ;
	
	private int orderName; 
	
	private String address ;
	

	public Order(int orderId, int orderName, String address) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.address = address;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderName() {
		return orderName;
	}

	public void setOrderName(int orderName) {
		this.orderName = orderName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + ", address=" + address + "]";
	}
	
	
	
}
