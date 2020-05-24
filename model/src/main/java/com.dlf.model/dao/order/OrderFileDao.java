package com.dlf.model.dao.order;

import com.dlf.model.po.order.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFileDao extends JpaRepository<OrderFile, Long> {

}
