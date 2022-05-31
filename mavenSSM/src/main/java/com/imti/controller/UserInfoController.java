package com.imti.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.imti.model.UserInfo;
import com.imti.service.UserInfoService;

/**@�ļ���: UserInfoController.java
 * @�๦��˵��: 
 * @����: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @����: 2022��5��25������9:51:35
 * @�޸�˵��:<br> 
 * <pre>
 * 	 <li>����: ZhangWeiPeng</li> 
 * 	 <li>����: 2022��5��25������9:51:35</li> 
 *	 <li>����: </li>
 * </pre>
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	@RequestMapping("/login")
	public String login(UserInfo userInfo,HttpServletRequest request) throws UnsupportedEncodingException {
		//��ȡSession
		HttpSession session=request.getSession();

		//�ж�session���Ƿ��е�¼��Աֵ  ����Ϊ�� ֱ�ӷ��ص���ҳ��
		if (session.getAttribute("currentUserInfo")!=null) {
			return "main";
		}else {
			//����һ��װ��¼��Ա����Ϣ
			UserInfo resultUserInfo=userInfoService.login(userInfo);
			//�ж� resultUserInfo�Ƿ�Ϊ��
			if (resultUserInfo!=null) {
				//�ѵ�¼��Աװ�� session�� 
				session.setAttribute("currentUserInfo", resultUserInfo);
				//��¼�ɹ� ��ת��ҳ�� main
				return "main";
			}else {
				//��¼ʧ�ܲ���ת ����ʾ
				return "redirect:../login.html?error=1&user_name="+URLEncoder.encode(userInfo.getUser_name(),"UTF-8")+"&user_pwd="+userInfo.getUser_pwd();
			}
		}
	}
	//�û��б� 
	@RequestMapping("/userList")
	@ResponseBody
	public Map<String, Object> userlist(String seachName,int page,int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("seachName", seachName);
		map.put("start", (page-1)*rows);
		map.put("rows", rows);
		Map<String, Object> resultmap=new HashMap<String, Object>();
		resultmap.put("userlist", userInfoService.findAllUserInfo(map));
		resultmap.put("total", userInfoService.findAllUserInfoCount(map));
		return resultmap;
	}

	//��¼
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:../login.html";
	}

	//ɾ��
	@RequestMapping("delete")
	@ResponseBody
	public boolean delete(int user_id) {
		int result=userInfoService.deleteUserInfo(user_id);
		if (result>0) {
			return true;
		}
		else {
			return false;
		}
	}
	//��ӻ����޸�
	@RequestMapping("preSave")
	public String preSave(HttpServletRequest request,UserInfo userInfo) {
		HttpSession session=request.getSession();
		UserInfo adduserInfo=(UserInfo)session.getAttribute("currentUserInfo");
		if (userInfo.getUser_id()==null) {
			userInfo.setOpt_id(adduserInfo.getUser_id());
			userInfoService.addUserInfo(userInfo);
			return "redirect:userList.html";
		}else {
			userInfoService.updateUserInfo(userInfo);
			return "redirect:userList.html";
		}
	}
	//����ʱ�û��������ظ�
	@RequestMapping("nameByName")
	@ResponseBody
	public boolean nameByName(UserInfo userInfo) {
		UserInfo userInfo2=userInfoService.findNameByName(userInfo.getUser_name());
		if (userInfo2!=null) {
			return true;
		}else {
			return false;
		}
	}
	//����
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(String seachName,int page,int rows,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("seachName",seachName);
		int start=rows*(page-1);
		map.put("start", start);
		map.put("rows", rows);
		 //��ȡ����Դ
		List<UserInfo> userList=userInfoService.findAllUserInfo(map);
		//����excel
		try {
	        response.setHeader("Content-Disposition","attachment;filename="+new String("�û���Ϣ.xls".getBytes(),"ISO-8859-1"));
	        response.setContentType("application/x-excel;charset=UTF-8");
	        OutputStream outputStream;
			outputStream = response.getOutputStream();
			//����
	        userInfoService.exportExcel(userList,outputStream);
	        outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
