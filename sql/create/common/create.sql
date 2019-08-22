create table f_file(
  id VARCHAR2(32) primary key ,
  name VARCHAR2(255) NOT NULL,
  suffix VARCHAR2(10),
  path_root VARCHAR2(255),
  path_relative VARCHAR2(255) NOT NULL,
  file_size NUMERIC(20) NOT NULL,
  md5 VARCHAR2(32) NOT NULL,
  status numeric(2),
  remarks VARCHAR2(200),
  create_time VARCHAR2(20) not null,
  create_user_id VARCHAR2(32),
  update_time VARCHAR2(20),
  update_user_id VARCHAR2(32),
  is_deleted VARCHAR2(2)
);

create table f_user_file(
  id VARCHAR2(32) primary key ,
  file_id VARCHAR2(32) NOT NULL,
  org_name VARCHAR2(255) NOT NULL,
  user_id VARCHAR2(32) NOT NULL,
  status numeric(2),
  type numeric(2),
  remarks VARCHAR2(200),
  create_time VARCHAR2(20) not null,
  create_user_id VARCHAR2(32),
  update_time VARCHAR2(20),
  update_user_id VARCHAR2(32),
  is_deleted VARCHAR2(2)
);

create table f_chat_file(
    id VARCHAR2(32) primary key ,
    file_id VARCHAR2(32) NOT NULL,
    org_name VARCHAR2(255) NOT NULL,
    user_id VARCHAR2(32),
    target_user_id VARCHAR2(32) NOT NULL,
    is_read numeric(2) NOT NULL,
    status numeric(2),
    remarks VARCHAR2(200),
    create_time VARCHAR2(20) not null,
    create_user_id VARCHAR2(32),
    update_time VARCHAR2(20),
    update_user_id VARCHAR2(32),
    is_deleted VARCHAR2(2)
);

create table d_areacode(
  id VARCHAR2(32) primary key ,
  code VARCHAR2(20),
  name VARCHAR2(50),
  parent_code VARCHAR2(20),
  remarks VARCHAR2(200),
  create_time VARCHAR2(20) not null,
  create_user_id VARCHAR2(32),
  update_time VARCHAR2(20),
  update_user_id VARCHAR2(32),
  is_deleted VARCHAR2(2)
);

create table d_dictionary(
  id VARCHAR2(32) primary key ,
  name VARCHAR2(20),
  dict_key VARCHAR2(20),
  dict_value VARCHAR2(500),
  type numeric(2),
  status numeric(2),
  remarks VARCHAR2(200),
  create_time VARCHAR2(20) not null,
  create_user_id VARCHAR2(32),
  update_time VARCHAR2(20),
  update_user_id VARCHAR2(32),
  is_deleted VARCHAR2(2)
);



