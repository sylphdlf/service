drop table `u_user`;
CREATE TABLE IF NOT EXISTS `u_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL COMMENT '用户名',
  `password` VARCHAR(60) NULL,
  `mobile` VARCHAR(60) DEFAULT NULL COMMENT '手机',
  `type` INT(2) DEFAULT '0' COMMENT '类型: 1:微信小程序',
  `status` INT(2) DEFAULT '0' COMMENT '状态：0：待审核；1：审核通过；2：审核不通过；',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `u_user_wx` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
  `nick_name` VARCHAR(20) NULL COMMENT '昵称',
  `open_id` VARCHAR(50) NOT NULL COMMENT 'openId',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='微信用户表';

CREATE TABLE IF NOT EXISTS `u_org` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(32) DEFAULT NULL COMMENT '组织机构代码',
  `parent_code` VARCHAR(32) DEFAULT '0' COMMENT '上级组织机构代码',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '组织机构名',
  `level` INT(2) DEFAULT NULL COMMENT '部门等级 1 一级部门  2 二级部门 3 三级部门 4 四级部门...',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

CREATE TABLE IF NOT EXISTS `u_org_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `org_id` BIGINT(20) DEFAULT NULL COMMENT '组织机构id',
  `user_id` BIGINT(20) DEFAULT '0' COMMENT '用户id',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_org_id` (`org_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户组织关联表';

CREATE TABLE IF NOT EXISTS `u_user_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

CREATE TABLE IF NOT EXISTS `u_org_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `org_id` BIGINT(20) NOT NULL COMMENT '组织机构id',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='组织角色关联表';

CREATE TABLE IF NOT EXISTS `u_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `type` INT(2) DEFAULT '0' COMMENT '类型: 1:超级管理员，2：其他角色',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE IF NOT EXISTS `u_role_fun` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `function_id` BIGINT(20) NOT NULL COMMENT '权限id',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

CREATE TABLE IF NOT EXISTS `u_fun` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(32) DEFAULT NULL COMMENT '权限code',
  `parent_code` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '父节点',
  `type` INT(2) NOT NULL COMMENT '权限类型 1 菜单、2按钮、3资源',
  `path` VARCHAR(128) DEFAULT NULL COMMENT '访问路径',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '权限名称',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '权限描述',
  `level` INT(2) DEFAULT NULL COMMENT '菜单层级：1：一级菜单、2：二级菜单',
  `icon` VARCHAR(255) DEFAULT NULL,
  `sort_value` INT(4) NULL COMMENT '菜单排序字段',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE IF NOT EXISTS `u_access_log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
  `username` VARCHAR(255) NOT NULL COMMENT '用户名',
  `ip_addr` VARCHAR(20) NOT NULL COMMENT '访问ip',
  `session_id` VARCHAR(50) NOT NULL COMMENT 'session',
  `url` VARCHAR(100) NOT NULL COMMENT '访问的接口',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
#   UNIQUE KEY `uk_code` (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='访问记录';