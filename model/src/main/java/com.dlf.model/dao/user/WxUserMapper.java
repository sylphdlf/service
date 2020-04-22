package com.dlf.model.dao.user;

import com.dlf.model.po.user.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WxUserMapper extends JpaRepository<WxUser, Long> {

}