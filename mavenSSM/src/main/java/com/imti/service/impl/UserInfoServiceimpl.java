package com.imti.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imti.dao.UserInfoDao;
import com.imti.model.UserInfo;
import com.imti.service.UserInfoService;

/**@文件名: UserInfoServiceimpl.java
 * @类功能说明: 
 * @作者: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @日期: 2022年5月25日下午9:46:59
 * @修改说明:<br> 
 * <pre>
 * 	 <li>作者: ZhangWeiPeng</li> 
 * 	 <li>日期: 2022年5月25日下午9:46:59</li> 
 *	 <li>内容: </li>
 * </pre>
 */
@Service
public class UserInfoServiceimpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	public UserInfo login(UserInfo userInfo) {
		
		return userInfoDao.login(userInfo);
	}

	public int addUserInfo(UserInfo userInfo) {
		return userInfoDao.addUserInfo(userInfo);
	}

	public int updateUserInfo(UserInfo userInfo) {
		return userInfoDao.updateUserInfo(userInfo);
	}

	public List<UserInfo> findAllUserInfo(Map<String, Object> map) {
		return userInfoDao.findAllUserInfo(map);
	}

	public int deleteUserInfo(int user_id) {
		return userInfoDao.deleteUserInfo(user_id);
	}

	public UserInfo findNameByName(String user_name) {
		return userInfoDao.findNameByName(user_name);
	}

	public int findAllUserInfoCount(Map<String, Object> map) {
		
		return userInfoDao.findAllUserInfoCount(map);
	}

	
	public void exportExcel(List<UserInfo> userList, OutputStream outputStream) {
		//创建工作薄
		HSSFWorkbook hwb=new HSSFWorkbook();
		//创建合并单元格
		CellRangeAddress cellRangeAddress=new CellRangeAddress(0,0,0,4);
		//创建工作表
		HSSFSheet sheet=hwb.createSheet("用户信息表");
		//添加合并单元格
		sheet.addMergedRegion(cellRangeAddress);
		//创建第一行及单元格
		HSSFRow row1=sheet.createRow(0);
		HSSFCell cell1=row1.createCell(0);
		cell1.setCellValue("用户信息");
		//创建第二行及单元格
		HSSFRow row2=sheet.createRow(1);
		String[] row2Cell= {"编号","姓名","密码","年龄","创建时间","备注"};
		for (int i = 0; i < row2Cell.length; i++) {
			row2.createCell(i).setCellValue(row2Cell[i]);
		}
		//创建第三行及单元格
		if(userList!= null && userList.size()>0){
            for(int j=0 ; j<userList.size() ;j++){
                HSSFRow rowUser = sheet.createRow(j+2);
                rowUser.createCell(0).setCellValue(userList.get(j).getUser_id());
                rowUser.createCell(1).setCellValue(userList.get(j).getUser_name());
                rowUser.createCell(2).setCellValue(userList.get(j).getUser_pwd());
                rowUser.createCell(3).setCellValue(userList.get(j).getUser_age());
                rowUser.createCell(4).setCellValue(userList.get(j).getUser_createtime());
                rowUser.createCell(5).setCellValue(userList.get(j).getUser_remark());
            }
        }
		//输出
        try {
			hwb.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
