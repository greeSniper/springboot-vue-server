package com.tangzhe.filter;

import com.alibaba.fastjson.JSONObject;
import com.tangzhe.util.JWTUtils;
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

		if (StringUtils.isNotBlank(authorization)) {
			JWTUtils.JWTResult result = JWTUtils.getInstance().checkToken(authorization);
			if (!result.isStatus()) {
				// 非法请求
				PrintWriter writer = resp.getWriter();
				String res = JSONObject.toJSONString(result);
				writer.write(res);
				writer.flush();
				return;
			}
		} else {
			PrintWriter writer = resp.getWriter();
			writer.write("没有登录");
			writer.flush();
			return;
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}
