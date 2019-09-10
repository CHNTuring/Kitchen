package cn.edu.zucc.kitchen;

import cn.edu.zucc.kitchen.comtrol.example.ExampleAdminUserManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodOrderManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodPurchase;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodTypeManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleMenuAssessmentManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleMenuIngredient;
import cn.edu.zucc.kitchen.comtrol.example.ExampleMenuManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleMenuStepManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleUserManager;
import cn.edu.zucc.kitchen.itf.IAdminUserManager;
import cn.edu.zucc.kitchen.itf.IFoodManager;
import cn.edu.zucc.kitchen.itf.IFoodOrderManager;
import cn.edu.zucc.kitchen.itf.IFoodPurchase;
import cn.edu.zucc.kitchen.itf.IFoodTypeManager;
import cn.edu.zucc.kitchen.itf.IMenuAssessmentManager;
import cn.edu.zucc.kitchen.itf.IMenuIngredient;
import cn.edu.zucc.kitchen.itf.IMenuManager;
import cn.edu.zucc.kitchen.itf.IMenuStepManager;
import cn.edu.zucc.kitchen.itf.IUserManager;

public class KitchenAssistantUtil {
	public static IFoodManager foodManager = new ExampleFoodManager();
	public static IFoodTypeManager foodTypeManager = new ExampleFoodTypeManager();
	public static IMenuManager menuManager = new ExampleMenuManager();
	public static IMenuStepManager menuStepManager = new ExampleMenuStepManager();
	public static IUserManager userManager = new ExampleUserManager();
	public static IAdminUserManager adminUserManager = new ExampleAdminUserManager();
	public static IMenuIngredient menuIngredient = new ExampleMenuIngredient();
	public static IMenuAssessmentManager assessmentManager = new ExampleMenuAssessmentManager();
	public static IFoodOrderManager foodOrderManager = new ExampleFoodOrderManager();
	public static IFoodPurchase foodPurchaseManager = new ExampleFoodPurchase();
}
