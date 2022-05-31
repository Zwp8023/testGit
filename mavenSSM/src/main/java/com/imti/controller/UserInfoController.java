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

/**@文件名: UserInfoController.java
 * @类功能说明: 
 * @作者: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @日期: 2022年5月25日下午9:51:35
 * @修改说明:<br> 
 * <pre>
 * 	 <li>作者: ZhangWeiPeng</li> 
 * 	 <li>日期: 2022年5月25日下午9:51:35</li> 
 *	 <li>内容: </li>
 * </pre>
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	@RequestMapping("/login")
	public String login(UserInfo userInfo,HttpServletRequest request) throws UnsupportedEncodingException {
		//获取Session
		HttpSession session=request.getSession();

		//判断session里是否有登录人员值  若不为空 直接返回到主页面
		if (session.getAttribute("currentUserInfo")!=null) {
			return "main";
		}else {
			//声明一个装登录人员的信息
			UserInfo resultUserInfo=userInfoService.login(userInfo);
			//判断 resultUserInfo是否为空
			if (resultUserInfo!=null) {
				//把登录人员装进 session中 
				session.setAttribute("currentUserInfo", resultUserInfo);
				//登录成功 跳转主页面 main
				return "main";
			}else {
				//登录失败不跳转 并提示
				return "redirect:../login.html?error=1&user_name="+URLEncoder.encode(userInfo.getUser_name(),"UTF-8")+"&user_pwd="+userInfo.getUser_pwd();
			}
		}
	}
	//用户列表 
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

	//登录
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:../login.html";
	}

	//删除
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
	//添加或者修改
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
	//新增时用户名不可重复
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
	//导出
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(String seachName,int page,int rows,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("seachName",seachName);
		int start=rows*(page-1);
		map.put("start", start);
		map.put("rows", rows);
		 //获取数据源
		List<UserInfo> userList=userInfoService.findAllUserInfo(map);
		//导出excel
		try {
	        response.setHeader("Content-Disposition","attachment;filename="+new String("用户信息.xls".getBytes(),"ISO-8859-1"));
	        response.setContentType("application/x-excel;charset=UTF-8");
	        OutputStream outputStream;
			outputStream = response.getOutputStream();
			//导出
	        userInfoService.exportExcel(userList,outputStream);
	        outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
