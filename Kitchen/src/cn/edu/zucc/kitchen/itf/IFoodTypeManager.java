package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodTypeManager {
	/**
	 * ����ʳ������
	 * 
	 * @param foodtypename        ʳ������
	 * @param foodtypedescription ��������
	 * @throws BaseException
	 */
	public void add(String foodtypename, String foodtypedescription) throws BaseException;

	/**
	 * ɾ��ʳ�����࣬���ʳ�������Ѿ���ʳ�����ã�������ɾ��
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	public void delete(BeanFoodType foodtype) throws BaseException;

	/**
	 * �޸�ʳ������
	 * 
	 * @param foodtype
	 * @param foodtypename        ʳ������
	 * @param foodtypedescription ��������
	 * @throws BaseException
	 */
	public void modify(BeanFoodType foodtype, String foodtypename, String foodtypedescription) throws BaseException;

	/**
	 * ��ȡ����ʳ��������Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFoodType> loadAll() throws BaseException;
}
