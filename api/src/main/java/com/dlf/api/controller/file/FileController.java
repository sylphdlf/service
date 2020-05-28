package com.dlf.api.controller.file;

import com.dlf.business.manager.file.FileService;
import com.dlf.business.manager.order.OrderService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.file.FileReqDTO;
import com.dlf.model.dto.file.FileSearchDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController{

    @Resource
    FileService fileService;

    @RequestMapping(value = "/saveFromOd")
    public GlobalResultDTO saveFromOd(@RequestBody FileReqDTO reqDTO){

        return fileService.saveFromOd(reqDTO);
    }

    @RequestMapping(value = "/save")
    public GlobalResultDTO save(@RequestBody FileReqDTO reqDTO){

        return fileService.save(reqDTO);
    }

    @RequestMapping(value = "/rollback")
    public GlobalResultDTO rollback(){

        return GlobalResultDTO.SUCCESS();
    }

    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
    public GlobalResultDTO queryPage(@RequestBody FileSearchDTO searchDTO) {
        return fileService.queryPage(searchDTO);
    }

    @RequestMapping(value = "/queryPageForUser", method = RequestMethod.POST)
    public GlobalResultDTO queryPageForUser(@RequestBody FileSearchDTO searchDTO) {
        return fileService.queryPageForUser(searchDTO);
    }
}
