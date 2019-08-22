package com.dlf.common.utils.user;

public class ThreadUser {
    private String id;
    private String username;
    private Integer type;
    private Integer isCheckImgCode;
    private String orgCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsCheckImgCode() {
        return isCheckImgCode;
    }

    public void setIsCheckImgCode(Integer isCheckImgCode) {
        this.isCheckImgCode = isCheckImgCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "ThreadUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", type=" + type +
                ", isCheckImgCode=" + isCheckImgCode +
                ", orgCode='" + orgCode + '\'' +
                '}';
    }
}
