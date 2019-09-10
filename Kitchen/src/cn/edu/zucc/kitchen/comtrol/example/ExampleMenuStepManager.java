package cn.edu.zucc.kitchen.comtrol.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.itf.IMenuStepManager;
import cn.edu.zucc.kitchen.itf.IUserManager;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class ExampleMenuStepManager implements IMenuStepManager {
	/**
	 * ���Ӳ��ײ��裬��������ˮ�ų�ͻ��ܾ��޸��׳��쳣
	 * 
	 * @param menu                ����
	 * @param menuStepOrderId     ���ײ�����ˮ��
	 * @param menuStepDescription ��������
	 * @param filepath            ����ͼƬ
	 * @throws BaseException
	 * @throws IOException
	 */
	@Override
	public void add(BeanMenu menu, String menuStepDescription, int menuStepOrderId, String filepath)
			throws BaseException, IOException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuStep where menuId=" + menu.getMenuId() + " and menuStepOrderId="
					+ menuStepOrderId;
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				throw new BaseException("�ò�������Ѵ��ڣ������Ե�����Ӧ�Ĳ���˳��");
			}
//			if("".equals(menuStepDescription)||menuStepDescription==null) {
//				throw new BaseException("����д�����Ĳ�����Ϣ,����ͼƬ�����Ժ��ϴ�!");
//			}
			tx = session.beginTransaction();
			BeanMenuStep bfs = new BeanMenuStep();
			bfs.setMenu(menu);
			bfs.setMenuStepDescription(menuStepDescription);
			bfs.setMenuStepOrderId(menuStepOrderId);
			if ("".equals(filepath) != true && filepath != null) {
				FileInputStream fis = new FileInputStream(filepath);
				byte[] byteArray = new byte[fis.available()];
				fis.read(byteArray);
				bfs.setMenuStepImage(Hibernate.getLobCreator(session).createBlob(byteArray));
				fis.close();
			}
			session.save(bfs);
			tx.commit();
		} catch (BusinessException e) {
			if (session != null) {
				try {
					tx.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
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

	/**
	 * ɾ������
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	@Override
	public void delete(BeanMenuStep step) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanMenuStep bms = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
			session.delete(bms);
			String hql = "update BeanMenuStep set menuStepOrderId=menuStepOrderId-1 where menuId="
					+ step.getMenu().getMenuId() + " and menuStepOrderId>" + step.getMenuStepOrderId();
			session.createQuery(hql).executeUpdate();
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
	 * �޸Ĳ��ײ���
	 * 
	 * @param menu                ����
	 * @param menuStepDescription ��������
	 * @param menuStepImage       ����ͼƬ
	 * @throws BaseException
	 */
	@Override
	public void modify(BeanMenuStep Step, String menuStepDescription, String filepath) throws BaseException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			BeanMenuStep bfs = session.get(BeanMenuStep.class, Step.getMenuStepId());
			bfs.setMenuStepDescription(menuStepDescription);
			if ("".equals(filepath) != true && filepath != null) {
				FileInputStream fis = new FileInputStream(filepath);
				byte[] byteArray = new byte[fis.available()];
				fis.read(byteArray);
				bfs.setMenuStepImage(Hibernate.getLobCreator(session).createBlob(byteArray));
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
	 * ������ǰ�������ˮ�� ע�⣺���ݿ����menuStepOrderId�Ͻ�����Ψһ������������ǰ��������ֵ����һ��������ֵʱ���ܳ������ֵһ�������
	 * 
	 * @param step
	 * @throws BaseException
	 */
	@Override
	public void moveUp(BeanMenuStep step) throws BaseException {
		Session session = null;
		Transaction tx = null;
		int order = step.getMenuStepOrderId();
		try {
			session = HibernateUtil.getSession();
			if (step.getMenuStepOrderId() == 1)
				throw new BaseException("��ǰ�����Ѿ�����ǰ��");

			if (session.createQuery("from BeanMenuStep where menuId=" + step.getMenu().getMenuId()
					+ " and menuStepOrderid=" + (order - 1)).list().size() == 0) {
				tx = session.beginTransaction();
				BeanMenuStep bs = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
				bs.setMenuStepOrderId(step.getMenuStepOrderId() - 1);
				tx.commit();
			} else {
				tx = session.beginTransaction();
				BeanMenuStep bs1 = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
				bs1.setMenuStepOrderId(0);
				tx.commit();

				tx = session.beginTransaction();
				Query query = session.createQuery("from BeanMenuStep where menuId=" + step.getMenu().getMenuId()
						+ " and menuStepOrderid=" + (order - 1));
				BeanMenuStep bs = (BeanMenuStep) query.list().get(0);
				BeanMenuStep bs2 = (BeanMenuStep) session.get(BeanMenuStep.class, bs.getMenuStepId());
				bs2.setMenuStepOrderId(step.getMenuStepOrderId());
				tx.commit();

				tx = session.beginTransaction();
				BeanMenuStep bs3 = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
				bs3.setMenuStepOrderId(order - 1);
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
	public void moveDown(BeanMenuStep step) throws BaseException {
		Session session = null;
		Transaction tx = null;
		int order = step.getMenuStepOrderId();
		try {
			session = HibernateUtil.getSession();
			if (session.createQuery("from BeanMenuStep where menuStepOrderId=" + (step.getMenuStepOrderId() + 1)
					+ " and menuId=" + step.getMenu().getMenuId()).list().isEmpty())
				throw new BaseException("��ǰ�����Ѿ��������");

			if (session.createQuery("from BeanMenuStep where menuId=" + step.getMenu().getMenuId()
					+ " and menuStepOrderid=" + (order + 1)).list().size() == 0) {
				tx = session.beginTransaction();
				BeanMenuStep bs = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
				bs.setMenuStepOrderId(step.getMenuStepOrderId() + 1);
				tx.commit();
			} else {
				tx = session.beginTransaction();
				BeanMenuStep bs1 = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
				bs1.setMenuStepOrderId(0);
				tx.commit();

				tx = session.beginTransaction();
				Query query = session.createQuery("from BeanMenuStep where menuId=" + step.getMenu().getMenuId()
						+ " and menuStepOrderid=" + (order + 1));
				BeanMenuStep bs = (BeanMenuStep) query.list().get(0);
				BeanMenuStep bs2 = (BeanMenuStep) session.get(BeanMenuStep.class, bs.getMenuStepId());
				bs2.setMenuStepOrderId(step.getMenuStepOrderId());
				tx.commit();

				tx = session.beginTransaction();
				BeanMenuStep bs3 = (BeanMenuStep) session.get(BeanMenuStep.class, step.getMenuStepId());
				bs3.setMenuStepOrderId(order + 1);
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

	/**
	 * ��ȡ����ʳ��������Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<BeanMenuStep> loadAll(BeanMenu menu) throws BaseException {
		List<BeanMenuStep> result = new ArrayList<BeanMenuStep>();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuStep where menuId=" + menu.getMenuId() + " order by menuStepOrderId asc";
			Query query = session.createQuery(hql);
			result = (List<BeanMenuStep>) query.list();

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
	 * ���ײ����ѯ
	 * 
	 * @param step ���ײ���
	 * @throws BaseException
	 */
	@Override
	public List<BeanMenuStep> search(BeanMenuStep step) throws BaseException {
		List<BeanMenuStep> list = null;
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			String hql = "from BeanMenuStep where menuStepId=" + step.getMenuStepId();
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				list = (List<BeanMenuStep>) query.list();
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
}
