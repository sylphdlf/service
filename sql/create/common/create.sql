CREATE TABLE IF NOT EXISTS f_file (
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

CREATE TABLE IF NOT EXISTS f_file_user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    file_id bigint(20) not null comment '文件id',
    name varchar(255) null comment '用户文件名',
    user_id bigint(20) not null comment '用户id',
    status INT(2) DEFAULT '0' COMMENT '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户文件关联表';

CREATE TABLE IF NOT EXISTS f_file_trash (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    file_id bigint(20) not null comment '源文件id',
    hold_time int(4) not null comment '保留时间(天)',
    status INT(2) DEFAULT '0' COMMENT '状态',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='文件垃圾箱';

CREATE TABLE IF NOT EXISTS s_sys_log (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) comment '日志名称',
    content text comment '日志内容',
    priority int(2) default 0 comment '优先级',
    remarks VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_user_id BIGINT(20) NULL COMMENT '创建人',
    create_time TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
    update_user_id BIGINT(20) NULL COMMENT '最后修改人',
    update_time TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
    org_code VARCHAR(32) NULL COMMENT '组织机构代码',
    is_deleted INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统日志表';