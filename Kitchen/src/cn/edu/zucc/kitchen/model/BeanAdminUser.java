package cn.edu.zucc.kitchen.model;

import java.util.Set;

public class BeanAdminUser {
	public static BeanAdminUser currentLoginUser = null;
	private int adminUserId;
	private String adminUserName;
	private String adminUserPasswd;
	private Set<BeanFoodPurchase> purchase;

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminUserPasswd() {
		return adminUserPasswd;
	}

	public void setAdminUserPasswd(String adminUserPasswd) {
		this.adminUserPasswd = adminUserPasswd;
	}

	public Set<BeanFoodPurchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(Set<BeanFoodPurchase> purchase) {
		this.purchase = purchase;
	}

}
