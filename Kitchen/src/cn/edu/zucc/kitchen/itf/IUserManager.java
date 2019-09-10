package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IUserManager {
	/**
	 * ע�᣺ Ҫ���û��������ظ�������Ϊ�� ����������������һ�£����벻��Ϊ�� ���ע��ʧ�ܣ����׳��쳣
	 * 
	 * @param username �û���
	 * @param pwd      ����
	 * @param pwd2     �ظ����������
	 * @param sex      �Ա�
	 * @param phone    �绰
	 * @param city     ���ڳ���
	 * @param email
	 * @return
	 * @throws BaseException
	 */
	public BeanUser reg(String username, String pwd, String pwd2, String sex, String phone, String eamil, String city)
			throws BaseException;

	/**
	 * ע�᣺ Ҫ���û��������ظ�������Ϊ�� ����������������һ�£����벻��Ϊ�� ���ע��ʧ�ܣ����׳��쳣
	 * 
	 * @param username �û���
	 * @param pwd      ����
	 * @param pwd2     �ظ����������
	 * @param sex      �Ա�
	 * @param phone    �绰
	 * @param city     ���ڳ���
	 * @param email
	 * @return
	 * @throws BaseException
	 */
	public void modify(BeanUser user, String username, String sex, String phone, String eamil, String city)
			throws BaseException;

	/**
	 * ��½ 1������û������ڻ�����������׳�һ���쳣 2�������֤�ɹ����򷵻ص�ǰ�û���Ϣ
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanUser login(String username, String pwd) throws BaseException;

	/**
	 * �޸����� ������ã����׳��쳣
	 * 
	 * @param user    ��ǰ�û�
	 * @param oldPwd  ԭ����
	 * @param newPwd  ������
	 * @param newPwd2 �ظ������������
	 */
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException;

	/**
	 * �û�ѡ������ע�� ����û�������δ�ʹ��ʳ�Ķ��������׳��쳣
	 * 
	 * @param user ��ɾ�����û�
	 * @throws BaseException
	 */
	public void logoutForever(String user) throws BaseException;

	/**
	 * ������ͨ�û�
	 * 
	 * @param user �û�
	 * @throws BaseException
	 */
	public List<BeanUser> loadAll() throws BaseException;

	/**
	 * ��������
	 * 
	 * @param user �û�
	 * @throws BaseException
	 */
	public void resetPwd(String user) throws BaseException;

	/**
	 * ɾ���û�
	 * 
	 * @param user �û�
	 * @throws BaseException
	 */
	public void delete(String user) throws BaseException;

	/**
	 * ģ����ѯ�û�
	 * 
	 * @param user �û�
	 * @throws BaseException
	 */
	public List<BeanUser> search(String user) throws BaseException;
}
