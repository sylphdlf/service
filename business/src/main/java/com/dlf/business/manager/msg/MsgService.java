package com.dlf.business.manager.msg;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgReqDTO;

/**
 * Created by Administrator on 2017/5/29.
 */
public interface MsgService {

    /**
     * 发送验证码
     * @param reqDTO
     * @return
     */
    GlobalResultDTO sendVerifyCode(MsgReqDTO reqDTO);

    GlobalResultDTO checkVerifyCode(MsgReqDTO reqDTO);

    Long getExpireTime();

    Long getSendInterval();
}
