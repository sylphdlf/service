package com.dlf.business.manager.sys;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.SysLogReqDTO;
import com.dlf.model.dto.sys.SysLogSearchDTO;

public interface SysLogService {

    GlobalResultDTO save(SysLogReqDTO reqDTO);

    GlobalResultDTO queryPage(SysLogSearchDTO searchDTO);

    GlobalResultDTO del(SysLogReqDTO reqDTO);
}
