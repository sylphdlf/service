package com.dlf.model.dao.user;

import com.dlf.model.po.user.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxUserMapper extends JpaRepository<WxUser, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);
}