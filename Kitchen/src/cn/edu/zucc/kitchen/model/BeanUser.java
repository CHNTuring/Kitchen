package cn.edu.zucc.kitchen.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import cn.edu.zucc.kitchen.model.BeanUser;

public class BeanUser {
	public static BeanUser currentLoginUser = null;

	public static final String[] userTitle = { "用户名", "性别", "联系电话", "电子邮箱", "所在城市", "注册时间" };
	private int userId;
	private String userName;
	private String userSex;
	private String userPasswd;
	private String userPhone;
	private String userEmail;
	private String userCity;
	private java.sql.Timestamp userRegisterTime;
	private Set<BeanMenuAssessment> assessments;
	private Set<BeanMenu> menus;
	private Set<BeanFoodOrder> foodOrders;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public java.sql.Timestamp getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(java.sql.Timestamp userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

	public Set<BeanMenuAssessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(Set<BeanMenuAssessment> assessments) {
		this.assessments = assessments;
	}

	public Set<BeanMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<BeanMenu> menus) {
		this.menus = menus;
	}

	public Set<BeanFoodOrder> getFoodOrders() {
		return foodOrders;
	}

	public void setFoodOrders(Set<BeanFoodOrder> foodOrders) {
		this.foodOrders = foodOrders;
	}

	public String getCell(int col) {
		if (col == 0)
			return userName + "";
		else if (col == 1)
			return userSex + "";
		else if (col == 2)
			return userPhone + "";
		else if (col == 3)
			return userEmail + "";
		else if (col == 4)
			return userCity + "";
		else if (col == 5)
			return userRegisterTime.toString();
		else
			return "";
	}

}
