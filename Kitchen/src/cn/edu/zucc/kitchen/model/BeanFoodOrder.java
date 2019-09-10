package cn.edu.zucc.kitchen.model;

import java.util.Set;

public class BeanFoodOrder {
	private int foodOrderId;
	private int userId;
	private java.sql.Timestamp deadline;
	private String address;
	private String phone;
	private String status;
	private BeanUser user;
	private Set<BeanOrderDetail> orderDetails;

	public int getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public java.sql.Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(java.sql.Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	public Set<BeanOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<BeanOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
