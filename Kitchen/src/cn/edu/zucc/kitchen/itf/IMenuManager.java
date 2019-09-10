package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuManager {
	/**
	 * ���Ӳ���
	 * 
	 * @param menuName        ��������
	 * @param menuDescription ��������
	 * @param filepath        ���׳�ƷͼƬ
	 * @param user            �����ṩ�û�
	 * @throws BaseException
	 */
	public void add(BeanUser user, String menuName, String menuDescrpition, String filepath) throws BaseException;

	/**
	 * ɾ�����ף�ͬʱɾ����֮����������ϼ�¼���ۼ�¼�Ͳ����¼���������û�
	 * 
	 * @param menu
	 * @throws BaseException
	 */
	public void delete(BeanMenu menu) throws BaseException;
	
	/**
	 * ����Աɾ�����׼��������Ϣ
	 * 
	 * @param menu
	 * @throws BaseException
	 */
	public void adminAelete(BeanMenu menu) throws BaseException;

	/**
	 * �޸Ĳ�����Ϣ
	 * 
	 * @param menuName        ��������
	 * @param menuDescription ��������
	 * @param filepath        ���׳�ƷͼƬ
	 * @throws BaseException
	 */
	public void modify(BeanMenu menu, String menuName, String menuDescription, String filepath)
			throws BaseException;

	/**
	 * ��ȡ���в�����Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenu> loadAll() throws BaseException;
	
	/**
	 * ����ģ����ѯ
	 * 
	 * @param name        ����ģ������
	 * @throws BaseException
	 */
	public List<BeanMenu> search(String name) throws BaseException;
	
	/**
	 * ��ȡ�Ҵ����Ĳ�����Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenu> loadMyMenu(BeanUser user) throws BaseException;
}
