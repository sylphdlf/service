package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.RoleSearchDTO;

public interface RoleService {

    GlobalResultDTO queryPage(RoleSearchDTO searchDTO);
}
