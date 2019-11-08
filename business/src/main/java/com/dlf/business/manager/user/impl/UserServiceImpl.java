package com.dlf.business.manager.user.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.UserService;
import com.dlf.common.utils.BeanUtils;
import com.dlf.common.utils.Md5Utils;
import com.dlf.model.dao.user.UserDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.enums.user.UserResultEnums;
import com.dlf.model.po.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    @ValidateAnno(names = {"username", "password"})
    public GlobalResultDTO login(UserReqDTO reqDTO) {
        try {
            User result = userDao.findByUsernameAndPassword(reqDTO.getUsername(), Md5Utils.md5Encoding32(reqDTO.getPassword().getBytes()));
            if (null != result) {
                UserResDTO userResDTO = new UserResDTO();
                BeanUtils.copyProperties(result, userResDTO);
                return GlobalResultDTO.SUCCESS(userResDTO);
            }
            return GlobalResultDTO.FAIL(UserResultEnums.USER_NOT_EXISTED.getCode(), UserResultEnums.USER_NOT_EXISTED.getMsg());
        }catch (Exception e){
            throw new MyException(e.getMessage());
        }
    }


    @Override
    public GlobalResultDTO register(UserReqDTO reqDTO) throws MyException{
        try {
            User user = userDao.findByUsername(reqDTO.getUsername());
            if(null == user){
                user = new User();
                user.setUsername(reqDTO.getUsername());
                user.setPassword(Md5Utils.md5Encoding32(reqDTO.getPassword().getBytes()));
                userDao.saveAndFlush(user);
                return GlobalResultDTO.SUCCESS();
            }
            return GlobalResultDTO.FAIL(UserResultEnums.USER_EXISTED.getCode(), UserResultEnums.USER_EXISTED.getMsg());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new MyException(e.getMessage());
        }
    }
}
