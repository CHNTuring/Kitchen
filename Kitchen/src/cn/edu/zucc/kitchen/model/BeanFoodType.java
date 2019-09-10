package cn.edu.zucc.kitchen.model;

import java.util.Set;

public class BeanFoodType {
	private int foodTypeId;
	private String foodTypeName;
	private String foodTypeDescription;
	private Set<BeanFood> foods;

	public int getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	public String getFoodTypeName() {
		return foodTypeName;
	}

	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}

	public String getFoodTypeDescription() {
		return foodTypeDescription;
	}

	public void setFoodTypeDescription(String foodTypeDescription) {
		this.foodTypeDescription = foodTypeDescription;
	}

	public Set<BeanFood> getFoods() {
		return foods;
	}

	public void setFoods(Set<BeanFood> foods) {
		this.foods = foods;
	}
}
