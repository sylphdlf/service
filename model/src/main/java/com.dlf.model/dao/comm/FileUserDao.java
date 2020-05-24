package com.dlf.model.dao.comm;

import com.dlf.model.po.comm.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUserDao extends JpaRepository<FileUser, Long> {
}
