package com.dlf.api.controller.order;

import com.dlf.business.manager.order.OrderService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.order.OrderReqDTO;
import com.dlf.model.dto.order.OrderSearchDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping(value = "/createOuter", method = RequestMethod.POST)
    public GlobalResultDTO createOuter(@RequestBody OrderReqDTO reqDTO) {
        return orderService.createOuter(reqDTO);
    }

    @RequestMapping(value = "/queryOutPage", method = RequestMethod.POST)
    public GlobalResultDTO queryOutPage(@RequestBody OrderSearchDTO searchDTO) {
        return orderService.queryOutPage(searchDTO);
    }

}
