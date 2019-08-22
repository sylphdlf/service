package com.dlf.api.user;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.BaseReqDTO;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public GlobalResultDTO login(@RequestBody BaseReqDTO<UserReqDTO> reqDTO) {
        return userService.login(reqDTO.getData());
    }
}
