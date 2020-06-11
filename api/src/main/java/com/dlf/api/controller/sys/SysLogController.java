package com.dlf.api.controller.sys;

import com.dlf.business.manager.sys.ServerService;
import com.dlf.business.manager.sys.SysLogService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.AccessSearchDTO;
import com.dlf.model.dto.sys.SysLogReqDTO;
import com.dlf.model.dto.sys.SysLogSearchDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/sysLog")
public class SysLogController {

    @Resource
    SysLogService sysLogService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public GlobalResultDTO save(@RequestBody SysLogReqDTO reqDTO){
        return sysLogService.save(reqDTO);
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public GlobalResultDTO queryPage(@RequestBody SysLogSearchDTO searchDTO){
        return sysLogService.queryPage(searchDTO);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public GlobalResultDTO del(@RequestBody SysLogReqDTO reqDTO){
        return sysLogService.del(reqDTO);
    }

}
