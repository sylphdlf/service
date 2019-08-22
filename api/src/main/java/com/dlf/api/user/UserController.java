package com.dlf.api.user;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.BaseReqDTO;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    UserService userService;

    /**
     * 通过用户名查询用户
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/queryUserByUsername", method = RequestMethod.POST)
    public GlobalResultDTO queryUserByUsername(@RequestBody BaseReqDTO<UserReqDTO> reqDTO){
        return userService.queryUserByUsername(reqDTO.getData());
    }
}
