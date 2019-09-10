package cn.edu.zucc.kitchen.comtrol.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IFoodOrderManager;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodOrder;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanOrderDetail;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleFoodOrderManager implements IFoodOrderManager {

	@Override
	public void add(BeanUser user, BeanMenu menu, Timestamp deadline, String address, String phone)
			throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if (menu.getIngredients().size() == 0) {
				throw new BaseException("该菜谱没有食材用料!\n无法一键生成订单!");
			}
			BeanFoodOrder bf = new BeanFoodOrder();
			bf.setAddress(address);
			bf.setDeadline(deadline);
			Double.parseDouble(phone);
			bf.setPhone(phone);
			bf.setUser(user);
			bf.setStatus("下单");
			session.save(bf);
			tx.commit();
			String hql = "from BeanFoodOrder ORDER BY foodOrderId desc";
			Query query = session.createQuery(hql);
			bf = (BeanFoodOrder) query.list().get(0);
			
			tx = session.beginTransaction();
			for (BeanMenuIngredient mi : menu.getIngredients()) {
//				if (mi.getIngredientCount() == 0) {
//					od.setCount(1);
//					od.setPrice(mi.getFood().getFoodPrice());
//				} else {
//					od.setCount(Math.ceil(mi.getIngredientCount()));// 向上取整
//					od.setPrice(mi.getFood().getFoodPrice() * Math.ceil(mi.getIngredientCount()));
//				}
				if(mi.getFood().getFoodCount()<1) {
					JOptionPane.showMessageDialog(null, "“"+mi.getFood().getFoodName()+"”的库存不足没有为您自动添加到订单中", "提示", JOptionPane.WARNING_MESSAGE);
					continue;
				}
				BeanOrderDetail od = new BeanOrderDetail();
				od.setCount(1);
				od.setPrice(mi.getFood().getFoodPrice());
				od.setDiscount(1);
				od.setFoodOrder(bf);
				od.setFood(mi.getFood());
//				session.merge(od);
				session.save(od);
				session.flush();///////////
				session.clear();///////////
//				tx.commit();
//				session.merge(od);

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
		} catch (NumberFormatException e) {

			if (session != null) {
				try {
					tx.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			throw new BusinessException("电话号码不能有除阿拉伯数字外的其他字母!");
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
	public void delete(BeanFoodOrder order) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanFoodOrder bf = session.get(BeanFoodOrder.class, order.getFoodOrderId());
			if ("退货".equals(bf.getStatus()) || "配送".equals(bf.getStatus())) {
				throw new BaseException("目前订单状态为_" + bf.getStatus() + "_!不允许删除");
			}
			String hql = "from BeanOrderDetail where foodOrderId=" + order.getFoodOrderId();
			Query query = session.createQuery(hql);
			for (BeanOrderDetail b : (List<BeanOrderDetail>) query.list()) {
				BeanOrderDetail od = (BeanOrderDetail) session.get(BeanOrderDetail.class, b.getOrderDetailId());
				session.delete(od);
			}

			session.delete(bf);
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
	}

	@Override
	public void modify(BeanFoodOrder order, Timestamp deadline, String address, String phone) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanFoodOrder bf = session.get(BeanFoodOrder.class, order.getFoodOrderId());
			bf.setDeadline(deadline);
			bf.setDeadline(deadline);
			bf.setAddress(address);
			bf.setPhone(phone);
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
	public List<BeanFoodOrder> loadAll(BeanUser user) throws BaseException {
		List<BeanFoodOrder> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanFoodOrder where userId=" + user.getUserId();
			Query query = session.createQuery(hql);
			result = (List<BeanFoodOrder>) query.list();

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
	public void addFoodOrder(BeanUser user, BeanFood food, double count, Timestamp deadline, String address,
			String phone) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			if(food.getFoodCount()<count) {
				throw new BaseException("“"+food.getFoodName()+"”的库存不足不能创建订单");
			}
			BeanFoodOrder bf = new BeanFoodOrder();
			bf.setAddress(address);
			bf.setDeadline(deadline);
			Double.parseDouble(phone);
			bf.setPhone(phone);
			bf.setUser(user);
			bf.setStatus("下单");
			session.save(bf);
			tx.commit();
			String hql = "from BeanFoodOrder ORDER BY foodOrderId desc";
			Query query = session.createQuery(hql);
			bf = (BeanFoodOrder) query.list().get(0);
			tx = session.beginTransaction();
			BeanOrderDetail od = new BeanOrderDetail();
			od.setPrice(food.getFoodPrice());
			od.setCount(count);// 向上取整
			od.setPrice(food.getFoodPrice() * count);
			od.setDiscount(1);
			od.setFoodOrder(bf);
			od.setFood(food);
			session.save(od);
			session.flush();///////////
			session.clear();///////////
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
	}

	@Override
	public List<BeanOrderDetail> load(BeanFoodOrder order) throws BaseException {
		List<BeanOrderDetail> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanOrderDetail where foodOrderId=" + order.getFoodOrderId();
			Query query = session.createQuery(hql);
			result = (List<BeanOrderDetail>) query.list();

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
