package com.dlf.api.controller.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.WxUserReqDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/wxsp/register", method = RequestMethod.POST)
    public GlobalResultDTO wxRegister(@RequestBody WxUserReqDTO reqDTO) {

        return GlobalResultDTO.SUCCESS();
    }
}
