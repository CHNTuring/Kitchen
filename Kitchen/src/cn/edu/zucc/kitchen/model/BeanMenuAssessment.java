package cn.edu.zucc.kitchen.model;

public class BeanMenuAssessment {
	private int menuAssessmentId;
	private int userId;
	private int menuId;
	private String menuAssessmentContent;
	private boolean isBrowsed;
	private boolean isCollected;
	private double menuScore;
	private BeanUser user;
	private BeanMenu menu;

	public int getMenuAssessmentId() {
		return menuAssessmentId;
	}

	public void setMenuAssessmentId(int menuAssessmentId) {
		this.menuAssessmentId = menuAssessmentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuAssessmentContent() {
		return menuAssessmentContent;
	}

	public void setMenuAssessmentContent(String menuAssessmentContent) {
		this.menuAssessmentContent = menuAssessmentContent;
	}

	public boolean getIsBrowsed() {
		return isBrowsed;
	}

	public void setIsBrowsed(boolean isBrowsed) {
		this.isBrowsed = isBrowsed;
	}

	public boolean getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	public double getMenuScore() {
		return menuScore;
	}

	public void setMenuScore(double menuScore) {
		this.menuScore = menuScore;
	}

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	public BeanMenu getMenu() {
		return menu;
	}

	public void setMenu(BeanMenu menu) {
		this.menu = menu;
	}

}
