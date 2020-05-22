package com.dlf.model.dao.comm;

import com.dlf.model.po.comm.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long> {
}
