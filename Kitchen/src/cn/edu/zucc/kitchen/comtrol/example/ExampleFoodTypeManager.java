package cn.edu.zucc.kitchen.comtrol.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IFoodTypeManager;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleFoodTypeManager implements IFoodTypeManager {
	/**
	 * ����ʳ������
	 * 
	 * @param foodtypename        ʳ������
	 * @param foodtypedescription ��������
	 * @throws BaseException
	 */
	@Override
	public void add(String foodtypename, String foodtypedescription) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanFoodType where foodTypeName='" + foodtypename + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("��ʳ�������Ѿ����ڣ�");
			} else {
				BeanFoodType bft = new BeanFoodType();
				bft.setFoodTypeName(foodtypename);
				bft.setFoodTypeDescription(foodtypedescription);
				session.save(bft);
			}
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
	 * ɾ��ʳ�����࣬���ʳ�������Ѿ���ʳ�����ã�������ɾ��
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	@Override
	public void delete(BeanFoodType foodtype) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanFood where foodTypeId=" + foodtype.getFoodTypeId();
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("����ɾ�����ڸ�ʳ������ʳ�");
			}
			BeanFoodType bft = (BeanFoodType) session.get(BeanFoodType.class, foodtype.getFoodTypeId());
			session.delete(bft);
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
	 * �޸�ʳ������
	 * 
	 * @param foodtype
	 * @param foodtypename        ʳ������
	 * @param foodtypedescription ��������
	 * @throws BaseException
	 */
	@Override
	public void modify(BeanFoodType foodtype, String foodtypename, String foodtypedescription) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanFoodType where foodTypeName='" + foodtypename + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				if(!foodtype.getFoodTypeName().equals(foodtypename))
					throw new BaseException("��ʳ�������Ѿ����ڣ�");
			}
			BeanFoodType bft = session.get(BeanFoodType.class, foodtype.getFoodTypeId());
			bft.setFoodTypeDescription(foodtypedescription);
			bft.setFoodTypeName(foodtypename);
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
	 * ��ȡ����ʳ��������Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<BeanFoodType> loadAll() throws BaseException {
		List<BeanFoodType> result = new ArrayList<BeanFoodType>();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanFoodType";
			Query query = session.createQuery(hql);
			result = (List<BeanFoodType>) query.list();

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
