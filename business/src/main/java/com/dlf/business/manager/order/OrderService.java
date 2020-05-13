package com.dlf.business.manager.order;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.order.OrderReqDTO;

public interface OrderService {
    GlobalResultDTO createOuter(OrderReqDTO reqDTO);
}
