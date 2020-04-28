package com.dlf.business.manager.user.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.RoleService;
import com.dlf.model.dao.user.RoleMapper;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.RoleReqDTO;
import com.dlf.model.dto.user.RoleSearchDTO;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.enums.ICommEnums;
import com.dlf.model.enums.redis.IRedisPrefixEnums;
import com.dlf.model.enums.user.RoleEnums;
import com.dlf.model.po.user.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    @Override
//    @RedisCacheAnno(key = IRedisPrefixEnums.roleType, timeout = 1800)
    public GlobalResultDTO getTypeMap() {
        return GlobalResultDTO.SUCCESS(ICommEnums.getKeyValueMap("type", RoleEnums.class));
    }

    @Override
    public GlobalResultDTO addOrEdit(RoleReqDTO reqDTO) {
        Role role = new Role();
        role.setName(reqDTO.getName());
        long count = roleMapper.count(Example.of(role));
        if(count > 0){
            throw new MyException(GlobalResultEnum.UNIQUE_ERROR);
        }
        roleMapper.saveAndFlush(role);
        return GlobalResultDTO.SUCCESS();
    }
}
