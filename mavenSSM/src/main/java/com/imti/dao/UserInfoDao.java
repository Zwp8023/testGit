package com.imti.dao;
/**@�ļ���: UserInfoDao.java
 * @�๦��˵��: 
 * @����: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @����: 2022��5��25������9:33:07
 * @�޸�˵��:<br> 
 * <pre>
 * 	 <li>����: ZhangWeiPeng</li> 
 * 	 <li>����: 2022��5��25������9:33:07</li> 
 *	 <li>����: </li>
 * </pre>
 */

import java.util.List;
import java.util.Map;

import com.imti.model.UserInfo;

public interface UserInfoDao {

	//��¼
	public UserInfo login(UserInfo userInfo);
	//���
	public int addUserInfo(UserInfo userInfo);
	//�޸�
	public int updateUserInfo(UserInfo userInfo);
	//ɾ��
	public int deleteUserInfo(int user_id);
	//��ѯ
	public List<UserInfo> findAllUserInfo(Map<String, Object> map);
	//��ѯ������
	public int findAllUserInfoCount(Map<String, Object> map);
	//У���Ƿ�����
	public UserInfo findNameByName(String user_name);
}
