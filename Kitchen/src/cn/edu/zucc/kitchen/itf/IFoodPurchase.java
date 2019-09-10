package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodPurchase {
	/**
	 * ����ʳ������
	 * 
	 * @param food  ʳ��
	 * @param count ʳ������
	 * @throws BaseException
	 */
	public void add(BeanFood food, double count) throws BaseException;

	/**
	 * ɾ��ʳ�����࣬���ʳ�������Ѿ���ʳ�����ã�������ɾ��
	 * 
	 * @param purchase
	 * @throws BaseException
	 */
	public void delete(BeanFoodPurchase purchase) throws BaseException;

	/**
	 * �޸�ʳ��
	 * 
	 * @param purchase
	 * @param food        ʳ������
	 * @param count ��������
	 * @throws BaseException
	 */
	public void modify(BeanFoodPurchase purchase, BeanFood food, double count,String status) throws BaseException;

	/**
	 * ��ȡ����ʳ�Ĳɹ���Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFoodPurchase> loadAll() throws BaseException;
}
