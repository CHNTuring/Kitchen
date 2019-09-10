package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodTypeManager {
	/**
	 * 增加食材种类
	 * 
	 * @param foodtypename        食材种类
	 * @param foodtypedescription 种类描述
	 * @throws BaseException
	 */
	public void add(String foodtypename, String foodtypedescription) throws BaseException;

	/**
	 * 删除食材种类，如果食材种类已经被食材引用，则不允许删除
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	public void delete(BeanFoodType foodtype) throws BaseException;

	/**
	 * 修改食材种类
	 * 
	 * @param foodtype
	 * @param foodtypename        食材种类
	 * @param foodtypedescription 种类描述
	 * @throws BaseException
	 */
	public void modify(BeanFoodType foodtype, String foodtypename, String foodtypedescription) throws BaseException;

	/**
	 * 提取所有食材种类信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFoodType> loadAll() throws BaseException;
}
