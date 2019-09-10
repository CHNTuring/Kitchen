package cn.edu.zucc.kitchen.comtrol.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.MD5Util;
import cn.edu.zucc.kitchen.itf.IFoodManager;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleFoodManager implements IFoodManager {
	/**
	 * ����ʳ�ģ� ѡ����Ӧ��ʳ�����࣬�����ݿ����Ѵ���ͬ��ʳ��������ʧ���׳��쳣
	 * 
	 * @param foodtype           ʳ������
	 * @param foodname           ʳ������
	 * @param foodprice          ʳ�ļ۸�
	 * @param foodcount          ʳ������
	 * @param fooddescription    ʳ������
	 * @param foodspecifications ʳ�Ĺ��
	 * @param filepath           ʳ��ͼƬ
	 * @return
	 * @throws BaseException
	 */
	@Override
	public void add(BeanFoodType foodtype, String foodname, double foodprice, double foodcount, String fooddescription,
			String foodspecifications, String filepath) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if (foodprice < 0) {
				throw new BaseException("�۸���Ϊ����");
			}
			if (foodcount < 0) {
				throw new BaseException("��������Ϊ����");
			}
			String hql = "from BeanFood where foodName='" + foodname + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("��ʳ���Ѿ����ڣ�");
			} else {
				BeanFood bf = new BeanFood();
				bf.setFoodType(foodtype);
				bf.setFoodCount(foodcount);
				bf.setFoodDescription(fooddescription);
				bf.setFoodName(foodname);
				bf.setFoodPrice(foodprice);
				bf.setFoodSpecifications(foodspecifications);
				if (filepath == null || "".equals(filepath)) {
//					throw new BaseException("��ѡ��ͼƬ��");
				} else {
					FileInputStream fis = new FileInputStream(filepath);
					byte[] byteArray = new byte[fis.available()];
					fis.read(byteArray);
					bf.setFoodImage(Hibernate.getLobCreator(session).createBlob(byteArray));
					fis.close();
				}
				session.save(bf);
				tx.commit();
			}
		} catch (BusinessException | IOException e) {
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
	 * ɾ��ʳ�ģ����ʳ���Ѿ����������û��Ѿ����û��͹���Ա����������ɾ��
	 * 
	 * @param food
	 * @throws BaseException
	 */
	@Override
	public void delete(BeanFood food) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from ViewFoodInformationEntity where foodId=" + food.getFoodId();
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("��ʳ������ϵͳ������Ϣ�б����ã�������ɾ����");
			}
			BeanFood bf = (BeanFood) session.get(BeanFood.class, food.getFoodId());
			session.delete(bf);
			tx.commit();
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
	 * �޸�ʳ����Ϣ�������ȴ����ݿ�����ȡ�����Ϣ����Ӧ�ı��У������޸�
	 * 
	 * @param food
	 * @param foodtype           ʳ������
	 * @param foodname           ʳ������
	 * @param foodprice          ʳ�ļ۸�
	 * @param foodcount          ʳ������
	 * @param fooddescription    ʳ������
	 * @param foodspecifications ʳ�Ĺ��
	 * @param foodimage          ʳ��ͼƬ
	 * @return
	 * @throws BaseException
	 */
	@Override
	public void modify(BeanFood food, BeanFoodType foodtype, String foodname, double foodprice, double foodcount,
			String fooddescription, String foodspecifications, String filepath) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanFood bf = session.get(BeanFood.class, food.getFoodId());
			bf.setFoodType(foodtype);
			bf.setFoodCount(foodcount);
			bf.setFoodDescription(fooddescription);
			bf.setFoodName(foodname);
			bf.setFoodPrice(foodprice);
			bf.setFoodSpecifications(foodspecifications);
			if ("".equals(filepath) || filepath == null) {
			} else {
				FileInputStream fis = new FileInputStream(filepath);
				byte[] byteArray = new byte[fis.available()];
				fis.read(byteArray);
				bf.setFoodImage(Hibernate.getLobCreator(session).createBlob(byteArray));
				fis.close();
			}
			tx.commit();
		} catch (Exception e) {
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
	 * ��ȡ����ʳ����Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<BeanFood> loadAll() throws BaseException {
		List<BeanFood> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanFood";
			Query query = session.createQuery(hql);
			result = (List<BeanFood>) query.list();

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
	public List<BeanFood> load(BeanFoodType type) throws BaseException {
		List<BeanFood> result = new ArrayList<BeanFood>();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanFood where foodTypeId=" + type.getFoodTypeId();
			Query query = session.createQuery(hql);
			result = (List<BeanFood>) query.list();

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
}
