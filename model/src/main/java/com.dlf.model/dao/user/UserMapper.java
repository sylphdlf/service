package com.dlf.model.dao.user;

import com.dlf.model.po.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User, Long> {

}