package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodManager {
	/**
	 * 增加食材： 选择相应的食材种类，若数据库中已存在同名食材则增加失败抛出异常
	 * 
	 * @param foodtype           食材种类
	 * @param foodname           食材名称
	 * @param foodprice          食材价格
	 * @param foodcount          食材数量
	 * @param fooddescription    食材描述
	 * @param foodspecifications 食材规格
	 * @param filepath           食材图片
	 * @return
	 * @throws BaseException
	 */
	public void add(BeanFoodType foodtype, String foodname, double foodprice, double foodcount, String fooddescription,
			String foodspecifications, String filepath) throws BaseException;

	/**
	 * 删除食材，如果食材已经被菜谱引用或已经被用户和管理员购买，则不允许删除
	 * 
	 * @param food
	 * @throws BaseException
	 */
	public void delete(BeanFood food) throws BaseException;

	/**
	 * 修改食材信息，可以先从数据库中提取相关信息到相应的表单中，方便修改
	 * 
	 * @param food
	 * @param foodtype           食材种类
	 * @param foodname           食材名称
	 * @param foodprice          食材价格
	 * @param foodcount          食材数量
	 * @param fooddescription    食材描述
	 * @param foodspecifications 食材规格
	 * @param foodimage          食材图片
	 * @return
	 * @throws BaseException
	 */
	public void modify(BeanFood food, BeanFoodType foodtype, String foodname, double foodprice, double foodcount,
			String fooddescription, String foodspecifications, String filepath) throws BaseException;

	/**
	 * 提取所有食材信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFood> loadAll() throws BaseException;
	
	/**
	 * 提取指定种类的食材信息
	 * 
	 * @param type        食材类别
	 * @throws BaseException
	 */
	public List<BeanFood> load(BeanFoodType type) throws BaseException;
}
