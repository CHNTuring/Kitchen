package cn.edu.zucc.kitchen.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "view_foodInformation", schema = "kitchen", catalog = "")
public class ViewFoodInformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int foodId;
	private Integer foodTypeId;
	private String foodName;
	private Double foodPrice;
	private Double foodCount;
	private String foodDescription;
	private String foodSpecifications;
	private java.sql.Blob foodImage;
	private int foodPurchaseId;
	private Integer adminUserId;
	private Double purchaseCount;
	private String purchaseStatus;
	private int orderDetailId;
	private Integer foodOrderId;
	private Double count;
	private Double price;
	private Double discount;
	private int foodIngredientId;
	private Integer menuId;
	private Double ingredientCount;
	private String ingredientUnit;

	@Basic
	@Column(name = "foodId", nullable = false)
	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	@Basic
	@Column(name = "foodTypeId", nullable = true)
	public Integer getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(Integer foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

	@Basic
	@Column(name = "foodName", nullable = true, length = 20)
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	@Basic
	@Column(name = "foodPrice", nullable = true, precision = 0)
	public Double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(Double foodPrice) {
		this.foodPrice = foodPrice;
	}

	@Basic
	@Column(name = "foodCount", nullable = true, precision = 0)
	public Double getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(Double foodCount) {
		this.foodCount = foodCount;
	}

	@Basic
	@Column(name = "foodDescription", nullable = true, length = 255)
	public String getFoodDescription() {
		return foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	@Basic
	@Column(name = "foodSpecifications", nullable = true, length = 100)
	public String getFoodSpecifications() {
		return foodSpecifications;
	}

	public void setFoodSpecifications(String foodSpecifications) {
		this.foodSpecifications = foodSpecifications;
	}

	@Basic
	@Column(name = "foodImage", nullable = true)
	public java.sql.Blob getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(java.sql.Blob foodImage) {
		this.foodImage = foodImage;
	}

	@Basic
	@Column(name = "foodPurchaseId", nullable = false)
	public int getFoodPurchaseId() {
		return foodPurchaseId;
	}

	public void setFoodPurchaseId(int foodPurchaseId) {
		this.foodPurchaseId = foodPurchaseId;
	}

	@Basic
	@Column(name = "adminUserId", nullable = true)
	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	@Basic
	@Column(name = "purchaseCount", nullable = true, precision = 0)
	public Double getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(Double purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	@Basic
	@Column(name = "purchaseStatus", nullable = true, length = 10)
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	@Basic
	@Column(name = "orderDetailId", nullable = false)
	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@Basic
	@Column(name = "foodOrderId", nullable = true)
	public Integer getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(Integer foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	@Basic
	@Column(name = "count", nullable = true, precision = 0)
	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	@Basic
	@Column(name = "price", nullable = true, precision = 0)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Basic
	@Column(name = "discount", nullable = true, precision = 0)
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Basic
	@Column(name = "foodIngredientId", nullable = false)
	public int getFoodIngredientId() {
		return foodIngredientId;
	}

	public void setFoodIngredientId(int foodIngredientId) {
		this.foodIngredientId = foodIngredientId;
	}

	@Basic
	@Column(name = "menuId", nullable = true)
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Basic
	@Column(name = "ingredientCount", nullable = true, precision = 0)
	public Double getIngredientCount() {
		return ingredientCount;
	}

	public void setIngredientCount(Double ingredientCount) {
		this.ingredientCount = ingredientCount;
	}

	@Basic
	@Column(name = "ingredientUnit", nullable = true, length = 10)
	public String getIngredientUnit() {
		return ingredientUnit;
	}

	public void setIngredientUnit(String ingredientUnit) {
		this.ingredientUnit = ingredientUnit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ViewFoodInformationEntity that = (ViewFoodInformationEntity) o;
		return foodId == that.foodId && foodPurchaseId == that.foodPurchaseId && orderDetailId == that.orderDetailId
				&& foodIngredientId == that.foodIngredientId && Objects.equals(foodTypeId, that.foodTypeId)
				&& Objects.equals(foodName, that.foodName) && Objects.equals(foodPrice, that.foodPrice)
				&& Objects.equals(foodCount, that.foodCount) && Objects.equals(foodDescription, that.foodDescription)
				&& Objects.equals(foodSpecifications, that.foodSpecifications)
				&& Objects.equals(foodImage, that.foodImage) && Objects.equals(adminUserId, that.adminUserId)
				&& Objects.equals(purchaseCount, that.purchaseCount)
				&& Objects.equals(purchaseStatus, that.purchaseStatus) && Objects.equals(foodOrderId, that.foodOrderId)
				&& Objects.equals(count, that.count) && Objects.equals(price, that.price)
				&& Objects.equals(discount, that.discount) && Objects.equals(menuId, that.menuId)
				&& Objects.equals(ingredientCount, that.ingredientCount)
				&& Objects.equals(ingredientUnit, that.ingredientUnit);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(foodId, foodTypeId, foodName, foodPrice, foodCount, foodDescription,
				foodSpecifications, foodImage, foodPurchaseId, adminUserId, purchaseCount, purchaseStatus,
				orderDetailId, foodOrderId, count, price, discount, foodIngredientId, menuId, ingredientCount,
				ingredientUnit);
		return result;
	}
}
