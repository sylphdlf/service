package com.dlf.business.manager.sys.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.manager.sys.ServerService;
import com.dlf.model.dao.sys.ServerDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.ServerReqDTO;
import com.dlf.model.po.sys.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServerServiceImpl implements ServerService {

    @Resource
    ServerDao serverDao;

    @Override
    @ValidateAnno(names = {"name", "ip", "port", "serverPath", "configPath"})
    public GlobalResultDTO addOrEdit(ServerReqDTO reqDTO) {
        Server server = new Server();
        BeanUtils.copyProperties(reqDTO, server);
        serverDao.save(server);
        return GlobalResultDTO.SUCCESS();
    }
}
