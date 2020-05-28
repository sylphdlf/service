package com.dlf.business.manager.sys.impl;

import com.alibaba.fastjson.JSONObject;
import com.dlf.business.manager.sys.ServerService;
import com.dlf.model.dao.user.AccessLogDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.AccessSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ServerServiceImpl implements ServerService {

    @Resource
    AccessLogDao accessLogDao;

    @Override
    public GlobalResultDTO queryAccessPage(AccessSearchDTO searchDTO) {
        Pageable pageable = PageRequest.of(searchDTO.getPageIndex()-1, searchDTO.getPageSize());
        Page<JSONObject[]> all = accessLogDao.queryPageLeftUser(pageable);
        return GlobalResultDTO.SUCCESS(all);
    }
}
