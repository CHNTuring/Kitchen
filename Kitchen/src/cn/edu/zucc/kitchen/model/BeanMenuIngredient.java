package cn.edu.zucc.kitchen.model;

public class BeanMenuIngredient {
	public static final String[] foodTitle = { "用料类别", "用料名称", "用料数量" };
	private int foodIngredientId;
	private int foodId;
	private int menuId;
	private double ingredientCount;
	private String ingredientUnit;
	private BeanFood food;
	private BeanMenu menu;

	public int getFoodIngredientId() {
		return foodIngredientId;
	}

	public void setFoodIngredientId(int foodIngredientId) {
		this.foodIngredientId = foodIngredientId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public double getIngredientCount() {
		return ingredientCount;
	}

	public void setIngredientCount(double ingredientCount) {
		this.ingredientCount = ingredientCount;
	}

	public String getIngredientUnit() {
		return ingredientUnit;
	}

	public void setIngredientUnit(String ingredientUnit) {
		this.ingredientUnit = ingredientUnit;
	}

	public BeanFood getFood() {
		return food;
	}

	public void setFood(BeanFood food) {
		this.food = food;
	}

	public BeanMenu getMenu() {
		return menu;
	}

	public void setMenu(BeanMenu menu) {
		this.menu = menu;
	}

	public String getCell(int col) {
		if (col == 0)
			return food.getFoodType().getFoodTypeName() + "";
		else if (col == 1)
			return food.getFoodName() + "";
		else if (col == 2 && ingredientCount == 0) {
			return ingredientUnit + "";
		} else if (col == 2 && ingredientCount != 0)
			return ingredientCount + ingredientUnit + "";
		else
			return "";
	}
}
