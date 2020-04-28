package com.dlf.business.manager.comm.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.comm.DictService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.comm.DictReqDTO;
import com.dlf.model.enums.ICommEnums;
import com.dlf.model.enums.user.RoleEnums;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DictServiceImpl implements DictService {

    private static String aaa;

    @Override
    @RedisCacheAnno(timeout = 1800)
    public GlobalResultDTO getMap(DictReqDTO reqDTO) {
        String key = reqDTO.getDictKey();
        String[] keys = key.split("-");
        Map<Integer, String> keyValueMap = ICommEnums.getKeyValueMap(keys[1], RoleEnums.class);
        return GlobalResultDTO.SUCCESS(keyValueMap);
    }
}
