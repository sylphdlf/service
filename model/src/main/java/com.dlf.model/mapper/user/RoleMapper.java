package com.dlf.model.mapper.user;

import com.dlf.model.mapper.BaseMapper;
import com.dlf.model.po.user.Role;

public interface RoleMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}