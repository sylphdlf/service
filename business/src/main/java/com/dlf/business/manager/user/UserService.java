package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.WxUserReqDTO;

public interface UserService {

    GlobalResultDTO register();

    GlobalResultDTO wxRegister(WxUserReqDTO reqDTO);

}
