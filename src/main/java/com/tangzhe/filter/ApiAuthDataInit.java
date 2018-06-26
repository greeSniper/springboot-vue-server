package com.tangzhe.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tangzhe.annotation.EnableAuth;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目一启动，就调用这个方法获取所有需要权限拦截的接口，放到checkApis中
 */
@Component
@Configuration
public class ApiAuthDataInit implements ApplicationContextAware {

	/** 存放需要权限拦截的接口uri */
	public static List<String> checkApis = new ArrayList<>();

	/**
	 * 获取所有带有@RestController注解的类，
	 * 并获取该类下所有带有@EnableAuth注解的方法，
	 * 获取该方法@RequestMapping的uri路径，
	 * 将uri存入checkApis中
	 */
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String, Object> beanMap = ctx.getBeansWithAnnotation(RestController.class);
		if (beanMap != null) {
			for (Object bean : beanMap.values()) {
				Class<?> clz = bean.getClass();
				Method[] methods = clz.getMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(EnableAuth.class)) {
						String uri = getApiUri(clz, method);
						System.err.println(uri);
						checkApis.add(uri);
					}
				}
			}
		}
	}

	private String getApiUri(Class<?> clz, Method method) {
		StringBuilder uri = new StringBuilder();
		uri.append(clz.getAnnotation(RequestMapping.class).value()[0]);
		if (method.isAnnotationPresent(GetMapping.class)) {
			uri.append(method.getAnnotation(GetMapping.class).value()[0]);
		} else if (method.isAnnotationPresent(PostMapping.class)) {
			uri.append(method.getAnnotation(PostMapping.class).value()[0]);
		} else if (method.isAnnotationPresent(RequestMapping.class)) {
			uri.append(method.getAnnotation(RequestMapping.class).value()[0]);
		}
		return uri.toString();
	}

}