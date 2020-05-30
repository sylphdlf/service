CREATE TABLE IF NOT EXISTS s_dict (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name varchar(50) not null comment '名称',
    dict_key VARCHAR(50) NOT NULL COMMENT '键',
    dict_value VARCHAR(50) NOT NULL COMMENT '值',
    parent_id BIGINT(20) DEFAULT NULL COMMENT '父ID',
    sort INT(3) DEFAULT 0 COMMENT '排序',
    status int(2) default 0 comment '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_key` (dict_key)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='字典表';