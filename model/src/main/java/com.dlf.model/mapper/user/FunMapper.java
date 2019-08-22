package com.dlf.model.mapper.user;

import com.dlf.model.mapper.BaseMapper;
import com.dlf.model.po.user.Fun;

public interface FunMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fun record);

    int insertSelective(Fun record);

    Fun selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fun record);

    int updateByPrimaryKey(Fun record);
}