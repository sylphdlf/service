package com.dlf.api.interceptor;

import com.dlf.common.utils.user.ThreadUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 获取访问的用户信息，存入threadLocal中
 */
public class UserInterceptor implements HandlerInterceptor {

    private static final String HEADER_USER_ID = "userId";
    private static final String HEADER_USERNAME = "username";
    private static final String DEFAULT_USER_ID = "0";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        Optional.ofNullable(request.getHeader(HEADER_USER_ID))
                .map(t -> StringUtils.isEmpty(t) ? DEFAULT_USER_ID : t)
                .ifPresent(t -> ThreadUser.setUser(Long.valueOf(t), request.getHeader(HEADER_USERNAME)));
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("after");
    }

    public static void main(String[] args) {
        String temp = "";
        Optional.of(temp)
                .flatMap(t -> StringUtils.isEmpty(t) ? Optional.of(DEFAULT_USER_ID) : Optional.of(t))
                .ifPresent(System.out::println);
    }
}
