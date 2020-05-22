package com.dlf.business.manager.file.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.file.FileService;
import com.dlf.business.manager.order.OrderService;
import com.dlf.common.utils.CodeGenerateUtils;
import com.dlf.model.dao.comm.FileDao;
import com.dlf.model.dao.order.OrderOuterDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileReqDTO;
import com.dlf.model.enums.order.OrderResultEnums;
import com.dlf.model.po.comm.File;
import com.dlf.model.po.order.OrderOuter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileDao fileDao;
    @Resource
    OrderOuterDao orderOuterDao;

    @Override
    @ValidateAnno(names = {"md5","orderId"})
    public GlobalResultDTO save(FileReqDTO reqDTO) {
        //查询是否有重复文件, 没有则新增
        File file = new File();
        file.setMd5(reqDTO.getMd5());
        file = fileDao.findOne(Example.of(file)).orElseGet(() -> {
            File fileAdd = new File();
            BeanUtils.copyProperties(reqDTO, fileAdd);
            fileAdd.setName(CodeGenerateUtils.codeGenerateByPrefix("f"));
            fileAdd.setPath(java.io.File.pathSeparator);
            return fileDao.saveAndFlush(fileAdd);
        });
        //查询订单信息
        OrderOuter orderOuter = new OrderOuter();
        orderOuter.setId(reqDTO.getOrderId());
        OrderOuter order = orderOuterDao.findOne(Example.of(orderOuter)).orElseThrow(() -> new MyException(OrderResultEnums.ORDER_NULL));

        return null;
    }
}
