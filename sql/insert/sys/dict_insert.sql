insert into s_dict (name, dict_key, dict_value, create_user_id, create_time)
 values ('短信发送间隔(秒)', 'msg_interval', '60', 0, sysdate());
insert into s_dict (name, dict_key, dict_value, create_user_id, create_time)
values ('验证码超时时间(秒)', 'msg_expire', '300', 0, sysdate());