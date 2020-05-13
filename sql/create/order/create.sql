CREATE TABLE IF NOT EXISTS o_order (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    code varchar(20) not null comment '订单编号',
    status INT(2) DEFAULT '0' COMMENT '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='订单表';

CREATE TABLE IF NOT EXISTS o_order_sub (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    order_id BIGINT(20) NOT NULL comment '订单id',
    code varchar(23) not null comment '子单编号',
    product_id bigint(20) not null comment '商品id',
    product_num int(5) not null default 1 comment '商品数量',
    status INT(2) DEFAULT '0' COMMENT '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='订单表';

CREATE TABLE IF NOT EXISTS o_order_outer (
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   user_id bigint(20) not null comment '用户id',
   code varchar(30) not null comment '订单编号',
   platform varchar(20) null comment '平台',
   mobile VARCHAR(60) DEFAULT NULL COMMENT '手机',
   verify_code VARCHAR(10) NULL COMMENT '查看码',
   status INT(2) DEFAULT '0' COMMENT '状态',
   remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
   create_user_id BIGINT(20) NULL COMMENT '创建人',
   create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
   update_user_id BIGINT(20) NULL COMMENT '最后修改人',
   update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
   org_code VARCHAR(32) NULL COMMENT '组织机构代码',
   is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
   PRIMARY KEY (id),
   unique key uk_code (code)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='外部订单表';

CREATE TABLE IF NOT EXISTS o_order_file (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    order_id bigint(20) not null comment '订单id或外部订单id',
    file_id varchar(200) not null comment '文件id集合，f_user_file表id',
    status INT(2) DEFAULT '0' COMMENT '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='订单表';
