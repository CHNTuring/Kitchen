package cn.edu.zucc.kitchen.itf;

import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IAdminUserManager {
	/**
	 * ��½ 1���������Ա�����ڻ�����������׳�һ���쳣 2�������֤�ɹ����򷵻ص�ǰ�û���Ϣ
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanAdminUser login(String username, String pwd) throws BaseException;

	/**
	 * ����Աɾ���û� ����û�������δ�ʹ��ʳ�Ķ��������׳��쳣
	 * 
	 * @param user ��ɾ�����û�
	 * @throws BaseException
	 */
	public void deleteUser(String user) throws BaseException;

	/**
	 * �޸����� ������ã����׳��쳣
	 * 
	 * @param user    ��ǰ�û�
	 * @param oldPwd  ԭ����
	 * @param newPwd  ������
	 * @param newPwd2 �ظ������������
	 */
	public void changePwd(BeanAdminUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException;

	/**
	 *��������Ա
	 * 
	 * @param user �¹���Ա�û���
	 * @throws BaseException
	 */
	public void reg(String user) throws BaseException;

	public void firstChangePwd(String userName, String string, String string2, String string3) throws BaseException;
}
