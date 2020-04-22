package com.dlf.business.manager.user.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dao.user.UserMapper;
import com.dlf.model.dao.user.WxUserMapper;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.WxUserReqDTO;
import com.dlf.model.enums.user.UserEnums;
import com.dlf.model.po.user.User;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    WxUserMapper wxUserMapper;

    @Override
    public GlobalResultDTO register() {
        return null;
    }

    @Override
    @Transactional
    public GlobalResultDTO wxRegister(WxUserReqDTO reqDTO) {
        User user = new User();
        user.setUsername(reqDTO.getMobile());
        user.setMobile(reqDTO.getMobile());
        user.setType(UserEnums.TYPE_1.getCode());
        userMapper.save(user);
        return GlobalResultDTO.FAIL();
    }

    @Override
    public GlobalResultDTO getUserByUsername(UserReqDTO reqDTO) {
        User user = new User();
        user.setUsername(reqDTO.getUsername());
        return userMapper.findOne(Example.of(user))
                .map(u -> GlobalResultDTO.SUCCESS(u.getUsername()))
                .orElse(GlobalResultDTO.FAIL());
    }
}
