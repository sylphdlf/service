package com.dlf.model.dao.user;

import com.dlf.model.po.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleMapper extends JpaRepository<Role, Long> {

}