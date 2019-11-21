package com.dlf.common.utils.user;

public class UserUtils {

    private static ThreadLocal<ThreadUser> USER_LOCAL = new ThreadLocal<ThreadUser>();

    public static ThreadUser getUserLocal() {
        return USER_LOCAL.get();
    }

    public static Long getUserId(){
        return USER_LOCAL.get().getUserId();
    }

    public static String getOrgCode(){
        return USER_LOCAL.get().getOrgCode();
    }

    public static void setUserLocal(ThreadUser userLocal) {
        USER_LOCAL.set(userLocal);
    }
    public static void setUserId(Long userId) {
        ThreadUser threadUser = new ThreadUser();
        threadUser.setUserId(userId);
        USER_LOCAL.set(threadUser);
    }
}
