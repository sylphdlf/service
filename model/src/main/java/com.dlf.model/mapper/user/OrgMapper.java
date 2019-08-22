package com.dlf.model.mapper.user;

import com.dlf.model.mapper.BaseMapper;
import com.dlf.model.po.user.Org;

public interface OrgMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
}