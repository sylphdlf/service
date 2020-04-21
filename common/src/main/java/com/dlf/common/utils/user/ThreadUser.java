package com.dlf.common.utils.user;

public class ThreadUser {

    private static final ThreadLocal<User> USER_LOCAL = new ThreadLocal<>();

    public static Long getUserId(){
        return USER_LOCAL.get().getUserId();
    }

    public static String getOrgCode(){
        return USER_LOCAL.get().getOrgCode();
    }

    public static void setUserLocal(User userLocal) {
        USER_LOCAL.set(userLocal);
    }
    public static void setUserId(Long userId) {
        User threadUser = new User();
        threadUser.setUserId(userId);
        USER_LOCAL.set(threadUser);
    }

    static class User {
        private Long userId;
        private String username;
        private String orgCode;

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
    }
}
