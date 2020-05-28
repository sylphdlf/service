package com.dlf.model.dao.comm;

import com.alibaba.fastjson.JSONObject;
import com.dlf.model.po.comm.FileUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileUserDao extends JpaRepository<FileUser, Long> {

    @Query(value = "select b.* from f_file_user a " +
            "left join f_file b on a.file_id = b.id where a.user_id =:userId",
            countQuery = "select count(1) from f_file_user a " +
                    "left join f_file  b on a.file_id = b.id where a.user_id =:userId",
            nativeQuery = true)
    Page<JSONObject[]> findUserFilePage(@Param("userId") Long userId, Pageable pageable);
}
