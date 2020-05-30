package com.dlf.business.manager.comm;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.comm.DictReqDTO;
import com.dlf.model.dto.comm.DictSearchDTO;

public interface DictService {

    GlobalResultDTO getMap(DictReqDTO reqDTO);

    GlobalResultDTO queryPage(DictSearchDTO searchDTO);

    GlobalResultDTO addOrEdit(DictReqDTO reqDTO);
}
