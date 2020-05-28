package com.dlf.business.manager.sys;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.AccessSearchDTO;

public interface ServerService {

    GlobalResultDTO queryAccessPage(AccessSearchDTO searchDTO);
}
