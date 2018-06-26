package com.tangzhe.filter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        ApiFilter httpBasicFilter = new ApiFilter();
        registrationBean.setFilter(httpBasicFilter);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

}
