CREATE TABLE IF NOT EXISTS `s_server` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL COMMENT '名称',
  `ip` VARCHAR(20) NOT NULL COMMENT 'ip',
  `port` int(6) NOT NULL COMMENT '端口',
  `source_path` VARCHAR(100) NOT NULL COMMENT '源码路径',
  `server_path` VARCHAR(100) NOT NULL COMMENT '服务地址',
  `config_path` VARCHAR(100) NOT NULL COMMENT '配置文件地址',
  `status` INT(2) DEFAULT '0' COMMENT '状态',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `org_code` VARCHAR(32) NULL COMMENT '组织机构代码',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='服务器信息';
