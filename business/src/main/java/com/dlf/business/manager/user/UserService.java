package com.dlf.business.manager.user;

import com.dlf.business.exception.MyException;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserReqDTO;

public interface UserService {

    GlobalResultDTO login(UserReqDTO reqDTO);

    GlobalResultDTO register(UserReqDTO reqDTO) throws MyException;

    GlobalResultDTO queryUserByUsername(UserReqDTO reqDTO);

}
