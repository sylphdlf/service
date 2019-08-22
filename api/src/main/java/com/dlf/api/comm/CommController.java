//package com.dlf.api.comm;
//
//import com.dlf.business.exception.MyException;
//import com.dlf.business.manager.comm.CommService;
//import com.dlf.model.dto.BaseReqDTO;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.comm.AreaCodeReqDTO;
//import com.dlf.model.dto.message.MsgReqDTO;
//import com.dlf.model.dto.user.UserReqDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/comm")
//public class CommController {
//
//    @Autowired
//    CommService commService;
//
//    @RequestMapping(value = "/msgSend", method = RequestMethod.POST)
//    public GlobalResultDTO msgSend(@RequestBody BaseReqDTO<MsgReqDTO> reqDTO) throws MyException {
//        return commService.msgSend(reqDTO.getData());
//    }
//
//    @RequestMapping(value = "/queryAreaCode", method = RequestMethod.POST)
//    public GlobalResultDTO queryAreaCode(@RequestBody BaseReqDTO<AreaCodeReqDTO> reqDTO){
//        return commService.queryAreaCode(reqDTO.getData());
//    }
//
//    @RequestMapping(value = "/getImgCode", method = RequestMethod.POST)
//    public GlobalResultDTO getImgCode(@RequestBody BaseReqDTO<UserReqDTO> reqDTO){
//        return commService.getImgCode(reqDTO.getData());
//    }
//}
