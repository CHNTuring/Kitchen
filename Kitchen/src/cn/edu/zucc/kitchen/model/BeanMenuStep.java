package cn.edu.zucc.kitchen.model;

public class BeanMenuStep {
	public static final String[] stepTitle = { "≤Ω÷Ë–Ú∫≈", "≤Ω÷Ë√Ë ˆ" };
	private int menuStepId;
	private int menuId;
	private int menuStepOrderId;
	private String menuStepDescription;
	private java.sql.Blob menuStepImage;
	private BeanMenu menu;

	public int getMenuStepId() {
		return menuStepId;
	}

	public void setMenuStepId(int menuStepId) {
		this.menuStepId = menuStepId;
	}

	public String getMenuStepDescription() {
		return menuStepDescription;
	}

	public void setMenuStepDescription(String menuStepDescription) {
		this.menuStepDescription = menuStepDescription;
	}

	public java.sql.Blob getMenuStepImage() {
		return menuStepImage;
	}

	public void setMenuStepImage(java.sql.Blob menuStepImage) {
		this.menuStepImage = menuStepImage;
	}

	public BeanMenu getMenu() {
		return menu;
	}

	public void setMenu(BeanMenu menu) {
		this.menu = menu;
	}

	public int getMenuStepOrderId() {
		return menuStepOrderId;
	}

	public void setMenuStepOrderId(int menuStepOrderId) {
		this.menuStepOrderId = menuStepOrderId;
	}

	public String getCell(int col) {
		if (col == 0)
			return menuStepOrderId + "";
		else if (col == 1)
			return menuStepDescription + "";
		else
			return "";
	}
}
