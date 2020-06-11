package com.dlf.business.manager.sys;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.AccessSearchDTO;
import com.dlf.model.dto.sys.SysLogReqDTO;

public interface ServerService {

    GlobalResultDTO queryAccessPage(AccessSearchDTO searchDTO);

}
