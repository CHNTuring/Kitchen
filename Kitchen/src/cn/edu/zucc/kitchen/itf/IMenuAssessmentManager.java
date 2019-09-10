package cn.edu.zucc.kitchen.itf;

import java.util.List;

import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public interface IMenuAssessmentManager {
	/**
	 * ���Ӳ���
	 * 
	 * @param user        �û�
	 * @param menu        ����
	 * @param assessment  ��������
	 * @param isCollected �ղر��
	 * @param menuScore   ����
	 * @throws BaseException
	 */
	public void add(BeanUser user, BeanMenu menu, String assessment, boolean isCollected, double menuScore)
			throws BaseException;

	/**
	 * ɾ������
	 * 
	 * @param assessment
	 * @throws BaseException
	 */
	public void delete(BeanMenuAssessment assessment) throws BaseException;

	/**
	 * �޸Ĳ�����Ϣ
	 * 
	 * @param a           ��������
	 * @param assessment  ��������
	 * @param isCollected �ղر��
	 * @param menuScore   ����
	 * @throws BaseException
	 */
	public void modify(BeanMenuAssessment a, String assessment, boolean isCollected, double menuScore)
			throws BaseException;

	/**
	 * ��ȡ���в���������Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuAssessment> loadAll(BeanMenu menu) throws BaseException;


	/**
	 * ��ȡ�Ҵ����Ĳ���������Ϣ
	 * 
	 * @return
	 * @throws BaseException
	 */
	public List<BeanMenuAssessment> loadMyMenu(BeanUser user) throws BaseException;
	
	public BeanMenuAssessment search(BeanMenu menu) throws BaseException;
}
