package com.dlf.api.controller.sys;

import com.dlf.business.manager.sys.ServerService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.sys.AccessSearchDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/server")
public class ServerController {

    @Resource
    ServerService serverService;

    @RequestMapping(value = "/queryAccessPage", method = RequestMethod.POST)
    public GlobalResultDTO queryAccessPage(@RequestBody AccessSearchDTO searchDTO){
        return serverService.queryAccessPage(searchDTO);
    }

}
