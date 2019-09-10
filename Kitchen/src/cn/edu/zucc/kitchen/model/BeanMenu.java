package cn.edu.zucc.kitchen.model;

import java.util.Set;

public class BeanMenu {
	public static final String[] menuTitle={"菜谱名称","创建用户","综合评分","收藏总数","浏览总数"};
	private int menuId;
	private int userId;
	private String menuName;
	private String menuDescription;
	private double menuScoreCount;
	private int menuCollectedCount;
	private int menuBrowseCount;
	private java.sql.Blob menuImage;
	private BeanUser user;
	private Set<BeanMenuAssessment> assessmets;
	private Set<BeanMenuIngredient> ingredients;
	private Set<BeanMenuStep> steps;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public double getMenuScoreCount() {
		return menuScoreCount;
	}

	public void setMenuScoreCount(double menuScoreCount) {
		this.menuScoreCount = menuScoreCount;
	}

	public int getMenuCollectedCount() {
		return menuCollectedCount;
	}

	public void setMenuCollectedCount(int menuCollectedCount) {
		this.menuCollectedCount = menuCollectedCount;
	}

	public int getMenuBrowseCount() {
		return menuBrowseCount;
	}

	public void setMenuBrowseCount(int menuBrowseCount) {
		this.menuBrowseCount = menuBrowseCount;
	}

	public java.sql.Blob getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(java.sql.Blob menuImage) {
		this.menuImage = menuImage;
	}

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	public Set<BeanMenuAssessment> getAssessmets() {
		return assessmets;
	}

	public void setAssessmets(Set<BeanMenuAssessment> assessmets) {
		this.assessmets = assessmets;
	}

	public Set<BeanMenuIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<BeanMenuIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<BeanMenuStep> getSteps() {
		return steps;
	}

	public void setSteps(Set<BeanMenuStep> steps) {
		this.steps = steps;
	}
	
	public String getCell(int col){
		if(col==0) return menuName+"";
		else if(col==1) return user.getUserName()+"";
		else if(col==2) return menuScoreCount+"";
		else if(col==3) return menuCollectedCount+"";
		else if(col==4) return menuBrowseCount+"";
		else return "";
	}
}
