package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuAssessmentManager {
	/**
	 * 增加菜谱
	 * 
	 * @param user        用户
	 * @param menu        菜谱
	 * @param assessment  菜谱评价
	 * @param isCollected 收藏标记
	 * @param menuScore   评分
	 * @throws BaseException
	 */
	public void add(BeanUser user, BeanMenu menu, String assessment, boolean isCollected, double menuScore)
			throws BaseException;

	/**
	 * 删除评价
	 * 
	 * @param assessment
	 * @throws BaseException
	 */
	public void delete(BeanMenuAssessment assessment) throws BaseException;

	/**
	 * 修改菜谱信息
	 * 
	 * @param a           菜谱名称
	 * @param assessment  菜谱评价
	 * @param isCollected 收藏标记
	 * @param menuScore   评分
	 * @throws BaseException
	 */
	public void modify(BeanMenuAssessment a, String assessment, boolean isCollected, double menuScore)
			throws BaseException;

	/**
	 * 提取所有菜谱评价信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuAssessment> loadAll(BeanMenu menu) throws BaseException;


	/**
	 * 提取我创建的菜谱评价信息
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuAssessment> loadMyMenu(BeanUser user) throws BaseException;
	
	public BeanMenuAssessment search(BeanMenu menu) throws BaseException;
}
