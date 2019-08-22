package com.dlf.common.utils.user;

public class UserUtils {

    private static ThreadLocal<ThreadUser> USER_LOCAL = new ThreadLocal<ThreadUser>();

    public static ThreadUser getUserLocal() {
        return USER_LOCAL.get();
    }

    public static String getUserId(){
        return USER_LOCAL.get().getId();
    }

    public static String getOrgCode(){
        return USER_LOCAL.get().getOrgCode();
    }

    public static Integer getType(){
        return USER_LOCAL.get().getType();
    }

    public static void setUserLocal(ThreadUser userLocal) {
        USER_LOCAL.set(userLocal);
    }

}
