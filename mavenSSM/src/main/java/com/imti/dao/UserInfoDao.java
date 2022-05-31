package com.imti.dao;
/**@文件名: UserInfoDao.java
 * @类功能说明: 
 * @作者: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @日期: 2022年5月25日下午9:33:07
 * @修改说明:<br> 
 * <pre>
 * 	 <li>作者: ZhangWeiPeng</li> 
 * 	 <li>日期: 2022年5月25日下午9:33:07</li> 
 *	 <li>内容: </li>
 * </pre>
 */

import java.util.List;
import java.util.Map;

import com.imti.model.UserInfo;

public interface UserInfoDao {

	//登录
	public UserInfo login(UserInfo userInfo);
	//添加
	public int addUserInfo(UserInfo userInfo);
	//修改
	public int updateUserInfo(UserInfo userInfo);
	//删除
	public int deleteUserInfo(int user_id);
	//查询
	public List<UserInfo> findAllUserInfo(Map<String, Object> map);
	//查询总条数
	public int findAllUserInfoCount(Map<String, Object> map);
	//校验是否重名
	public UserInfo findNameByName(String user_name);
}
