package cn.edu.zucc.kitchen.model;

import java.util.Set;

public class BeanFood {
	private int foodId;
	private int foodTypeId;
	private String foodName;
	private double foodPrice;
	private double foodCount;
	private String foodDescription;
	private String foodSpecifications;
	private java.sql.Blob foodImage;
	private BeanFoodType foodType;
	private Set<BeanMenuIngredient> ingredients;
	private Set<BeanOrderDetail> orderDetails;
	private Set<BeanFoodPurchase> purchase;

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	public double getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(double foodCount) {
		this.foodCount = foodCount;
	}

	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	public String getFoodSpecifications() {
		return foodSpecifications;
	}

	public void setFoodSpecifications(String foodSpecifications) {
		this.foodSpecifications = foodSpecifications;
	}

	public java.sql.Blob getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(java.sql.Blob foodImage) {
		this.foodImage = foodImage;
	}

	public BeanFoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(BeanFoodType foodType) {
		this.foodType = foodType;
	}

	public Set<BeanMenuIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<BeanMenuIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<BeanOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<BeanOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Set<BeanFoodPurchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(Set<BeanFoodPurchase> purchase) {
		this.purchase = purchase;
	}

}
