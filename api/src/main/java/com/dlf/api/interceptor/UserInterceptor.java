package com.dlf.api.interceptor;

import com.dlf.common.utils.user.ThreadUser;
import com.dlf.model.dao.user.AccessLogDao;
import com.dlf.model.po.user.AccessLog;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 获取访问的用户信息
 * 记录访问日志
 */
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    AccessLogDao accessLogDao;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final ImmutableList<String> logUrl = ImmutableList.of("/user/getByUsername");

    private static final String HEADER_USER_ID = "userId";
    private static final String HEADER_USERNAME = "username";
    private static final String HEADER_IP = "ip";
    private static final String DEFAULT_USER_ID = "0";
    private static final String SESSION_ID = "session_id";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        String userId = "";
        String url = request.getServletPath();
        String ip = request.getHeader(HEADER_IP);
        String sessionId = request.getHeader(SESSION_ID);
        if(StringUtils.isBlank(request.getHeader(HEADER_USER_ID))){
            userId = DEFAULT_USER_ID;
        } else {
            userId = request.getHeader(HEADER_USER_ID);
        }
        ThreadUser.setUser(Long.valueOf(userId), request.getHeader(HEADER_USERNAME), ip);
        //记录访问日志
        //fixme 放到消息队列中
        if(logUrl.contains(url)){
            String finalUserId = userId;
            executor.execute(() -> {
                AccessLog accessLog = new AccessLog();
                accessLog.setUserId(Long.valueOf(finalUserId));
                accessLog.setAccessCount(1);
                accessLog.setIpAddr(ip);
                accessLog.setSession_id(sessionId);
                accessLog.setUrl(url);
                accessLogDao.save(accessLog);
            });
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("after");
    }

}
