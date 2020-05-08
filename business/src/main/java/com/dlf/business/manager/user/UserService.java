package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.dto.user.WxUserReqDTO;

public interface UserService {

    GlobalResultDTO register();

    GlobalResultDTO wxRegister(WxUserReqDTO reqDTO);

    GlobalResultDTO getUserByUsername(UserReqDTO reqDTO);

    GlobalResultDTO checkWxspUser(UserReqDTO reqDTO);

    GlobalResultDTO queryPage(UserSearchDTO searchDTO);

    GlobalResultDTO getTypeMap();
}
