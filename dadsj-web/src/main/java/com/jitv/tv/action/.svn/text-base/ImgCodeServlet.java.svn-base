package com.jitv.tv.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jitv.tv.utils.ValidCodeUtils;

public class ImgCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String codeToken = request.getParameter("codeToken");
		ValidCodeUtils codeUtils = ValidCodeUtils.create();
		ValidCodeUtils.setCodeToRedis(codeToken, codeUtils.getCode());
		codeUtils.write(response.getOutputStream());
	}
}
