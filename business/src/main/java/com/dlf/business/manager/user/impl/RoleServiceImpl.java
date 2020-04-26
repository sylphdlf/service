package com.dlf.business.manager.user.impl;

import com.dlf.business.manager.user.RoleService;
import com.dlf.model.dao.user.RoleMapper;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.RoleSearchDTO;
import com.dlf.model.po.user.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public GlobalResultDTO queryPage(RoleSearchDTO searchDTO) {
        Role role = new Role();
        if(StringUtils.isNotEmpty(searchDTO.getName())){
            role.setName(searchDTO.getName());
        }
        Pageable pageable = PageRequest.of(searchDTO.getPageIndex()-1, searchDTO.getPageSize());
        Example<Role> example = Example.of(role);
        Page<Role> all = roleMapper.findAll(example, pageable);
        return GlobalResultDTO.SUCCESS(all);
    }
}
