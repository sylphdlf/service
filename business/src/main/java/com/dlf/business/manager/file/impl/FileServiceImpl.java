package com.dlf.business.manager.file.impl;

import com.dlf.business.anno.ValidateAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.file.FileService;
import com.dlf.common.utils.CodeGenerateUtils;
import com.dlf.model.dao.comm.FileDao;
import com.dlf.model.dao.comm.FileUserDao;
import com.dlf.model.dao.order.OrderFileDao;
import com.dlf.model.dao.order.OrderOuterDao;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileReqDTO;
import com.dlf.model.enums.order.OrderResultEnums;
import com.dlf.model.po.comm.File;
import com.dlf.model.po.comm.FileUser;
import com.dlf.model.po.order.OrderFile;
import com.dlf.model.po.order.OrderOuter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileDao fileDao;
    @Resource
    FileUserDao fileUserDao;
    @Resource
    OrderOuterDao orderOuterDao;
    @Resource
    OrderFileDao orderFileDao;
    @Value("${comm.upload.root}")
    private String filePath;

    @Override
    @Transactional
    @ValidateAnno(names = {"md5","orderId"})
    public GlobalResultDTO save(FileReqDTO reqDTO) {
        AtomicReference<String> fileSavePath = new AtomicReference<>("");
        //查询是否有重复文件, 没有则新增
        File fileReq = new File();
        fileReq.setMd5(reqDTO.getMd5());
        File file = fileDao.findOne(Example.of(fileReq)).orElseGet(() -> {
            File fileAdd = new File();
            BeanUtils.copyProperties(reqDTO, fileAdd);
            fileAdd.setName(CodeGenerateUtils.codeGenerateByPrefix("f"));//自定义文件名
            fileAdd.setPath(java.io.File.separator);//相对路径
            fileSavePath.set(filePath + fileAdd.getPath() + fileAdd.getName() + "." + fileAdd.getSuffix());
            return fileDao.saveAndFlush(fileAdd);
        });

        //查询订单信息
        OrderOuter orderOuter = new OrderOuter();
        orderOuter.setId(reqDTO.getOrderId());
        OrderOuter order = orderOuterDao.findOne(Example.of(orderOuter))
                .orElseThrow(() -> new MyException(OrderResultEnums.ORDER_NULL));

        //新增用户-文件关联关系
        FileUser fileUser = new FileUser();
        fileUser.setFileId(file.getId());
        fileUser.setUserId(order.getUserId());
        fileUser = fileUserDao.findOne(Example.of(fileUser)).orElseGet(() -> {
            FileUser fileUserAdd = new FileUser();
            fileUserAdd.setFileId(file.getId());
            fileUserAdd.setUserId(order.getUserId());
            fileUserAdd.setName(Optional.ofNullable(reqDTO.getFileName()).orElse(file.getName()));
            return fileUserDao.saveAndFlush(fileUserAdd);
        });

        //查询orderFile, 没有则新增
        OrderFile orderFile = new OrderFile();
        orderFile.setOrderId(order.getId());
        FileUser finalFileUser = fileUser;
        orderFileDao.findOne(Example.of(orderFile)).map(t -> {
            //去重
            boolean noneMatch = Arrays.stream(t.getFileId().split(",")).noneMatch(o -> o.equals(finalFileUser.getId() + ""));
            if(noneMatch){
                t.setFileId(StringUtils.isBlank(t.getFileId()) ? finalFileUser.getId() + "" : "," + finalFileUser.getId());
                return orderFileDao.save(t);
            }
            return t;
        }).orElseGet(() -> {
            //新增orderFile
            orderFile.setFileId(finalFileUser.getId() + "");
            return orderFileDao.save(orderFile);
        });
        return GlobalResultDTO.SUCCESS(fileSavePath.get());
    }
}
