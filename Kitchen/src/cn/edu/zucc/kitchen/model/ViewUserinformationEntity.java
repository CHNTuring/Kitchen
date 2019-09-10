package cn.edu.zucc.kitchen.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "view_userinformation", schema = "kitchen", catalog = "")
public class ViewUserinformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userid;
	private String userName;
	private String userSex;
	private String userPasswd;
	private String userPhone;
	private String userEmail;
	private String userCity;
	private Date userRegisterTime;
	private int menuId;
	private String menuName;
	private String menuDescription;
	private Double menuScoreCount;
	private Integer menuCollectedCount;
	private Integer menuBrowseCount;
	private java.sql.Blob menuImage;
	private int menuAssessmentId;
	private String menuAssessmentContent;
	private Byte isBrowsed;
	private Byte isCollected;
	private Double menuScore;
	private int foodOrderId;
	private Date deadline;
	private String address;
	private String phone;
	private String status;

	@Basic
	@Column(name = "userid", nullable = false)
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Basic
	@Column(name = "userName", nullable = true, length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Basic
	@Column(name = "userSex", nullable = true, length = 10)
	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	@Basic
	@Column(name = "userPasswd", nullable = true, length = 512)
	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	@Basic
	@Column(name = "userPhone", nullable = true, length = 20)
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Basic
	@Column(name = "userEmail", nullable = true, length = 20)
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Basic
	@Column(name = "userCity", nullable = true, length = 20)
	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Basic
	@Column(name = "userRegisterTime", nullable = true)
	public Date getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(Date userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

	@Basic
	@Column(name = "menuId", nullable = false)
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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
	public Byte getIsBrowsed() {
		return isBrowsed;
	}

	public void setIsBrowsed(Byte isBrowsed) {
		this.isBrowsed = isBrowsed;
	}

	@Basic
	@Column(name = "isCollected", nullable = true)
	public Byte getIsCollected() {
		return isCollected;
	}

	public void setIsCollected(Byte isCollected) {
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
	@Column(name = "foodOrderId", nullable = false)
	public int getFoodOrderId() {
		return foodOrderId;
	}

	public void setFoodOrderId(int foodOrderId) {
		this.foodOrderId = foodOrderId;
	}

	@Basic
	@Column(name = "deadline", nullable = true)
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Basic
	@Column(name = "address", nullable = true, length = 55)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Basic
	@Column(name = "phone", nullable = true, length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Basic
	@Column(name = "status", nullable = true, length = 10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ViewUserinformationEntity that = (ViewUserinformationEntity) o;
		return userid == that.userid && menuId == that.menuId && menuAssessmentId == that.menuAssessmentId
				&& foodOrderId == that.foodOrderId && Objects.equals(userName, that.userName)
				&& Objects.equals(userSex, that.userSex) && Objects.equals(userPasswd, that.userPasswd)
				&& Objects.equals(userPhone, that.userPhone) && Objects.equals(userEmail, that.userEmail)
				&& Objects.equals(userCity, that.userCity) && Objects.equals(userRegisterTime, that.userRegisterTime)
				&& Objects.equals(menuName, that.menuName) && Objects.equals(menuDescription, that.menuDescription)
				&& Objects.equals(menuScoreCount, that.menuScoreCount)
				&& Objects.equals(menuCollectedCount, that.menuCollectedCount)
				&& Objects.equals(menuBrowseCount, that.menuBrowseCount) && Objects.equals(menuImage, that.menuImage)
				&& Objects.equals(menuAssessmentContent, that.menuAssessmentContent)
				&& Objects.equals(isBrowsed, that.isBrowsed) && Objects.equals(isCollected, that.isCollected)
				&& Objects.equals(menuScore, that.menuScore) && Objects.equals(deadline, that.deadline)
				&& Objects.equals(address, that.address) && Objects.equals(phone, that.phone)
				&& Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(userid, userName, userSex, userPasswd, userPhone, userEmail, userCity,
				userRegisterTime, menuId, menuName, menuDescription, menuScoreCount, menuCollectedCount,
				menuBrowseCount, menuImage, menuAssessmentId, menuAssessmentContent, isBrowsed, isCollected, menuScore,
				foodOrderId, deadline, address, phone, status);
		return result;
	}
}
