CREATE TABLE IF NOT EXISTS s_schedule (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name varchar(100) not null comment '文件名(系统生成)',
    org_name varchar(100) null null comment '文件名(原始)',
    suffix varchar(10) null comment '后缀',
    path varchar(255) not null comment '相对路径',
    size bigint(20) not null comment '文件大小b',
    md5 varchar(32) not null comment 'md5',
    status INT(2) DEFAULT '0' COMMENT '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='文件表';

CREATE TABLE if not exists p_amap (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    type int(2) NOT NULL COMMENT '查询类型',
    origin VARCHAR(20) NOT NULL COMMENT '出发点',
    destination VARCHAR(20) NULL COMMENT '目的地',
    waypoints VARCHAR(20) NULL COMMENT '途径地(主要的)',
    time_cost INT(11) NOT NULL COMMENT '时间(秒)',
    distance INT(11) NOT NULL COMMENT '距离(米)',
    remarks VARCHAR(200) NULL COMMENT '备注',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    is_deleted TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
    PRIMARY KEY (id),
    INDEX type_index(type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通行时间统计';

CREATE TABLE if not exists p_weather (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    country VARCHAR(20) NOT NULL COMMENT '国家',
    province VARCHAR(20) NULL COMMENT '省份',
    city VARCHAR(20) NULL COMMENT '城市',
    weather VARCHAR(20) NULL COMMENT '当前天气',
    temperature INT(3) NULL COMMENT '当前温度',
    pm25 INT(3) NULL COMMENT '当前温度',
    humidity INT(3) NULL COMMENT '当前湿度',
    wind VARCHAR(30) NULL COMMENT '风力和风向',
    data_update_time DATETIME NULL COMMENT '气象站更新时间',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    remarks VARCHAR(200) NULL COMMENT '备注',
    is_deleted TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='天气';
