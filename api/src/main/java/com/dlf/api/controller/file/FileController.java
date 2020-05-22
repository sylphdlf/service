package com.dlf.api.controller.file;

import com.dlf.business.manager.file.FileService;
import com.dlf.business.manager.order.OrderService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileReqDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;

    @RequestMapping(value = "/save")
    public GlobalResultDTO save(@RequestBody FileReqDTO reqDTO){

        return fileService.save(reqDTO);
    }

    @RequestMapping(value = "/rollback")
    public GlobalResultDTO rollback(){

        return GlobalResultDTO.SUCCESS();
    }

}
