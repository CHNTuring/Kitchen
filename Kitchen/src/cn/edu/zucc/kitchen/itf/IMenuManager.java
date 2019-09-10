package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuManager {
	/**
	 * 增加菜谱
	 * 
	 * @param menuName        菜谱名称
	 * @param menuDescription 菜谱描述
	 * @param filepath        菜谱成品图片
	 * @param user            菜谱提供用户
	 * @throws BaseException
	 */
	public void add(BeanUser user, String menuName, String menuDescrpition, String filepath) throws BaseException;

	/**
	 * 删除菜谱，同时删除与之相关联的配料记录评价记录和步骤记录，并提醒用户
	 * 
	 * @param menu
	 * @throws BaseException
	 */
	public void delete(BeanMenu menu) throws BaseException;
	
	/**
	 * 管理员删除菜谱及其关联信息
	 * 
	 * @param menu
	 * @throws BaseException
	 */
	public void adminAelete(BeanMenu menu) throws BaseException;

	/**
	 * 修改菜谱信息
	 * 
	 * @param menuName        菜谱名称
	 * @param menuDescription 菜谱描述
	 * @param filepath        菜谱成品图片
	 * @throws BaseException
	 */
	public void modify(BeanMenu menu, String menuName, String menuDescription, String filepath)
			throws BaseException;

	/**
	 * 提取所有菜谱信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenu> loadAll() throws BaseException;
	
	/**
	 * 菜谱模糊查询
	 * 
	 * @param name        菜谱模糊名称
	 * @throws BaseException
	 */
	public List<BeanMenu> search(String name) throws BaseException;
	
	/**
	 * 提取我创建的菜谱信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenu> loadMyMenu(BeanUser user) throws BaseException;
}
