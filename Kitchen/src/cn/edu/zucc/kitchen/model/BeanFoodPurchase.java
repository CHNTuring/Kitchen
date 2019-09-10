package cn.edu.zucc.kitchen.model;

public class BeanFoodPurchase {
	private int foodPurchaseId;
	private int adminUserId;
	private int foodId;
	private double purchaseCount;
	private String purchaseStatus;
	private BeanAdminUser adminUser;
	private BeanFood food;

	public int getFoodPurchaseId() {
		return foodPurchaseId;
	}

	public void setFoodPurchaseId(int foodPurchaseId) {
		this.foodPurchaseId = foodPurchaseId;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public double getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(double purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public BeanAdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(BeanAdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public BeanFood getFood() {
		return food;
	}

	public void setFood(BeanFood food) {
		this.food = food;
	}

}
