package cn.edu.zucc.kitchen.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;
import java.io.Serializable;

@Entity
@Table(name = "view_menuinformation", schema = "kitchen", catalog = "")
public class ViewMenuinformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int menuId;
	private Integer userId;
	private String menuName;
	private String menuDescription;
	private Double menuScoreCount;
	private Integer menuCollectedCount;
	private Integer menuBrowseCount;
	private java.sql.Blob menuImage;
	private int foodIngredientId;
	private Integer foodId;
	private Double ingredientCount;
	private String ingredientUnit;
	private int menuAssessmentId;
	private String menuAssessmentContent;
	private boolean isBrowsed;
	private boolean isCollected;
	private Double menuScore;
	private int menuStepId;
	private String menuStepDescription;
	private Integer menuStepOrderId;
	private java.sql.Blob menuStepImage;

	@Basic
	@Column(name = "menuId", nullable = false)
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Basic
	@Column(name = "userId", nullable = true)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "menuName", nullable = true, length = 20)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Basic
	@Column(name = "menuDescription", nullable = true, length = 255)
	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	@Basic
	@Column(name = "menuScoreCount", nullable = true, precision = 0)
	public Double getMenuScoreCount() {
		return menuScoreCount;
	}

	public void setMenuScoreCount(Double menuScoreCount) {
		this.menuScoreCount = menuScoreCount;
	}

	@Basic
	@Column(name = "menuCollectedCount", nullable = true)
	public Integer getMenuCollectedCount() {
		return menuCollectedCount;
	}

	public void setMenuCollectedCount(Integer menuCollectedCount) {
		this.menuCollectedCount = menuCollectedCount;
	}

	@Basic
	@Column(name = "menuBrowseCount", nullable = true)
	public Integer getMenuBrowseCount() {
		return menuBrowseCount;
	}

	public void setMenuBrowseCount(Integer menuBrowseCount) {
		this.menuBrowseCount = menuBrowseCount;
	}

	@Basic
	@Column(name = "menuImage", nullable = true)
	public java.sql.Blob getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(java.sql.Blob menuImage) {
		this.menuImage = menuImage;
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
	@Column(name = "foodId", nullable = true)
	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
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

	@Basic
	@Column(name = "menuAssessmentId", nullable = false)
	public int getMenuAssessmentId() {
		return menuAssessmentId;
	}

	public void setMenuAssessmentId(int menuAssessmentId) {
		this.menuAssessmentId = menuAssessmentId;
	}

	@Basic
	@Column(name = "menuAssessmentContent", nullable = true, length = 255)
	public String getMenuAssessmentContent() {
		return menuAssessmentContent;
	}

	public void setMenuAssessmentContent(String menuAssessmentContent) {
		this.menuAssessmentContent = menuAssessmentContent;
	}

	@Basic
	@Column(name = "isBrowsed", nullable = true)
	public boolean getIsBrowsed() {
		return isBrowsed;
	}

	public void setIsBrowsed(boolean isBrowsed) {
		this.isBrowsed = isBrowsed;
	}

	@Basic
	@Column(name = "isCollected", nullable = true)
	public boolean getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	@Basic
	@Column(name = "menuScore", nullable = true, precision = 0)
	public Double getMenuScore() {
		return menuScore;
	}

	public void setMenuScore(Double menuScore) {
		this.menuScore = menuScore;
	}

	@Basic
	@Column(name = "menuStepId", nullable = false)
	public int getMenuStepId() {
		return menuStepId;
	}

	public void setMenuStepId(int menuStepId) {
		this.menuStepId = menuStepId;
	}

	@Basic
	@Column(name = "menuStepDescription", nullable = true, length = 255)
	public String getMenuStepDescription() {
		return menuStepDescription;
	}

	public void setMenuStepDescription(String menuStepDescription) {
		this.menuStepDescription = menuStepDescription;
	}

	@Basic
	@Column(name = "menuStepOrderId", nullable = true)
	public Integer getMenuStepOrderId() {
		return menuStepOrderId;
	}

	public void setMenuStepOrderId(Integer menuStepOrderId) {
		this.menuStepOrderId = menuStepOrderId;
	}

	@Basic
	@Column(name = "menuStepImage", nullable = true)
	public java.sql.Blob getMenuStepImage() {
		return menuStepImage;
	}

	public void setMenuStepImage(java.sql.Blob menuStepImage) {
		this.menuStepImage = menuStepImage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ViewMenuinformationEntity that = (ViewMenuinformationEntity) o;
		return menuId == that.menuId && foodIngredientId == that.foodIngredientId
				&& menuAssessmentId == that.menuAssessmentId && menuStepId == that.menuStepId
				&& Objects.equals(userId, that.userId) && Objects.equals(menuName, that.menuName)
				&& Objects.equals(menuDescription, that.menuDescription)
				&& Objects.equals(menuScoreCount, that.menuScoreCount)
				&& Objects.equals(menuCollectedCount, that.menuCollectedCount)
				&& Objects.equals(menuBrowseCount, that.menuBrowseCount) && Objects.equals(menuImage, that.menuImage)
				&& Objects.equals(foodId, that.foodId) && Objects.equals(ingredientCount, that.ingredientCount)
				&& Objects.equals(ingredientUnit, that.ingredientUnit)
				&& Objects.equals(menuAssessmentContent, that.menuAssessmentContent)
				&& Objects.equals(isBrowsed, that.isBrowsed) && Objects.equals(isCollected, that.isCollected)
				&& Objects.equals(menuScore, that.menuScore)
				&& Objects.equals(menuStepDescription, that.menuStepDescription)
				&& Objects.equals(menuStepOrderId, that.menuStepOrderId)
				&& Objects.equals(menuStepImage, that.menuStepImage);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(menuId, userId, menuName, menuDescription, menuScoreCount, menuCollectedCount,
				menuBrowseCount, menuImage, foodIngredientId, foodId, ingredientCount, ingredientUnit, menuAssessmentId,
				menuAssessmentContent, isBrowsed, isCollected, menuScore, menuStepId, menuStepDescription,
				menuStepOrderId, menuStepImage);
		return result;
	}
}
