package com.dlf.model.enums.redis;

public enum RedisPrefixEnums {
    //结尾是PRE的参数，为键的前缀，需要加后缀
    MESSAGE_PRE("message_", "验证码前缀"),
    REGISTER_MESSAGE_PRE("register_message_","注册验证码"),
    PASSWORD_MESSAGE_PRE("password_message_","忘记密码验证码"),
    SEND_LIMIT_PER_TIME_PRE("send_limit_per_time_","同一号码2次发送的间隔"),
    SEND_LIMIT_MAX_TIME_PRE("send_limit_max_time_","最大限制次数"),
    USER_LOGIN_CACHE_PRE("user_login_cache_","用户登录缓存用户名密码"),
    USER_NAME_COMPARE_LIST_PRE("user_name_compare_list_","注册用户名比较"),
    ORG_TREE_NODE("org_tree_node", "组织机构树"),
    ORG_TREE_NODE_PARENT_PRE("org_tree_node_parent_", "组织机构树父节点"),
    FUN_TREE_NODE("fun_tree_node", "权限树"),
    FUN_LIST("fun_list", "资源列表"),
    MARKET_NEWEST("market_newest_", "最新数据"),
    USER_VERIFY("user_verify_", "用户验证"),
    ORDER_COUNT("order_count", "交易笔数"),
    IMG_CODE("img_code_", "图形验证码"),
    VEHICLE_SUB_LIST("vehicle_sub_list", "订阅路线列表"),
    VEHICLE_SUB_PAGE("vehicle_sub_page", "订阅路线分页"),
    USER_ORDER_PREFER("user_order_prefer", "路线优选列表"),
    BLACKLIST("blacklist", "黑名单列表"),
    BLACKLIST_PRE("blacklist_", "黑名单前缀"),
    VEHICLE_CARRY_STATUS_LIST("vehicle_carry_status_list", "司机运载状态列表"),
    VEHICLE_CARRY_STATUS_LIST_PRE("vehicle_carry_status_", "司机运载状态前缀"),
    ORDER_LOCATION_LIST("order_location_list", "货源坐标列表"),
    ;
    private String code;
    private String desc;

    RedisPrefixEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
