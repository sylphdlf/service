package com.dlf.business.manager.comm.impl;

import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.comm.DictService;
import com.dlf.model.dao.comm.SysDictDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.comm.DictReqDTO;
import com.dlf.model.dto.comm.DictSearchDTO;
import com.dlf.model.enums.ICommEnums;
import com.dlf.model.enums.user.RoleEnums;
import com.dlf.model.po.comm.SysDict;
import com.dlf.model.po.user.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class DictServiceImpl implements DictService {

    @Resource
    SysDictDao sysDictDao;

    @Override
    @RedisCacheAnno(timeout = 1800)
    public GlobalResultDTO getMap(DictReqDTO reqDTO) {
        String key = reqDTO.getDictKey();
        String[] keys = key.split("-");
        Map<Integer, String> keyValueMap = ICommEnums.getKeyValueMap(keys[1], RoleEnums.class);
        return GlobalResultDTO.SUCCESS(keyValueMap);
    }

    @Override
    public GlobalResultDTO queryPage(DictSearchDTO searchDTO) {
        SysDict sysDict = new SysDict();
        if(null == searchDTO.getParentId()){
            searchDTO.setParentId(0L);
        }
        BeanUtils.copyProperties(searchDTO, sysDict);
        Pageable pageable = PageRequest.of(searchDTO.getPageIndex()-1, searchDTO.getPageSize());
        Example<SysDict> example = Example.of(sysDict);
        Page<SysDict> all = sysDictDao.findAll(example, pageable);
        return GlobalResultDTO.SUCCESS(all);
    }

    @Override
    public GlobalResultDTO addOrEdit(DictReqDTO reqDTO) {
        if(null != reqDTO.getId()){
            SysDict sysDict = sysDictDao.getOne(reqDTO.getId());
            BeanUtils.copyProperties(reqDTO, sysDict);
            sysDictDao.save(sysDict);
        }else{
            SysDict sysDict = new SysDict();
            BeanUtils.copyProperties(reqDTO, sysDict);
            if(null == reqDTO.getParentId()){
                sysDict.setParentId(0L);
            }
            sysDictDao.save(sysDict);
        }
        return GlobalResultDTO.SUCCESS();
    }
}
