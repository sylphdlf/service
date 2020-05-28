package com.dlf.api.controller.user;

import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.RoleSearchDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.dto.user.WxUserReqDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/wxspRegister", method = RequestMethod.POST)
    public GlobalResultDTO wxspRegister(@RequestBody WxUserReqDTO reqDTO) {
        return userService.wxRegister(reqDTO);
    }

    @RequestMapping(value = "/getByUsername", method = RequestMethod.POST)
    public GlobalResultDTO getByUsername(@RequestBody UserReqDTO reqDTO){
        return userService.getByUsername(reqDTO);
    }

    @RequestMapping(value = "/checkWxspUser", method = RequestMethod.POST)
    public GlobalResultDTO checkWxspUser(@RequestBody UserReqDTO reqDTO){
        return userService.checkWxspUser(reqDTO);
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public GlobalResultDTO queryPage(@RequestBody UserSearchDTO searchDTO) {
        return userService.queryPage(searchDTO);
    }

    @RequestMapping(value = "/getTypeMap", method = RequestMethod.POST)
    public GlobalResultDTO getTypeMap(){
        return userService.getTypeMap();
    }
}
