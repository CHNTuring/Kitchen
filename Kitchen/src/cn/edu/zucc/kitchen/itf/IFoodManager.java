package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IFoodManager {
	/**
	 * ����ʳ�ģ� ѡ����Ӧ��ʳ�����࣬�����ݿ����Ѵ���ͬ��ʳ��������ʧ���׳��쳣
	 * 
	 * @param foodtype           ʳ������
	 * @param foodname           ʳ������
	 * @param foodprice          ʳ�ļ۸�
	 * @param foodcount          ʳ������
	 * @param fooddescription    ʳ������
	 * @param foodspecifications ʳ�Ĺ��
	 * @param filepath           ʳ��ͼƬ
	 * @return
	 * @throws BaseException
	 */
	public void add(BeanFoodType foodtype, String foodname, double foodprice, double foodcount, String fooddescription,
			String foodspecifications, String filepath) throws BaseException;

	/**
	 * ɾ��ʳ�ģ����ʳ���Ѿ����������û��Ѿ����û��͹���Ա����������ɾ��
	 * 
	 * @param food
	 * @throws BaseException
	 */
	public void delete(BeanFood food) throws BaseException;

	/**
	 * �޸�ʳ����Ϣ�������ȴ����ݿ�����ȡ�����Ϣ����Ӧ�ı��У������޸�
	 * 
	 * @param food
	 * @param foodtype           ʳ������
	 * @param foodname           ʳ������
	 * @param foodprice          ʳ�ļ۸�
	 * @param foodcount          ʳ������
	 * @param fooddescription    ʳ������
	 * @param foodspecifications ʳ�Ĺ��
	 * @param foodimage          ʳ��ͼƬ
	 * @return
	 * @throws BaseException
	 */
	public void modify(BeanFood food, BeanFoodType foodtype, String foodname, double foodprice, double foodcount,
			String fooddescription, String foodspecifications, String filepath) throws BaseException;

	/**
	 * ��ȡ����ʳ����Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanFood> loadAll() throws BaseException;
	
	/**
	 * ��ȡָ�������ʳ����Ϣ
	 * 
	 * @param type        ʳ�����
	 * @throws BaseException
	 */
	public List<BeanFood> load(BeanFoodType type) throws BaseException;
}
