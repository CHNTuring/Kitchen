package cn.edu.zucc.kitchen.itf;

import java.io.IOException;
import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuStepManager {
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
	public void add(BeanMenu menu, String menuStepDescription, int menuStepOrderId, String filepath)
			throws BaseException, IOException;

	/**
	 * ɾ������
	 * 
	 * @param foodtype
	 * @throws BaseException
	 */
	public void delete(BeanMenuStep step) throws BaseException;

	/**
	 * �޸Ĳ��ײ���
	 * 
	 * @param menu                ����
	 * @param menuStepDescription ��������
	 * @param filepath            ����ͼƬ
	 * @throws BaseException
	 */
	public void modify(BeanMenuStep Step, String menuStepDescription, String filepath) throws BaseException;

	/**
	 * ������ǰ�������ˮ�� ע�⣺���ݿ����menuStepOrderId�Ͻ�����Ψһ������������ǰ��������ֵ����һ��������ֵʱ���ܳ������ֵһ�������
	 * 
	 * @param step
	 * @throws BaseException
	 */
	public void moveUp(BeanMenuStep step) throws BaseException;

	public void moveDown(BeanMenuStep step) throws BaseException;

	/**
	 * ��ȡ����ʳ��������Ϣ
	 * 
	 * @param menu
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuStep> loadAll(BeanMenu menu) throws BaseException;
	
	/**
	 * ���ײ����ѯ
	 * 
	 * @param step        ���ײ���
	 * @throws BaseException
	 */
	public List<BeanMenuStep> search(BeanMenuStep step) throws BaseException;
}
