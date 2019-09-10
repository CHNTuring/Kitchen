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
	 * 增加食材： 选择相应的食材种类，若数据库中已存在同名食材则增加失败抛出异常
	 * 
	 * @param foodtype           食材种类
	 * @param foodname           食材名称
	 * @param foodprice          食材价格
	 * @param foodcount          食材数量
	 * @param fooddescription    食材描述
	 * @param foodspecifications 食材规格
	 * @param filepath           食材图片
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
				throw new BaseException("价格不能为负！");
			}
			if (foodcount < 0) {
				throw new BaseException("数量不能为负！");
			}
			String hql = "from BeanFood where foodName='" + foodname + "'";
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("该食材已经存在！");
			} else {
				BeanFood bf = new BeanFood();
				bf.setFoodType(foodtype);
				bf.setFoodCount(foodcount);
				bf.setFoodDescription(fooddescription);
				bf.setFoodName(foodname);
				bf.setFoodPrice(foodprice);
				bf.setFoodSpecifications(foodspecifications);
				if (filepath == null || "".equals(filepath)) {
//					throw new BaseException("请选择图片！");
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
	 * 删除食材，如果食材已经被菜谱引用或已经被用户和管理员购买，则不允许删除
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
				throw new BaseException("该食材已在系统其他信息中被引用，不允许删除！");
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
	 * 修改食材信息，可以先从数据库中提取相关信息到相应的表单中，方便修改
	 * 
	 * @param food
	 * @param foodtype           食材种类
	 * @param foodname           食材名称
	 * @param foodprice          食材价格
	 * @param foodcount          食材数量
	 * @param fooddescription    食材描述
	 * @param foodspecifications 食材规格
	 * @param foodimage          食材图片
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
	 * 提取所有食材信息
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
