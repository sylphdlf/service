package com.dlf.model.dao.user;

import com.dlf.model.po.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMapper extends JpaRepository<User, Long> {

    @Query(value="select count(1) from " +
            "u_user_role a left join u_role b on a.role_id = b.id " +
            "where b.type = 1 and a.user_id=:userId", nativeQuery=true)
    int checkAdmin(@Param("userId") Long userId);
}