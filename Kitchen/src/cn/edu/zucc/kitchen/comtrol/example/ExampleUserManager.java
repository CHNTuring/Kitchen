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
	 * ע�᣺ Ҫ���û��������ظ�������Ϊ�� ����������������һ�£����벻��Ϊ�� ���ע��ʧ�ܣ����׳��쳣
	 * 
	 * @param username �û���
	 * @param pwd      ����
	 * @param pwd2     �ظ����������
	 * @param sex      �Ա�
	 * @param phone    �绰
	 * @param city     ���ڳ���
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
//				throw new BaseException("�û�������Ϊ�գ�");
//			}
//			if ("".equals(pwd) == true || "".equals(pwd2) == true) {
//				throw new BaseException("���벻��Ϊ�գ�");
//			}
			String hql = "from BeanUser where userName='" + username + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("�û����Ѵ��ڣ�");
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
					throw new BaseException("���벻һ��");
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
	 * ��½ 1������û������ڻ�����������׳�һ���쳣 2�������֤�ɹ����򷵻ص�ǰ�û���Ϣ
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
					throw new BaseException("�������");
				}
				bu = b;
			} else {
				throw new BaseException("�û������ڣ�");
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
	 * �޸����� ������ã����׳��쳣
	 * 
	 * @param user    ��ǰ�û�
	 * @param oldPwd  ԭ����
	 * @param newPwd  ������
	 * @param newPwd2 �ظ������������
	 */
	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if("".equals(newPwd)||"".equals(newPwd2)) {
				throw new BaseException("���벻��Ϊ�գ�");
			}
			String hql = "from BeanUser where userName='" + user.getUserName() + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				if (user.getUserPasswd().equals(MD5Util.MD5(oldPwd)) == true) {
					if (newPwd.equals(newPwd2) == true) {
						BeanUser u = session.get(BeanUser.class, user.getUserId());
						u.setUserPasswd(MD5Util.MD5(newPwd));
					} else {
						throw new BaseException("���벻һ�£�");
					}
				} else {
					throw new BaseException("ԭ�������");
				}
			} else {
				throw new BaseException("�û������ڣ�");
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
	 * �û�ѡ������ע�� ����û�������δ�ʹ��ʳ�Ķ��������׳��쳣
	 * 
	 * @param user ��ɾ�����û�
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
					+ " and status not like '�ʹ�' and status not like '�˻�'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("�û�����ʳ�Ķ���δ�ʹ������ע����");
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
					throw new BaseException("�û����Ѵ��ڣ�");
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