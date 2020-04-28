package com.dlf.business.manager.comm;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.comm.DictReqDTO;

public interface DictService {

    GlobalResultDTO getMap(DictReqDTO reqDTO);
}
