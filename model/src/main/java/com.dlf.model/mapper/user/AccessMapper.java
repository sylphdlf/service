package com.dlf.model.mapper.user;

import com.dlf.model.mapper.BaseMapper;
import com.dlf.model.po.user.Access;

public interface AccessMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Access record);

    int insertSelective(Access record);

    Access selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Access record);

    int updateByPrimaryKey(Access record);
}