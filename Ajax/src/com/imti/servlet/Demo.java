package com.imti.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**@�ļ���: Demo.java
 * @�๦��˵��: 
 * @����: ZhangWeiPeng
 * @Email: 3155545142@qq.com
 * @����: 2022��5��10������3:18:28
 * @�޸�˵��:<br> 
 * <pre>
 * 	 <li>����: ZhangWeiPeng</li> 
 * 	 <li>����: 2022��5��10������3:18:28</li> 
 *	 <li>����: </li>
 * </pre>
 */
public class Demo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("urf-8");
		String str="��ڵĹ�����";
		int num=520;
		JSONObject object=new JSONObject();
		object.put("str1",str );
		object.put("num1", num);
		PrintWriter out=response.getWriter();
		out.println(object);
	}

}
