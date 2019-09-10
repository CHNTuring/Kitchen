package cn.edu.zucc.kitchen.itf;

import java.io.IOException;
import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuIngredient {
	/**
	 * ���Ӳ������ϣ���������ˮ�ų�ͻ��ܾ��޸��׳��쳣
	 * 
	 * @param menu            ����
	 * @param food            ʳ��
	 * @param ingredientCount ��������
	 * @param ingredientUnit  ���ϵ�λ
	 * @throws BaseException
	 */
	public void add(BeanMenu menu, BeanFood food, double ingredientCount, String ingredientUnit) throws BaseException;

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	public void delete(BeanMenuIngredient food) throws BaseException;

	/**
	 * �޸�������Ϣ
	 * 
	 * @param food                ����
	 * @param ingredientCount  ������
	 * @param ingredientUnit            ���ϵ�λ
	 * @throws BaseException
	 */
	public void modify(BeanMenuIngredient food, double ingredientCount, String ingredientUnit) throws BaseException;


	/**
	 * ��ȡ����������Ϣ
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuIngredient> loadAll(BeanMenu menu) throws BaseException;
	
	/**
	 * ��ȡָ��������Ϣ
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public BeanMenuIngredient search(BeanMenuIngredient food) throws BaseException;

}
