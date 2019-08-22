//package com.dlf.api.comm;
//
//import com.dlf.business.manager.comm.DictService;
//import com.dlf.model.dto.BaseReqDTO;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.comm.DictReqDTO;
//import com.dlf.model.dto.comm.DictSearchDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/dict")
//public class DictController {
//    @Autowired
//    DictService dictService;
//
//    @RequestMapping(value = "/getAreaCode",method = RequestMethod.POST)
//    public GlobalResultDTO getAreaCode() throws Exception{
//        return dictService.getAreaCode();
//    }
//
//    @RequestMapping(value = "/getKeyValue",method = RequestMethod.POST)
//    public GlobalResultDTO getKeyValue(@RequestBody BaseReqDTO<DictReqDTO> reqDTO){
//        return dictService.getKeyValue(reqDTO.getData());
//    }
//
//    @RequestMapping(value = "/queryPageByParams",method = RequestMethod.POST)
//    public GlobalResultDTO queryPageByParams(@RequestBody BaseReqDTO<DictSearchDTO> searchDTO){
//        return dictService.queryPageByParams(searchDTO.getData());
//    }
//
//    @RequestMapping(value = "/addOrEdit",method = RequestMethod.POST)
//    public GlobalResultDTO addOrEdit(@RequestBody BaseReqDTO<DictReqDTO> reqDTO){
//        return dictService.addOrEdit(reqDTO.getData());
//    }
//
//    @RequestMapping(value = "/delDict",method = RequestMethod.POST)
//    public GlobalResultDTO delDict(@RequestBody BaseReqDTO<DictReqDTO> reqDTO){
//        return dictService.delDict(reqDTO.getData());
//    }
//}
