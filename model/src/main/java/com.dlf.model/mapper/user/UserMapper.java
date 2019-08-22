package com.dlf.model.mapper.user;

import com.dlf.model.mapper.BaseMapper;
import com.dlf.model.po.user.User;

public interface UserMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}