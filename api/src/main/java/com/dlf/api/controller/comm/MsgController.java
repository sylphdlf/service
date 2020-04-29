package com.dlf.api.controller.comm;

import com.dlf.business.manager.message.MessagePushService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Resource
    MessagePushService messagePushService;

    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    public GlobalResultDTO wxRegister(@RequestBody MsgReqDTO reqDTO) {
        return messagePushService.sendVerifyCode(reqDTO);
    }

}
