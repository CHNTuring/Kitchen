package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodPurchase {
	/**
	 * 增加食材种类
	 * 
	 * @param food  食材
	 * @param count 食材数量
	 * @throws BaseException
	 */
	public void add(BeanFood food, double count) throws BaseException;

	/**
	 * 删除食材种类，如果食材种类已经被食材引用，则不允许删除
	 * 
	 * @param purchase
	 * @throws BaseException
	 */
	public void delete(BeanFoodPurchase purchase) throws BaseException;

	/**
	 * 修改食材
	 * 
	 * @param purchase
	 * @param food        食材种类
	 * @param count 种类描述
	 * @throws BaseException
	 */
	public void modify(BeanFoodPurchase purchase, BeanFood food, double count,String status) throws BaseException;

	/**
	 * 提取所有食材采购信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFoodPurchase> loadAll() throws BaseException;
}
