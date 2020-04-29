package com.dlf.business.manager.message.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.message.MessagePushService;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.common.utils.message.SmsSendUtils;
import com.dlf.model.dao.comm.SysDictDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgResDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.enums.comm.MsgResultEnums;
import com.dlf.model.enums.comm.SysDictEnums;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import com.dlf.model.po.comm.SysDict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Created by Administrator on 2017/5/29.
 */
@Service
public class MessagePushServiceImpl implements MessagePushService {

    @Resource
    private RedisService redisService;
    @Resource
    private SysDictDao sysDictDao;

    @Override
    @ValidateAnno(names = {"mobile"})
    public GlobalResultDTO sendVerifyCode(MsgReqDTO reqDTO) {
        //重复发送校验
        sendVerify(reqDTO);
        //判断手机
        if(SmsSendUtils.isMobile(reqDTO.getMobile())){
            SmsSendUtils.send(reqDTO.getMobile(), reqDTO.getVerifyCode());
        }else{
            throw new MyException(MsgResultEnums.MSG_OO1.getCode(), MsgResultEnums.MSG_OO1.getMsg());
        }
        //把验证码放到缓存中,设置超时时间
        redisService.put(RedisPrefixEnums.VERIFY_CODE.getCode() + reqDTO.getMobile(), reqDTO.getVerifyCode() + "", getExpireTime());
        return Optional.of(new MsgResDTO()).map(t -> {
            t.setExpire(getExpireTime());
            t.setInterval(getSendInterval());
            return GlobalResultDTO.SUCCESS(t);
        }).get();
    }

    @ValidateAnno(names = {"mobile"})
    public GlobalResultDTO messagePush(MsgReqDTO reqDTO) throws MyException{
//        //重复发送校验
//        sendVerify(reqDTO);
//        //判断手机
//        if(SmsSendUtils.isMobile(reqDTO.getMobile())){
//            SmsSendUtils.send(reqDTO.getMobile(), reqDTO.getVerifyCode());
//        }else{
//            throw new MyException(MsgResultEnums.MSG_OO1.getCode(), MsgResultEnums.MSG_OO1.getMsg());
//        }
//        //把验证码放到缓存中,设置超时时间
//        redisService.put(redisPrefix + reqDTO.getMobile(), reqDTO.getVerifyCode() + "", reqDTO.getExpireTime());
//        return Optional.ofNullable(new MsgResDTO());
        return null;
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
        String checkSendInterval = redisService.get(RedisPrefixEnums.SEND_INTERVAL.getCode() + reqDTO.getMobile());
        if(StringUtils.isNotBlank(checkSendInterval)){
            throw new MyException("验证码发送间隔为" + getSendInterval() + "秒");
        }else{
            //发送做1分钟的限制
            redisService.put(RedisPrefixEnums.SEND_INTERVAL.getCode() + reqDTO.getMobile(), "true", getSendInterval());
        }
    }

    @Override
    @RedisCacheAnno(timeout = 1800)
    public Long getExpireTime(){
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(SysDictEnums.MSG_EXPIRE.getCode());
        return sysDictDao.findOne(Example.of(sysDict))
                .map(t -> Long.valueOf(t.getDictValue()))
                .orElse(0L);
    }

    @Override
    @RedisCacheAnno(timeout = 1800)
    public Long getSendInterval(){
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(SysDictEnums.MSG_INTERVAL.getCode());
        return sysDictDao.findOne(Example.of(sysDict))
                .map(t -> Long.valueOf(t.getDictValue()))
                .orElse(0L);
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(LocalDateTime.now());
    }
}
