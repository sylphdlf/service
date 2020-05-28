package com.dlf.model.dao.user;

import com.alibaba.fastjson.JSONObject;
import com.dlf.model.po.user.AccessLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccessLogDao extends JpaRepository<AccessLog, Long> {

    @Query(value = "select " +
            "b.username, a.ip_addr as ipAddr, a.user_id as userId, a.access_count as accessCount, " +
            "a.create_time as createTime, a.update_time as updateTime " +
            "from u_access_log a left join u_user b on a.user_id = b.id",
            countQuery = "select count(1) from u_access_log a left join u_user b on a.user_id = b.id",
            nativeQuery = true)
    Page<JSONObject[]> queryPageLeftUser(Pageable pageable);
}