package com.dlf.business.aspect;

import com.dlf.business.anno.CheckImgCodeAnno;
import com.dlf.business.comm.ValidateUtils;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.common.utils.user.UserUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.comm.ImgCodeResultEnums;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ImgCodeAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisService redisService;

    /**
     * 图片验证码验证
     * @param jp
     * @param anno
     * @return Object
     * @throws MyException
     */
    @Around(value = "@annotation(anno))")
    public Object validate(ProceedingJoinPoint jp, CheckImgCodeAnno anno) throws MyException{
        ValidateUtils.validate(jp.getArgs()[0], new String[]{"username"});
        Integer isCheckImgCode = UserUtils.getUserLocal().getIsCheckImgCode();
        logger.info("userlocal.isCheckImgCode:" + isCheckImgCode);
        String imgCode = "";
        String username = "";
        if (isCheckImgCode != null && isCheckImgCode == 1) {
            try {
                Object bean = jp.getArgs()[0];
                username = (String) PropertyUtils.getProperty(bean, "username");
                imgCode = (String) PropertyUtils.getProperty(bean, "imgCode");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            String rightImgCode = redisService.get(RedisPrefixEnums.IMG_CODE.getCode() + username);
            if (StringUtils.isBlank(rightImgCode)) {
                throw new MyException(ImgCodeResultEnums.IMG_CODE_EXPIRED.getCode(), ImgCodeResultEnums.IMG_CODE_EXPIRED.getMsg());
            }
            if (!rightImgCode.equalsIgnoreCase(imgCode)) {
                throw new MyException(ImgCodeResultEnums.IMG_CODE_ERROR.getCode(), ImgCodeResultEnums.IMG_CODE_ERROR.getMsg());
            }
        }
        try {
            GlobalResultDTO proceed = (GlobalResultDTO)jp.proceed();
            if (isCheckImgCode != null && isCheckImgCode == 1) {
                redisService.removeKey(RedisPrefixEnums.IMG_CODE.getCode() + username);
            }
            return proceed;
        } catch (MyException e) {
            throw new MyException(e.getErrorCode(), e.getMessage());
        } catch (Throwable t){
            logger.error(t.getMessage());
        }
        return GlobalResultDTO.FAIL();
    }
}