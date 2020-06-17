package com.dlf.api.interceptor;

import com.dlf.common.utils.user.ThreadUser;
import com.dlf.model.dao.user.AccessLogDao;
import com.dlf.model.dao.user.UserDao;
import com.dlf.model.po.user.AccessLog;
import com.dlf.model.po.user.User;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 获取访问的用户信息
 * 记录访问日志
 */
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    AccessLogDao accessLogDao;
    @Resource
    UserDao userDao;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final ImmutableList<String> logUrls = ImmutableList.of("/user/getByUsername");

    private static final String HEADER_USER_ID = "userId";
    private static final String HEADER_USERNAME = "username";
    private static final String HEADER_IP = "ip";
    private static final String DEFAULT_USER_ID = "-1";
    private static final String SESSION_ID = "session_id";

    /**
     * 取出用户属性，放到treadLocal中
     * @param request
     * @param response
     * @param o
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        String userId = "";
        String url = request.getServletPath();
        String ip = request.getHeader(HEADER_IP);
        String sessionId = request.getHeader(SESSION_ID);
        String username = request.getHeader(HEADER_USERNAME);
        if(StringUtils.isBlank(request.getHeader(HEADER_USER_ID))){
            userId = DEFAULT_USER_ID;
        } else {
            userId = request.getHeader(HEADER_USER_ID);
        }
        ThreadUser.setUser(Long.valueOf(userId), username, ip, url, sessionId);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("post");
        //记录访问日志
        //fixme 放到消息队列中
        ThreadUser.User userLocal = ThreadUser.getUserLocal();
        if(logUrls.contains(userLocal.getUrl())){
            executor.execute(() -> {
                User user = new User();
                user.setUsername(userLocal.getUsername());
                userDao.findOne(Example.of(user)).ifPresent(t -> {
                    AccessLog accessLog = new AccessLog();
                    accessLog.setUserId(t.getId());
                    accessLog.setIpAddr(userLocal.getIp());
                    accessLogDao.findOne(Example.of(accessLog)).map(a -> {
                        a.setAccessCount(a.getAccessCount() + 1);
                        return accessLogDao.saveAndFlush(a);
                    }).orElseGet(() -> {
                        accessLog.setAccessCount(1);
                        accessLog.setUrl(userLocal.getUrl());
                        accessLog.setSession_id(userLocal.getSessionId());
                        accessLog.setIpAddr(userLocal.getIp());
                        return accessLogDao.saveAndFlush(accessLog);
                    });
                });
            });
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("after");
    }

}
