package com.dlf.api.controller.comm;

import com.dlf.business.manager.msg.MsgService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Resource
    MsgService msgService;

    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.POST)
    public GlobalResultDTO getVerifyCode(@RequestBody MsgReqDTO reqDTO) {
        return msgService.sendVerifyCode(reqDTO);
    }

}
