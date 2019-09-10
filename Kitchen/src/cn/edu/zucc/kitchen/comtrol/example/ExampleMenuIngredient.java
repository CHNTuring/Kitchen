package cn.edu.zucc.kitchen.comtrol.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleMenuIngredient implements IMenuIngredient {

	@Override
	public void add(BeanMenu menu, BeanFood food, double ingredientCount, String ingredientUnit) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanMenuIngredient where foodId=" + food.getFoodId() + " and menuId="+menu.getMenuId();
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("该食材用料已经存在，您可以修改相应的用料信息！");
			} else {
				BeanMenuIngredient bmi = new BeanMenuIngredient();
				bmi.setFood(food);
				bmi.setMenu(menu);
				bmi.setIngredientCount(ingredientCount);
				bmi.setIngredientUnit(ingredientUnit);
				bmi.setIngredientUnit(ingredientUnit);
				session.save(bmi);
				tx.commit();
			}
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
	}

	@Override
	public void delete(BeanMenuIngredient food) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanMenuIngredient bf = (BeanMenuIngredient) session.get(BeanMenuIngredient.class,
					food.getFoodIngredientId());
			session.delete(bf);
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
	public void modify(BeanMenuIngredient food, double ingredientCount, String ingredientUnit) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanMenuIngredient bf = (BeanMenuIngredient) session.get(BeanMenuIngredient.class,
					food.getFoodIngredientId());
			bf.setIngredientCount(ingredientCount);
			bf.setIngredientUnit(ingredientUnit);
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
	public List<BeanMenuIngredient> loadAll(BeanMenu menu) throws BaseException {
		List<BeanMenuIngredient> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuIngredient where menuId=" + menu.getMenuId();
			Query query = session.createQuery(hql);
			result = (List<BeanMenuIngredient>) query.list();

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
	public BeanMenuIngredient search(BeanMenuIngredient food) throws BaseException {
//		Session session = null;
//		BeanMenuIngredient food=null;
//		try {
//			session = HibernateUtil.getSession();
//			String hql = "from BeanMenuIngredient where menuId=" + menu.getMenuId();
//			Query query = session.createQuery(hql);
//			result = (List<BeanMenuIngredient>) query.list();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (session != null) {
//					session.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return food;
		return null;
	}

}
