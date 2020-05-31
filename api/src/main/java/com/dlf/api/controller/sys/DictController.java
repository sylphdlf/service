package com.dlf.api.controller.sys;

import com.dlf.business.manager.comm.DictService;
import com.dlf.business.manager.user.RoleService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.comm.DictReqDTO;
import com.dlf.model.dto.comm.DictSearchDTO;
import com.dlf.model.dto.user.RoleSearchDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    DictService dictService;

    @RequestMapping(value = "/getMap", method = RequestMethod.POST)
    public GlobalResultDTO getMap(@RequestBody DictReqDTO reqDTO) {
        return dictService.getMap(reqDTO);
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public GlobalResultDTO queryPage(@RequestBody DictSearchDTO searchDTO){
        return dictService.queryPage(searchDTO);
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public GlobalResultDTO addOrEdit(@RequestBody DictReqDTO reqDTO){
        return dictService.addOrEdit(reqDTO);
    }

    /**
     * 刷新redis缓存
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/refreshCache", method = RequestMethod.POST)
    public GlobalResultDTO refreshCache(@RequestBody DictSearchDTO searchDTO){
        return dictService.refreshCache(searchDTO);
    }
}
