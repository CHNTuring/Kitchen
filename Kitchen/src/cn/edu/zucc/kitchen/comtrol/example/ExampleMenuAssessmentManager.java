package cn.edu.zucc.kitchen.comtrol.example;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IFoodTypeManager;
import cn.edu.zucc.kitchen.itf.IMenuAssessmentManager;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleMenuAssessmentManager implements IMenuAssessmentManager {

	@Override
	public void add(BeanUser user, BeanMenu menu, String assessment, boolean isCollected, double menuScore)
			throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			String hql = "from BeanMenuAssessment where menuId=" + menu.getMenuId() + " and userId=" + user.getUserId();
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("您已经评价过该菜谱!\n您可以修改相关的评价信息!");
			} else {
				BeanMenu bm = session.get(BeanMenu.class, menu.getMenuId());
				if (isCollected) {
					bm.setMenuCollectedCount(menu.getMenuCollectedCount() + 1);
				}
				query = session
						.createQuery("select sum(menuScore) from BeanMenuAssessment where menuId=" + menu.getMenuId());
				double sum = 0;
				if (query.list().get(0) != null) {
					sum = (Double) query.list().get(0);
					bm.setMenuScoreCount((sum + menuScore)
							/ (session.createQuery("from BeanMenuAssessment where menuId=" + menu.getMenuId()).list()
									.size() + 1));
				}

				bm.setMenuScoreCount(menuScore);
				bm.setMenuBrowseCount(bm.getMenuBrowseCount() + 1);
				BeanMenuAssessment bft = new BeanMenuAssessment();
				bft.setMenu(menu);
				bft.setUser(user);
				bft.setIsBrowsed(true);
				bft.setIsCollected(isCollected);
				bft.setMenuAssessmentContent(assessment);
				bft.setMenuScore(menuScore);
				session.save(bft);
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
	}

	@Override
	public void delete(BeanMenuAssessment assessment) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			BeanMenu bm = session.get(BeanMenu.class, assessment.getMenu().getMenuId());
			Query query = session.createQuery(
					"select sum(menuScore) from BeanMenuAssessment where menuId=" + assessment.getMenu().getMenuId());
			double sum = (Double) query.list().get(0);
			if (session.createQuery("from BeanMenuAssessment where menuId=" + assessment.getMenu().getMenuId()).list()
					.size() == 1) {
				bm.setMenuScoreCount(0);
			} else {
				bm.setMenuScoreCount((sum - assessment.getMenuScore()) / (session
						.createQuery("from BeanMenuAssessment where menuId=" + assessment.getMenu().getMenuId()).list()
						.size() - 1));
			}

			if (assessment.getIsCollected()) {
				bm.setMenuCollectedCount(bm.getMenuCollectedCount() - 1);
			}

			BeanMenuAssessment bft = (BeanMenuAssessment) session.get(BeanMenuAssessment.class,
					assessment.getMenuAssessmentId());
			session.delete(bft);
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
	public void modify(BeanMenuAssessment a, String assessment, boolean isCollected, double menuScore)
			throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			BeanMenu bm = session.get(BeanMenu.class, a.getMenu().getMenuId());
			Query query = session.createQuery(
					"select sum(menuScore) from BeanMenuAssessment where menuId=" + a.getMenu().getMenuId());
			double sum = (Double) query.list().get(0);
			int size=session.createQuery("from BeanMenuAssessment where menuId=" + a.getMenu().getMenuId()).list()
					.size();
			if(size==1) {
				sum=0;
			}else {
				sum=sum - a.getMenuScore();
			}
			bm.setMenuScoreCount((sum + menuScore) / (size));
			if (a.getIsCollected() == true && isCollected==false) {
				bm.setMenuCollectedCount(bm.getMenuCollectedCount() - 1);
			}
			if (a.getIsCollected() == false && isCollected==true) {
				bm.setMenuCollectedCount(bm.getMenuCollectedCount() + 1);
			}

			BeanMenuAssessment bft = session.get(BeanMenuAssessment.class, a.getMenuAssessmentId());
			bft.setIsCollected(isCollected);
			bft.setMenuScore(menuScore);
			bft.setMenuAssessmentContent(assessment);
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
	public List<BeanMenuAssessment> loadAll(BeanMenu menu) throws BaseException {
		List<BeanMenuAssessment> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuAssessment where menuId=" + menu.getMenuId();
			Query query = session.createQuery(hql);
			result = (List<BeanMenuAssessment>) query.list();

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
	public List<BeanMenuAssessment> loadMyMenu(BeanUser user) throws BaseException {
		List<BeanMenuAssessment> result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuAssessment where userId=" + user.getUserId();
			Query query = session.createQuery(hql);
			result = (List<BeanMenuAssessment>) query.list();

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

	public BeanMenuAssessment search(BeanMenu menu) throws BaseException {
		BeanMenuAssessment result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuAssessment where userId=" + BeanUser.currentLoginUser.getUserId()
					+ " and menuId=" + menu.getMenuId();
			Query query = session.createQuery(hql);
			result = (BeanMenuAssessment) query.list().get(0);

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
