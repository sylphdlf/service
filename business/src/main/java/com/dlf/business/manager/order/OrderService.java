package com.dlf.business.manager.order;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.order.OrderReqDTO;
import com.dlf.model.dto.order.OrderSearchDTO;

public interface OrderService {
    GlobalResultDTO createOuter(OrderReqDTO reqDTO);

    GlobalResultDTO queryOutPage(OrderSearchDTO searchDTO);
}
