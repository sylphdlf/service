package com.dlf.business.manager.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.manager.msg.MsgService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dao.user.AccessLogDao;
import com.dlf.model.dao.user.UserDao;
import com.dlf.model.dao.user.WxUserMapper;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.dto.user.WxUserReqDTO;
import com.dlf.model.enums.ICommEnums;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import com.dlf.model.enums.user.UserEnums;
import com.dlf.model.enums.user.UserResultEnums;
import com.dlf.model.po.user.AccessLog;
import com.dlf.model.po.user.User;
import com.dlf.model.po.user.WxUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    WxUserMapper wxUserMapper;
    @Resource
    MsgService msgService;
    @Resource
    AccessLogDao accessLogDao;

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
        Long userId = Optional.of(userDao.findOne(Example.of(user))).get()//是否注册过
                .orElseGet(() -> {//没有则新增
                    user.setMobile(reqDTO.getMobile());
                    user.setType(UserEnums.TYPE_1.getCode());
                    return userDao.saveAndFlush(user);
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
    public GlobalResultDTO getByUsername(UserReqDTO reqDTO) {
        User user = new User();
        user.setUsername(reqDTO.getUsername());
        return userDao.findOne(Example.of(user))
                .map(u -> {
                    UserResDTO resDTO = new UserResDTO();
                    BeanUtils.copyProperties(u, resDTO);
                    //获取上一次访问记录
                    AccessLog accessLog = accessLogDao.findTopByUserIdOrderByUpdateTimeDesc(u.getId());
                    resDTO.setLastIp(accessLog.getIpAddr());
                    resDTO.setUpdateTime(accessLog.getUpdateTime());
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
            User user = userDao.getOne(result.get().getUserId());
            //查询是否为管理员权限
            int count = userDao.checkAdmin(result.get().getUserId());
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
        Page<User> all = userDao.findAll(example, pageable);
        return GlobalResultDTO.SUCCESS(all);
    }

    @Override
    public GlobalResultDTO getTypeMap() {
        return GlobalResultDTO.SUCCESS(ICommEnums.getKeyValueMap("type", UserEnums.class));
    }
}
