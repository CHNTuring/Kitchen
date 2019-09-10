package cn.edu.zucc.kitchen.itf;

import java.io.IOException;
import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuStepManager {
	/**
	 * 增加菜谱步骤，若步骤流水号冲突则拒绝修改抛出异常
	 * 
	 * @param menu                菜谱
	 * @param menuStepOrderId     菜谱步骤流水号
	 * @param menuStepDescription 步骤描述
	 * @param filepath            步骤图片
	 * @throws BaseException
	 * @throws IOException
	 */
	public void add(BeanMenu menu, String menuStepDescription, int menuStepOrderId, String filepath)
			throws BaseException, IOException;

	/**
	 * 删除步骤
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	public void delete(BeanMenuStep step) throws BaseException;

	/**
	 * 修改菜谱步骤
	 * 
	 * @param menu                菜谱
	 * @param menuStepDescription 步骤描述
	 * @param filepath            步骤图片
	 * @throws BaseException
	 */
	public void modify(BeanMenuStep Step, String menuStepDescription, String filepath) throws BaseException;

	/**
	 * 调整当前步骤的流水号 注意：数据库表中menuStepOrderId上建立了唯一索引，调整当前步骤的序号值和上一步骤的序号值时不能出现序号值一样的情况
	 * 
	 * @param step
	 * @throws BaseException
	 */
	public void moveUp(BeanMenuStep step) throws BaseException;

	public void moveDown(BeanMenuStep step) throws BaseException;

	/**
	 * 提取所有食材种类信息
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuStep> loadAll(BeanMenu menu) throws BaseException;
	
	/**
	 * 菜谱步骤查询
	 * 
	 * @param step        菜谱步骤
	 * @throws BaseException
	 */
	public List<BeanMenuStep> search(BeanMenuStep step) throws BaseException;
}
