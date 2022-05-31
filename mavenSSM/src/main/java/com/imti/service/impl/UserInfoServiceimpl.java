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

/**@�ļ���: UserInfoServiceimpl.java
 * @�๦��˵��: 
 * @����: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @����: 2022��5��25������9:46:59
 * @�޸�˵��:<br> 
 * <pre>
 * 	 <li>����: ZhangWeiPeng</li> 
 * 	 <li>����: 2022��5��25������9:46:59</li> 
 *	 <li>����: </li>
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
		//����������
		HSSFWorkbook hwb=new HSSFWorkbook();
		//�����ϲ���Ԫ��
		CellRangeAddress cellRangeAddress=new CellRangeAddress(0,0,0,4);
		//����������
		HSSFSheet sheet=hwb.createSheet("�û���Ϣ��");
		//��Ӻϲ���Ԫ��
		sheet.addMergedRegion(cellRangeAddress);
		//������һ�м���Ԫ��
		HSSFRow row1=sheet.createRow(0);
		HSSFCell cell1=row1.createCell(0);
		cell1.setCellValue("�û���Ϣ");
		//�����ڶ��м���Ԫ��
		HSSFRow row2=sheet.createRow(1);
		String[] row2Cell= {"���","����","����","����","����ʱ��","��ע"};
		for (int i = 0; i < row2Cell.length; i++) {
			row2.createCell(i).setCellValue(row2Cell[i]);
		}
		//���������м���Ԫ��
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
		//���
        try {
			hwb.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
