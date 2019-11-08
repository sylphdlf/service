//package com.dlf.api.config;
//
//import com.dlf.api.filters.UserIdFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    @SuppressWarnings("unchecked")
//    public FilterRegistrationBean register(){
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new UserIdFilter());//添加过滤器
//        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
//        registration.setName("userIdFilter");//设置优先级
//        registration.setOrder(1);//设置优先级
//        return registration;
//    }
//}
