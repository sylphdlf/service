package com.dlf.api.controller.user;

import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public GlobalResultDTO login(@RequestBody UserReqDTO reqDTO) {
        return userService.login(reqDTO);
    }
}