package cn.edu.zucc.kitchen.itf;

import java.io.IOException;
import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodOrder;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanOrderDetail;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodOrderManager {
	/**
	 * ���Ӳ��ײ��裬��������ˮ�ų�ͻ��ܾ��޸��׳��쳣
	 * 
	 * @param user     �û�
	 * @param deadline ��ֹʱ��
	 * @param address  ��ַ
	 * @param phone    �绰
	 * @throws BaseException
	 */
	public void add(BeanUser user, BeanMenu menu, java.sql.Timestamp deadline, String address, String phone)
			throws BaseException;

	/**
	 * ���Ӳ��ײ��裬��������ˮ�ų�ͻ��ܾ��޸��׳��쳣
	 * 
	 * @param user     �û�
	 * @param deadline ��ֹʱ��
	 * @param address  ��ַ
	 * @param phone    �绰
	 * @throws BaseException
	 */
	public void addFoodOrder(BeanUser user,BeanFood food, double count,java.sql.Timestamp deadline, String address, String phone)
			throws BaseException;
	
	/**
	 * ɾ������
	 * 
	 * @param order
	 * @throws BaseException
	 */
	public void delete(BeanFoodOrder order) throws BaseException;

	/**
	 * �޸Ĳ��ײ���
	 * 
	 * @param menu                ����
	 * @param menuStepDescription ��������
	 * @param filepath            ����ͼƬ
	 * @throws BaseException
	 */
	public void modify(BeanFoodOrder order, java.sql.Timestamp deadline, String address, String phone)
			throws BaseException;

	/**
	 * ��ȡ�û����ж�����Ϣ
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFoodOrder> loadAll(BeanUser user) throws BaseException;

	/**
	 * ���ײ����ѯ
	 * 
	 * @param step ���ײ���
	 * @throws BaseException
	 */
	public List<BeanOrderDetail> load(BeanFoodOrder order) throws BaseException;

}
