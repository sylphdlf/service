package com.dlf.business.manager.message.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.message.MessagePushService;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.common.utils.message.SmsSendUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.enums.comm.MsgResultEnums;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/29.
 */
@Service
public class MessagePushServiceImpl implements MessagePushService {

    @Autowired
    private RedisService redisService;

    private static final String MAIL_FROM = "dailinfeng@ailchain.io";

    private static final String MAIL_SUBJECT = "注册验证码";

    private static final Long SEND_LIMIT_RESET_TIME = 86400L;

    private static final int SEND_LIMIT_MAX_TIME = 5;

    @ValidateAnno(names = {"username"})
    public GlobalResultDTO messagePush(MsgReqDTO reqDTO, String redisPrefix) throws MyException{
        //重复发送校验
        sendVerify(reqDTO);
        //判断手机
        if(SmsSendUtils.isMobile(reqDTO.getUsername())){
            SmsSendUtils.send(reqDTO.getUsername(), reqDTO.getVerifyCode());
        }else{
            throw new MyException(MsgResultEnums.MSG_OO1.getCode(), MsgResultEnums.MSG_OO1.getMsg());
        }
        //把验证码放到缓存中,设置超时时间
        redisService.put(redisPrefix + reqDTO.getUsername(), reqDTO.getVerifyCode() + "", reqDTO.getExpireTime());
        return GlobalResultDTO.SUCCESS(new MsgDTO(reqDTO.getExpireTime()));
    }

    public GlobalResultDTO messagePushToMQ(MsgReqDTO msgReqDTO) {
        return null;
    }

    public GlobalResultDTO messagePullFromMQ(MsgReqDTO msgReqDTO) {
        return null;
    }

    /**
     * 重复发送校验
     * @param reqDTO
     */
    private void sendVerify(MsgReqDTO reqDTO) throws MyException{
        String checkCodeSendLimit = redisService.get(RedisPrefixEnums.SEND_LIMIT_PER_TIME_PRE.getCode() + reqDTO.getUsername());
//        String checkCodeMaxLimit = redisService.get(RedisPrefixEnums.SEND_LIMIT_MAX_TIME.getCode() + target);
        if(StringUtils.isNotBlank(checkCodeSendLimit)){
            throw new MyException("验证码发送间隔为" + reqDTO.getSendInterval() + "秒");
        }else{
            //发送做1分钟的限制
            redisService.put(RedisPrefixEnums.SEND_LIMIT_PER_TIME_PRE.getCode() + reqDTO.getUsername(), "true", reqDTO.getSendInterval());

        }
//        if(StringUtils.isNotBlank(checkCodeMaxLimit) && Integer.parseInt(checkCodeMaxLimit) >5 ) {
//            throw new MyException("每天最大发送次数为" + SEND_LIMIT_MAX_TIME + "次");
//        }else{
//            //同一个号码或邮箱一天只能发送5次
//            redisService.put(RedisPrefixEnums.SEND_LIMIT_MAX_TIME.getCode() + target, "true", SEND_LIMIT_RESET_TIME);
//        }
    }
}
