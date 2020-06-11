package com.dlf.business.manager.sys.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.manager.sys.SysLogService;
import com.dlf.model.dao.sys.SysLogDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.SysLogReqDTO;
import com.dlf.model.dto.sys.SysLogSearchDTO;
import com.dlf.model.po.sys.SysLog;
import com.dlf.model.po.user.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {

    @Resource
    SysLogDao sysLogDao;

    @Override
    public GlobalResultDTO save(SysLogReqDTO reqDTO) {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(reqDTO, sysLog);
        sysLogDao.save(sysLog);
        return GlobalResultDTO.SUCCESS();
    }

    @Override
    public GlobalResultDTO queryPage(SysLogSearchDTO searchDTO) {
        SysLog sysLog = new SysLog();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(searchDTO.getPageIndex()-1, searchDTO.getPageSize(), sort);
        Example<SysLog> example = Example.of(sysLog);
        Page<SysLog> all = sysLogDao.findAll(example, pageable);
        return GlobalResultDTO.SUCCESS(all);
    }

    @Override
    @ValidateAnno(names = {"id"})
    public GlobalResultDTO del(SysLogReqDTO reqDTO) {
        sysLogDao.deleteById(reqDTO.getId());
        return GlobalResultDTO.SUCCESS();
    }
}
