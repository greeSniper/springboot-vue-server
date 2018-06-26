package com.tangzhe.filter;

import com.alibaba.fastjson.JSONObject;
import com.tangzhe.util.JWTUtils;
import com.tangzhe.util.LoginInfoUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		String authorization = req.getHeader("Authorization");

		// 判断checkApis中是否包含当前请求的uri
		if (ApiAuthDataInit.checkApis.contains(req.getRequestURI())) {
			// 获取当前登录用户
			String userId = LoginInfoUtils.getLoginUserId(req);
			if (userId == null) {
				PrintWriter writer = resp.getWriter();
				String res = "请先登录";
				writer.write(res);
				writer.flush();
				return;
			}
		}

		// 判断token值是否合法
		if (StringUtils.isNotBlank(authorization) && !"null".equals(authorization)) {
			JWTUtils.JWTResult result = JWTUtils.getInstance().checkToken(authorization);
			if (!result.isStatus()) {
				// 非法请求
				PrintWriter writer = resp.getWriter();
				String res = JSONObject.toJSONString(result);
				writer.write(res);
				writer.flush();
				return;
			}
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}
