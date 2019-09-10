package cn.edu.zucc.kitchen.itf;

import java.io.IOException;
import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodOrder;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanOrderDetail;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodOrderManager {
	/**
	 * 增加菜谱步骤，若步骤流水号冲突则拒绝修改抛出异常
	 * 
	 * @param user     用户
	 * @param deadline 截止时间
	 * @param address  地址
	 * @param phone    电话
	 * @throws BaseException
	 */
	public void add(BeanUser user, BeanMenu menu, java.sql.Timestamp deadline, String address, String phone)
			throws BaseException;

	/**
	 * 增加菜谱步骤，若步骤流水号冲突则拒绝修改抛出异常
	 * 
	 * @param user     用户
	 * @param deadline 截止时间
	 * @param address  地址
	 * @param phone    电话
	 * @throws BaseException
	 */
	public void addFoodOrder(BeanUser user,BeanFood food, double count,java.sql.Timestamp deadline, String address, String phone)
			throws BaseException;
	
	/**
	 * 删除步骤
	 * 
	 * @param order
	 * @throws BaseException
	 */
	public void delete(BeanFoodOrder order) throws BaseException;

	/**
	 * 修改菜谱步骤
	 * 
	 * @param menu                菜谱
	 * @param menuStepDescription 步骤描述
	 * @param filepath            步骤图片
	 * @throws BaseException
	 */
	public void modify(BeanFoodOrder order, java.sql.Timestamp deadline, String address, String phone)
			throws BaseException;

	/**
	 * 提取用户所有订单信息
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFoodOrder> loadAll(BeanUser user) throws BaseException;

	/**
	 * 菜谱步骤查询
	 * 
	 * @param step 菜谱步骤
	 * @throws BaseException
	 */
	public List<BeanOrderDetail> load(BeanFoodOrder order) throws BaseException;

}
