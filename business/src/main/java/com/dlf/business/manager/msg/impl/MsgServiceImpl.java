package com.dlf.business.manager.msg.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.msg.MsgService;
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
import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Administrator on 2017/5/29.
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Resource
    private RedisService redisService;
    @Resource
    private SysDictDao sysDictDao;

    @Override
    @ValidateAnno(names = {"mobile"})
    public GlobalResultDTO sendVerifyCode(MsgReqDTO reqDTO) {
        //重复发送校验
        sendCheck(reqDTO);
        //判断手机
        if(SmsSendUtils.isMobile(reqDTO.getMobile())){
            SmsSendUtils.send(reqDTO.getMobile(), "1234");
        }else{
            throw new MyException(MsgResultEnums.MOBILE_ILLEGAL);
        }
        //把验证码放到缓存中,设置超时时间
        redisService.put(RedisPrefixEnums.VERIFY_CODE.getCode() + reqDTO.getMobile(), "1234" + "", getExpireTime());
        return Optional.of(new MsgResDTO()).map(t -> {
            t.setExpire(getExpireTime());
            t.setInterval(getSendInterval());
            return GlobalResultDTO.SUCCESS(t);
        }).get();
    }

    @Override
    public GlobalResultDTO checkVerifyCode(MsgReqDTO reqDTO) throws MyException {
        Optional.ofNullable(redisService.get(reqDTO.getRedisKey()))
                .filter(StringUtils::isNotBlank)
                .map(t -> {
                    if(StringUtils.equalsIgnoreCase(t, reqDTO.getVerifyCode()))
                        return GlobalResultDTO.SUCCESS();
                    return GlobalResultDTO.FAIL(MsgResultEnums.VERIFY_CODE_ERROR);
                });
        return GlobalResultDTO.FAIL(MsgResultEnums.VERIFY_CODE_ERROR);
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
    private void sendCheck(MsgReqDTO reqDTO) throws MyException{
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
