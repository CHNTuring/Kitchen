package cn.edu.zucc.kitchen.comtrol.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import cn.edu.zucc.kitchen.MD5Util;
import cn.edu.zucc.kitchen.itf.IUserManager;
import cn.edu.zucc.kitchen.model.BeanFoodOrder;
import cn.edu.zucc.kitchen.model.BeanFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleUserManager implements IUserManager {

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
	@Override
	public BeanUser reg(String username, String pwd, String pwd2, String sex, String phone, String eamil, String city)
			throws BaseException {
		BeanUser bu = new BeanUser();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
//			if ("".equals(username) == true) {
//				throw new BaseException("用户名不能为空！");
//			}
//			if ("".equals(pwd) == true || "".equals(pwd2) == true) {
//				throw new BaseException("密码不能为空！");
//			}
			String hql = "from BeanUser where userName='" + username + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("用户名已存在！");
			} else {
				if (pwd.equals(pwd2) == true) {
					bu.setUserName(username);
					bu.setUserPasswd(MD5Util.MD5(pwd));
					bu.setUserPhone(phone);
					bu.setUserCity(city);
					bu.setUserCity(city);
					bu.setUserSex(sex);
					bu.setUserEmail(eamil);
					bu.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
					session.save(bu);
				} else {
					throw new BaseException("密码不一致");
				}
			}
			tx.commit();
		} catch (BusinessException e) {
			e.printStackTrace();
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
		return bu;
	}

	/**
	 * 登陆 1、如果用户不存在或者密码错误，抛出一个异常 2、如果认证成功，则返回当前用户信息
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	@Override
	public BeanUser login(String username, String pwd) throws BaseException {
		BeanUser bu = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanUser where userName='" + username + "'"; // "' and userPasswd='" + MD5Util.MD5(pwd) +
																			// "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				BeanUser b = (BeanUser) query.list().get(0);
				if (b.getUserPasswd().equals(MD5Util.MD5(pwd)) != true) {
					throw new BaseException("密码错误！");
				}
				bu = b;
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

		return bu;
	}

	/**
	 * 修改密码 如果重置，则抛出异常
	 * 
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if("".equals(newPwd)||"".equals(newPwd2)) {
				throw new BaseException("密码不能为空！");
			}
			String hql = "from BeanUser where userName='" + user.getUserName() + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				if (user.getUserPasswd().equals(MD5Util.MD5(oldPwd)) == true) {
					if (newPwd.equals(newPwd2) == true) {
						BeanUser u = session.get(BeanUser.class, user.getUserId());
						u.setUserPasswd(MD5Util.MD5(newPwd));
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
			BeanUser.currentLoginUser.setUserPasswd(MD5Util.MD5(newPwd));
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

	/**
	 * 用户选择永久注销 如果用户存在尚未送达的食材订单，则抛出异常
	 * 
	 * @param user 被删除的用户
	 * @throws BaseException
	 */
	@Override
	public void logoutForever(String user) throws BaseException {
//		Session session = null;
//		Transaction tx = null;
//		try {
//			
//		} catch (BusinessException e) {
//			if (session != null) {
//				try {
//					tx.rollback();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		} finally {
//			try {
//				if (session != null) {
//					session.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}


	@Override
	public List<BeanUser> loadAll() throws BaseException {
		List<BeanUser> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanUser where userId>0";
			Query query = session.createQuery(hql);
			result = (List<BeanUser>) query.list();

		} catch (Exception e) {
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
		return result;
	}

	public void resetPwd(String user) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanUser where userName='" + user + "'";
			Query query = session.createQuery(hql);
			BeanUser u = (BeanUser) query.list().get(0);
			BeanUser bu = session.get(BeanUser.class, u.getUserId());
			bu.setUserPasswd(MD5Util.MD5("123456"));
			session.save(bu);
			tx.commit();
		} catch (Exception e) {
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

	@Override
	public void delete(String user) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanUser where userName='" + user + "'";

			BeanUser u = (BeanUser) session.createQuery(hql).list().get(0);
			BeanUser bu = session.get(BeanUser.class, u.getUserId());
			hql = "from BeanFoodOrder where userId=" + bu.getUserId()
					+ " and status not like '送达' and status not like '退货'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("用户尚有食材订单未送达！不允许注销！");
			}
			for (BeanMenu m : u.getMenus()) {
				BeanMenu menu = session.get(BeanMenu.class, m.getMenuId());
				menu.setUser(session.get(BeanUser.class, 0));
			}
			for (BeanFoodOrder m : u.getFoodOrders()) {
				BeanFoodOrder menu = session.get(BeanFoodOrder.class, m.getFoodOrderId());
				menu.setUser(session.get(BeanUser.class, 0));
			}
			for (BeanMenuAssessment m : u.getAssessments()) {
				BeanMenuAssessment menu = session.get(BeanMenuAssessment.class, m.getMenuAssessmentId());
				menu.setUser(session.get(BeanUser.class, 0));
			}
			session.delete(bu);
			tx.commit();
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

	@Override
	public List<BeanUser> search(String user) throws BaseException {
		List<BeanUser> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanUser where userName like '%"+user+"%'";
			Query query = session.createQuery(hql);
			result = (List<BeanUser>) query.list();

		} catch (Exception e) {
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
		return result;
	}

	@Override
	public void modify(BeanUser user, String username, String sex, String phone, String eamil, String city)
			throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if(!user.getUserName().equals(username)) {
				String hql = "from BeanUser where userName='" + username + "'";
				Query query = session.createQuery(hql);
				if (query.list().size() != 0) {
					throw new BaseException("用户名已存在！");
				}
			}
			BeanUser u=session.get(BeanUser.class, user.getUserId());
			u.setUserName(username);
			u.setUserCity(city);
			u.setUserPhone(phone);
			u.setUserSex(sex);
			u.setUserEmail(eamil);
			tx.commit();

		} catch (BusinessException e) {
			e.printStackTrace();
//			throw new BaseException("");
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