package com.dlf.business.manager.sys;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.ServerReqDTO;

public interface ServerService {

    GlobalResultDTO addOrEdit(ServerReqDTO reqDTO);

}
