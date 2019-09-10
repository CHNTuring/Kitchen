package cn.edu.zucc.kitchen.comtrol.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.MD5Util;
import cn.edu.zucc.kitchen.itf.IAdminUserManager;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleAdminUserManager implements IAdminUserManager {
	/**
	 * 登陆 1、如果管理员不存在或者密码错误，抛出一个异常 2、如果认证成功，则返回当前用户信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	@Override
	public BeanAdminUser login(String username, String pwd) throws BaseException {
		BeanAdminUser au = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanAdminUser where adminUserName='" + username + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				BeanAdminUser b = (BeanAdminUser) query.list().get(0);
				if (b.getAdminUserPasswd().equals(MD5Util.MD5(pwd)) != true) {
					throw new BaseException("密码错误！");
				}
				au = b;
			} else {
				throw new BaseException("用户不存在！");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return au;
	}

	@Override
	public void deleteUser(String user) throws BaseException {
		// TODO Auto-generated method stub

	}


	@Override
	public void changePwd(BeanAdminUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanAdminUser where adminUserName='" + user.getAdminUserName() + "'";
			Query query = session.createQuery(hql);
			if("".equals(newPwd)||"".equals(newPwd2)) {
				throw new BaseException("密码不能为空！");
			}
			if (query.list().size() != 0) {
				if (user.getAdminUserPasswd().equals(MD5Util.MD5(oldPwd)) == true) {
					if (newPwd.equals(newPwd2) == true) {
						if(newPwd.equals("123456")) {
							throw new BaseException("密码不能为初始密码！");
						}
						BeanAdminUser u = session.get(BeanAdminUser.class, user.getAdminUserId());
						u.setAdminUserPasswd(MD5Util.MD5(newPwd));
					} else {
						throw new BaseException("密码不一致！");
					}
				} else {
					throw new BaseException("原密码错误！");
				}
			} else {
				throw new BaseException("用户不存在！");
			}
			tx.commit();
			BeanAdminUser.currentLoginUser.setAdminUserPasswd(MD5Util.MD5(newPwd));
		} catch (BusinessException e) {
			if (session != null) {
				try {
					tx.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void firstChangePwd(String user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanAdminUser where adminUserName='" + user + "'";
			Query query = session.createQuery(hql);
			if("".equals(newPwd)||"".equals(newPwd2)) {
				throw new BaseException("密码不能为空！");
			}
			if (query.list().size() != 0) {
					if (newPwd.equals(newPwd2) == true) {
						if(newPwd.equals("123456")) {
							throw new BaseException("密码不能为初始密码！");
						}
						BeanAdminUser u = session.get(BeanAdminUser.class, ((BeanAdminUser) query.list().get(0)).getAdminUserId());
						u.setAdminUserPasswd(MD5Util.MD5(newPwd));
					} else {
						throw new BaseException("密码不一致！");
					}
			} else {
				throw new BaseException("用户不存在！");
			}
			tx.commit();
			BeanAdminUser.currentLoginUser.setAdminUserPasswd(MD5Util.MD5(newPwd));
		} catch (BusinessException e) {
			if (session != null) {
				try {
					tx.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void reg(String user) throws BaseException {
		BeanAdminUser au = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanAdminUser where adminUserName='" + user + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("用户已存在！");
			} else {
				tx=session.beginTransaction();
				au=new BeanAdminUser();
				au.setAdminUserName(user);
				au.setAdminUserPasswd(MD5Util.MD5("123456"));
				session.save(au);
				tx.commit();
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
