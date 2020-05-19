package com.dlf.api.controller.file;

import com.dlf.business.manager.order.OrderService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.order.OrderReqDTO;
import com.dlf.model.dto.order.OrderSearchDTO;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    OrderService orderService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public GlobalResultDTO upload(MultiValueMap multiValueMap, @RequestParam("orderId") String orderId) {

        return GlobalResultDTO.SUCCESS();
    }

}
