package cn.edu.zucc.kitchen.itf;

import java.io.IOException;
import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuIngredient {
	/**
	 * 增加菜谱用料，若步骤流水号冲突则拒绝修改抛出异常
	 * 
	 * @param menu            菜谱
	 * @param food            食材
	 * @param ingredientCount 用料数量
	 * @param ingredientUnit  用料单位
	 * @throws BaseException
	 */
	public void add(BeanMenu menu, BeanFood food, double ingredientCount, String ingredientUnit) throws BaseException;

	/**
	 * 删除用料信息
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	public void delete(BeanMenuIngredient food) throws BaseException;

	/**
	 * 修改用料信息
	 * 
	 * @param food                用料
	 * @param ingredientCount  用料量
	 * @param ingredientUnit            用料单位
	 * @throws BaseException
	 */
	public void modify(BeanMenuIngredient food, double ingredientCount, String ingredientUnit) throws BaseException;


	/**
	 * 提取所有用料信息
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuIngredient> loadAll(BeanMenu menu) throws BaseException;
	
	/**
	 * 提取指定用料信息
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public BeanMenuIngredient search(BeanMenuIngredient food) throws BaseException;

}
