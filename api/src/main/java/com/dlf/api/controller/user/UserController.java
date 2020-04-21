package com.dlf.api.controller.user;

import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.WxUserReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/wxsp/register", method = RequestMethod.POST)
    public GlobalResultDTO wxRegister(@RequestBody WxUserReqDTO reqDTO) {
        return userService.wxRegister(reqDTO);
    }
}
