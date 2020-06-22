package com.dlf.common.utils.user;

public class ThreadUser {

    private static final ThreadLocal<User> USER_LOCAL = new ThreadLocal<>();

    public static Long getUserId(){
        return USER_LOCAL.get().getUserId();
    }

    public static User getUserLocal(){
        return USER_LOCAL.get();
    }

    public static String getOrgCode(){
        return USER_LOCAL.get().getOrgCode();
    }

    public static String getIp(){
        return USER_LOCAL.get().getIp();
    }

    public static void setUserLocal(User userLocal) {
        USER_LOCAL.set(userLocal);
    }

    public static void setUser(Long userId, String username, String ip, String url, String sessionId) {
        User threadUser = new User();
        threadUser.setUserId(userId);
        threadUser.setUsername(username);
        threadUser.setIp(ip);
        threadUser.setUrl(url);
        threadUser.setSessionId(sessionId);
        USER_LOCAL.set(threadUser);
    }

    public static void setIp(String ip){
        User threadUser = new User();
        threadUser.setIp(ip);
        USER_LOCAL.set(threadUser);
    }

    public static class User {
        private Long userId;
        private String username;
        private String orgCode;
        private String ip;
        private String url;
        private String sessionId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}
