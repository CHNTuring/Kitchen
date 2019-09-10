package cn.edu.zucc.kitchen.comtrol.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IMenuManager;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleMenuManager implements IMenuManager {
	/**
	 * ���Ӳ���
	 * 
	 * @param menuName        ��������
	 * @param menuDescription ��������
	 * @param filepath        ���׳�ƷͼƬ
	 * @param user            �����ṩ�û�
	 * @throws BaseException
	 */
	@Override
	public void add(BeanUser user, String menuName, String menuDescrpition, String filepath) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanMenu bm = new BeanMenu();
			bm.setUser(user);
			bm.setMenuName(menuName);
			bm.setMenuDescription(menuDescrpition);
			if ("".equals(filepath) != true && filepath != null) {
				FileInputStream fis = new FileInputStream(filepath);
				byte[] byteArray = new byte[fis.available()];
				fis.read(byteArray);
				bm.setMenuImage(Hibernate.getLobCreator(session).createBlob(byteArray));
				fis.close();
			}
			session.save(bm);
			tx.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("ͼƬ�洢ʧ�ܣ�");
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
	 * ɾ�����ף�ͬʱɾ����֮����������ϼ�¼���ۼ�¼�Ͳ����¼���������û�
	 * 
	 * @param menu
	 * @throws BaseException
	 */
	@Override
	public void delete(BeanMenu menu) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql="delete BeanMenuAssessment where menuId="+menu.getMenuId();
			session.createQuery(hql).executeUpdate();
			session.createQuery("delete BeanMenuIngredient where menuId="+menu.getMenuId()).executeUpdate() ;
			session.createQuery("delete BeanMenuStep where menuId="+menu.getMenuId()).executeUpdate() ;
//			String hql = "from ViewMenuinformationEntity where menuId=" + menu.getMenuId();
			Query query = session.createQuery(hql);
//			if (query.list().size() != 0) {
//				throw new BaseException("�ò�������ϵͳ������Ϣ�б����ã�������ɾ����");
//			}
			BeanMenu bm = (BeanMenu) session.get(BeanMenu.class, menu.getMenuId());
			session.delete(bm);
			tx.commit();
		} catch (Exception e) {
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
	}

	/**
	 * �޸Ĳ�����Ϣ
	 * 
	 * @param menuName        ��������
	 * @param menuDescription ��������
	 * @param menuImage       ���׳�ƷͼƬ
	 * @throws BaseException
	 */
	@Override
	public void modify(BeanMenu menu, String menuName, String menuDescription, String filepath) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanMenu bm = session.get(BeanMenu.class, menu.getMenuId());
			bm.setMenuName(menuName);
			bm.setMenuDescription(menuDescription);
			if ("".equals(filepath) != true && filepath != null) {
				FileInputStream fis = new FileInputStream(filepath);
				byte[] byteArray = new byte[fis.available()];
				fis.read(byteArray);
				bm.setMenuImage(Hibernate.getLobCreator(session).createBlob(byteArray));
				fis.close();
			}
			tx.commit();
		} catch (Exception e) {
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

	}

	/**
	 * ��ȡ���в�����Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<BeanMenu> loadAll() throws BaseException {
		List<BeanMenu> result =null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenu";
			Query query = session.createQuery(hql);
			result = (List<BeanMenu>) query.list();

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

	/**
	 * ����ģ����ѯ
	 * 
	 * @param name ����ģ������
	 * @throws BaseException
	 */
	public List<BeanMenu> search(String name) throws BaseException {
		List<BeanMenu> list = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenu where menuName like '%" + name + "%'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				list = (List<BeanMenu>) query.list();
			} else {
				throw new BaseException("δ��ѯ�������Ϣ��");
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
		return list;
	}

	@Override
	public List<BeanMenu> loadMyMenu(BeanUser user) throws BaseException {
		List<BeanMenu> list = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenu where userId=" + user.getUserId();
			Query query = session.createQuery(hql);
//			if(query.list().size()!=0) {
			list = (List<BeanMenu>) query.list();
//			}else {
//				throw new BaseException("δ��ѯ�������Ϣ��");
//			}
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
		return list;
	}

	@Override
	public void adminAelete(BeanMenu menu) throws BaseException {
		// TODO Auto-generated method stub
		
	}
}
