package cn.edu.zucc.kitchen.itf;

import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IAdminUserManager {
	/**
	 * 登陆 1、如果管理员不存在或者密码错误，抛出一个异常 2、如果认证成功，则返回当前用户信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanAdminUser login(String username, String pwd) throws BaseException;

	/**
	 * 管理员删除用户 如果用户存在尚未送达的食材订单，则抛出异常
	 * 
	 * @param user 被删除的用户
	 * @throws BaseException
	 */
	public void deleteUser(String user) throws BaseException;

	/**
	 * 修改密码 如果重置，则抛出异常
	 * 
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	public void changePwd(BeanAdminUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException;

	/**
	 *新增管理员
	 * 
	 * @param user 新管理员用户名
	 * @throws BaseException
	 */
	public void reg(String user) throws BaseException;

	public void firstChangePwd(String userName, String string, String string2, String string3) throws BaseException;
}
