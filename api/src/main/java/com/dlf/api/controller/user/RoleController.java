package com.dlf.api.controller.user;

import com.dlf.business.manager.user.RoleService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.RoleSearchDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService roleService;

    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public GlobalResultDTO queryPage(@RequestBody RoleSearchDTO searchDTO) {
        return roleService.queryPage(searchDTO);
    }

}
