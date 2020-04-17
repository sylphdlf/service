//package com.dlf.api.controller.sys;
//
//import com.dlf.business.manager.sys.ServerService;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.sys.ServerReqDTO;
//import com.dlf.model.dto.sys.ServerSearchDTO;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//@RequestMapping(value = "/serv")
//public class ServerController {
//
//    @Resource
//    ServerService serverService;
//
//    @RequestMapping(value = "/queryPage", method = RequestMethod.POST)
//    public GlobalResultDTO queryPage(@RequestBody ServerSearchDTO searchDTO){
//        return serverService.queryPage(searchDTO);
//    }
//
//    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
//    public GlobalResultDTO addOrEdit(@RequestBody ServerReqDTO reqDTO){
//        return serverService.addOrEdit(reqDTO);
//    }
//
//    @RequestMapping(value = "/configPath", method = RequestMethod.POST)
//    public GlobalResultDTO configPath(@RequestBody ServerSearchDTO searchDTO){
//        return serverService.configPath(searchDTO);
//    }
//}
