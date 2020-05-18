package com.dlf.business.manager.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.msg.MsgService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dao.user.UserMapper;
import com.dlf.model.dao.user.WxUserMapper;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.dto.user.WxUserReqDTO;
import com.dlf.model.enums.ICommEnums;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import com.dlf.model.enums.user.RoleEnums;
import com.dlf.model.enums.user.UserEnums;
import com.dlf.model.enums.user.UserResultEnums;
import com.dlf.model.po.user.Role;
import com.dlf.model.po.user.User;
import com.dlf.model.po.user.WxUser;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Resource
    MsgService msgService;

    @Override
    public GlobalResultDTO register() {
        return null;
    }

    @Override
    @ValidateAnno(names = {"verifyCode", "mobile", "openId"})
    @Transactional
    public GlobalResultDTO wxRegister(WxUserReqDTO reqDTO) {
        //验证码验证
        MsgReqDTO msgReqDTO = new MsgReqDTO();
        msgReqDTO.setVerifyCode(reqDTO.getVerifyCode());
        msgReqDTO.setRedisKey(RedisPrefixEnums.VERIFY_CODE.getCode() + reqDTO.getMobile());
        GlobalResultDTO resultDTO = msgService.checkVerifyCode(msgReqDTO);
        if(!resultDTO.isSuccess()){
            return GlobalResultDTO.FAIL(UserResultEnums.VERIFY_CODE_ERROR);
        }
        //新增用户
        User user = new User();
        user.setUsername(reqDTO.getMobile());
        Long userId = Optional.of(userMapper.findOne(Example.of(user))).get()//是否注册过
                .orElseGet(() -> {//没有则新增
                    user.setMobile(reqDTO.getMobile());
                    user.setType(UserEnums.TYPE_1.getCode());
                    return userMapper.saveAndFlush(user);
                }).getId();
        //新增微信用户
        WxUser wxUser = new WxUser();
        JSONObject userInfo = JSONObject.parseObject(reqDTO.getRemarks());
        wxUser.setNickName(userInfo.getString("nickName"));
        wxUser.setUserId(userId);
        wxUser.setOpenId(reqDTO.getOpenId());
        wxUser.setRemarks(reqDTO.getRemarks());
        wxUserMapper.save(wxUser);
        return GlobalResultDTO.SUCCESS();
    }

    @Override
    public GlobalResultDTO getUserByUsername(UserReqDTO reqDTO) {
        User user = new User();
        user.setUsername(reqDTO.getUsername());
        return userMapper.findOne(Example.of(user))
                .map(u -> {
                    UserResDTO resDTO = new UserResDTO();
                    BeanUtils.copyProperties(u, resDTO);
                    return GlobalResultDTO.SUCCESS(resDTO);
                })
                .orElse(GlobalResultDTO.FAIL());
    }

    @Override
    public GlobalResultDTO checkWxspUser(UserReqDTO reqDTO) {
        WxUser wxUser = new WxUser();
        wxUser.setOpenId(reqDTO.getOpenId());
        Optional<WxUser> result = wxUserMapper.findOne(Example.of(wxUser));
        if(result.isPresent()){
            User user = userMapper.getOne(result.get().getUserId());
            //查询是否为管理员权限
            int count = userMapper.checkAdmin(result.get().getUserId());
            UserResDTO resDTO = new UserResDTO();
            resDTO.setId(user.getId());
            resDTO.setUsername(user.getUsername());
            if(count > 0){
                resDTO.setIsAdmin(1);
            }
            return GlobalResultDTO.SUCCESS(resDTO);
        }
        return GlobalResultDTO.FAIL();
    }

    @Override
    public GlobalResultDTO queryPage(UserSearchDTO searchDTO) {
        User user = new User();
        BeanUtils.copyProperties(searchDTO, user);
        Pageable pageable = PageRequest.of(searchDTO.getPageIndex()-1, searchDTO.getPageSize());
        Example<User> example = Example.of(user);
        Page<User> all = userMapper.findAll(example, pageable);
        return GlobalResultDTO.SUCCESS(all);
    }

    @Override
    public GlobalResultDTO getTypeMap() {
        return GlobalResultDTO.SUCCESS(ICommEnums.getKeyValueMap("type", UserEnums.class));
    }
}
