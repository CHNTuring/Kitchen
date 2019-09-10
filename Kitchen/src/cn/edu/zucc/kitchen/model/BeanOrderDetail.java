package cn.edu.zucc.kitchen.model;

public class BeanOrderDetail {
	private int orderDetailId;
	private int foodId;
	private int foodOrderId;
	private double count;
	private double price;
	private double discount;
	private BeanFood food;
	private BeanFoodOrder foodOrder;

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public BeanFood getFood() {
		return food;
	}

	public void setFood(BeanFood food) {
		this.food = food;
	}

	public BeanFoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(BeanFoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

}
