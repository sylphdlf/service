package com.dlf.model.dao.user;

import com.dlf.model.po.user.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogDao extends JpaRepository<AccessLog, Long> {

}