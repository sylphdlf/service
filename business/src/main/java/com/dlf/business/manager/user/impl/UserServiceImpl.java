package com.dlf.business.manager.user.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.enums.user.UserEnums;
import com.dlf.model.enums.user.UserResultEnums;
import com.dlf.model.mapper.user.UserMapper2;
import com.dlf.model.po.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper2 userMapper;
    @Autowired
    RedisService redisService;

    @Override
    @ValidateAnno(names = {"username", "password"})
    public GlobalResultDTO login(UserReqDTO reqDTO) {
        User user = new User();
        user.setUsername(reqDTO.getUsername());
        List<User> returnList = userMapper.queryListByParams(user);
        if (!CollectionUtils.isEmpty(returnList)) {
            if (returnList.get(0).getStatus().intValue() == UserEnums.STATUS_4.getCode().intValue()) {
                throw new MyException(UserResultEnums.USER_BLACKLIST.getCode(), UserResultEnums.USER_BLACKLIST.getMsg());
            }
            UserResDTO userResDTO = new UserResDTO();
            return new GlobalResultDTO<>(userResDTO);
        }
        throw new MyException(UserResultEnums.USER_NOT_EXISTED.getCode(), UserResultEnums.USER_NOT_EXISTED.getMsg());
    }


    @Override
    @Transactional
    @ValidateAnno(names = {"step"})
    public GlobalResultDTO register(UserReqDTO reqDTO) throws MyException{
//        User localUser = null;
//        if (reqDTO.getStep() == 3 || reqDTO.getStep() == 4) {
//            GlobalResultDTO<User> globalResultDTO = this.checkAndGetLocalUser(reqDTO);
//            localUser = globalResultDTO.getData();
//        }
//        switch (reqDTO.getStep()){
//            case 1://验证码注册
//                return this.registerWithCode(reqDTO);
//            case 2://密码注册
//                return userService2.registerWithPassword(reqDTO);
//            case 3://设置身份证
//                reqDTO.setDriverMobile(localUser.getUsername());
//                return this.insertOrUpdateUserInfo(reqDTO);
//            case 4://设置车辆信息
//                //如果用户重新提交资料，则需要重新审核
//                if(null != localUser && localUser.getStatus().intValue() != UserEnums.STATUS_1.getCode().intValue()){
//                    localUser.setStatus(UserEnums.STATUS_1.getCode());
//                    userMapper.updateByPrimaryKey(localUser);
//                }
//                return this.insertOrUpdateVehicle(reqDTO);
//        }
        return GlobalResultDTO.FAIL();
    }

    @Override
    @ValidateAnno(names = {"username"})
    public GlobalResultDTO queryUserByUsername(UserReqDTO reqDTO){
        User user = new User();
        user.setUsername(reqDTO.getUsername());
        List<User> returnList = userMapper.queryListByParams(user);
        Map<String, Integer> statusMap = new HashMap<>();
        if(CollectionUtils.isEmpty(returnList)){
            statusMap.put("status", 0);
            return GlobalResultDTO.SUCCESS(statusMap);
        }
        statusMap.put("status", 1);
        return GlobalResultDTO.SUCCESS(statusMap);
    }
}
