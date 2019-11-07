package com.dlf.api.user;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.BaseReqDTO;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public GlobalResultDTO register(@RequestBody UserReqDTO reqDTO) throws MyException{
        return userService.register(reqDTO);
    }

}
