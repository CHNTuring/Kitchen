package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IUserManager {
	/**
	 * 注册： 要求用户名不能重复，不能为空 两次输入的密码必须一致，密码不能为空 如果注册失败，则抛出异常
	 * 
	 * @param username 用户名
	 * @param pwd      密码
	 * @param pwd2     重复输入的密码
	 * @param sex      性别
	 * @param phone    电话
	 * @param city     所在城市
	 * @param email
	 * @return
	 * @throws BaseException
	 */
	public BeanUser reg(String username, String pwd, String pwd2, String sex, String phone, String eamil, String city)
			throws BaseException;

	/**
	 * 注册： 要求用户名不能重复，不能为空 两次输入的密码必须一致，密码不能为空 如果注册失败，则抛出异常
	 * 
	 * @param username 用户名
	 * @param pwd      密码
	 * @param pwd2     重复输入的密码
	 * @param sex      性别
	 * @param phone    电话
	 * @param city     所在城市
	 * @param email
	 * @return
	 * @throws BaseException
	 */
	public void modify(BeanUser user, String username, String sex, String phone, String eamil, String city)
			throws BaseException;

	/**
	 * 登陆 1、如果用户不存在或者密码错误，抛出一个异常 2、如果认证成功，则返回当前用户信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanUser login(String username, String pwd) throws BaseException;

	/**
	 * 修改密码 如果重置，则抛出异常
	 * 
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException;

	/**
	 * 用户选择永久注销 如果用户存在尚未送达的食材订单，则抛出异常
	 * 
	 * @param user 被删除的用户
	 * @throws BaseException
	 */
	public void logoutForever(String user) throws BaseException;

	/**
	 * 加载普通用户
	 * 
	 * @param user 用户
	 * @throws BaseException
	 */
	public List<BeanUser> loadAll() throws BaseException;

	/**
	 * 重置密码
	 * 
	 * @param user 用户
	 * @throws BaseException
	 */
	public void resetPwd(String user) throws BaseException;

	/**
	 * 删除用户
	 * 
	 * @param user 用户
	 * @throws BaseException
	 */
	public void delete(String user) throws BaseException;

	/**
	 * 模糊查询用户
	 * 
	 * @param user 用户
	 * @throws BaseException
	 */
	public List<BeanUser> search(String user) throws BaseException;
}
