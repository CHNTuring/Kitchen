package cn.edu.zucc.kitchen.comtrol.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleFoodPurchase implements IFoodPurchase {

	@Override
	public void add(BeanFood food, double count) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanFoodPurchase bm = new BeanFoodPurchase();
			bm.setAdminUser(BeanAdminUser.currentLoginUser);
			bm.setFood(food);
			bm.setPurchaseCount(count);
			bm.setPurchaseStatus("下单");
			session.save(bm);
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

	@Override
	public void delete(BeanFoodPurchase purchase) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanFoodPurchase bm = (BeanFoodPurchase) session.get(BeanFoodPurchase.class, purchase.getFoodPurchaseId());
			session.delete(bm);
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

	@Override
	public void modify(BeanFoodPurchase purchase, BeanFood food, double count ,String status) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanFoodPurchase bm = (BeanFoodPurchase) session.get(BeanFoodPurchase.class, purchase.getFoodPurchaseId());
			bm.setFood(food);
			bm.setPurchaseCount(count);
			if("入库".equals(status)&&!"入库".equals(purchase.getPurchaseStatus())) {
				BeanFood bf=(BeanFood)session.get(BeanFood.class, purchase.getFood().getFoodId());
				bf.setFoodCount(bf.getFoodCount()+count);
			}
			if(!"入库".equals(status)&&"入库".equals(purchase.getPurchaseStatus())) {
				BeanFood bf=(BeanFood)session.get(BeanFood.class, purchase.getFood().getFoodId());
				bf.setFoodCount(bf.getFoodCount()-count);
			}
			bm.setPurchaseStatus(status);
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

	@Override
	public List<BeanFoodPurchase> loadAll() throws BaseException {
		List<BeanFoodPurchase> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanFoodPurchase";
			Query query = session.createQuery(hql);
			result = (List<BeanFoodPurchase>) query.list();

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
