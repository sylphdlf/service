//package com.dlf.business.manager.comm.impl;
//
//import com.dlf.business.anno.CheckImgCodeAnno;
//import com.dlf.business.anno.RedisCacheAnno;
//import com.dlf.business.anno.ValidateAnno;
//import com.dlf.business.exception.MyException;
//import com.dlf.business.manager.comm.CommService;
//import com.dlf.business.manager.redis.RedisService;
//import com.dlf.common.utils.ImgCodeUtils;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.comm.AreaCodeReqDTO;
//import com.dlf.model.dto.message.MsgDTO;
//import com.dlf.model.dto.message.MsgReqDTO;
//import com.dlf.model.dto.user.UserReqDTO;
//import com.dlf.model.enums.redis.RedisPrefixEnums;
//import com.dlf.model.mapper.comm.AreaCodeMapper2;
//import com.dlf.model.po.mat.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class CommServiceImpl implements CommService {
//    @Resource
//    AreaCodeMapper2 areaCodeMapper;
//    @Autowired
//    RedisService redisService;
//
//    //验证码超时时间
//    @Value("${customized.msg.expire-time}")
//    private Long msgExpireTime;
//    //发送间隔限制
//    @Value("${customized.msg.send-interval}")
//    private Long msgSendInterval;
//
//
//    @Override
//    @SuppressWarnings("unchecked")
//    @ValidateAnno(names = {"parentCode"})
//    public GlobalResultDTO queryAreaCode(AreaCodeReqDTO reqDTO) {
//        //获取parentCode，如果没有则获取第一级列表
//        return GlobalResultDTO.SUCCESS(areaCodeMapper.queryListByParams(reqDTO));
//    }
//
//    @Override
//    @ValidateAnno(names = {"username"})
//    public GlobalResultDTO<String> getImgCode(UserReqDTO reqDTO) throws MyException{
//        ImgCodeUtils imgCodeUtils = new ImgCodeUtils();
//        String randomCode = imgCodeUtils.getRandomCode();
//        redisService.put(RedisPrefixEnums.IMG_CODE.getCode() + reqDTO.getUsername(), randomCode, 60L);
//        return new GlobalResultDTO<String>(randomCode);
//    }
//}
