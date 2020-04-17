//package com.dlf.business.manager.sys.impl;
//
//import com.dlf.business.anno.ValidateAnno;
//import com.dlf.business.manager.sys.ServerService;
//import com.dlf.model.dao.sys.ServerDao;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.sys.ServerReqDTO;
//import com.dlf.model.dto.sys.ServerSearchDTO;
//import com.dlf.model.po.sys.Server;
//import com.google.common.collect.ImmutableList;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Slf4j
//public class ServerServiceImpl implements ServerService {
//
//    @Resource
//    ServerDao serverDao;
//
//    private static final ImmutableList<String> keyWordFilter = ImmutableList.of("username", "password", "login-username", "login-password");
//
//
//    @Override
//    @ValidateAnno(names = {"name", "ip", "port", "sourcePath", "serverPath", "configPath"})
//    public GlobalResultDTO addOrEdit(ServerReqDTO reqDTO) {
//        Server server = new Server();
//        BeanUtils.copyProperties(reqDTO, server);
//        serverDao.save(server);
//        return GlobalResultDTO.SUCCESS();
//    }
//
//    @Override
//    public GlobalResultDTO queryPage(ServerSearchDTO searchDTO) {
//        System.out.println("ServerServiceImpl.queryPage");
//        Server server = new Server();
//        BeanUtils.copyProperties(searchDTO, server);
//        Example<Server> search = Example.of(server);
//        PageRequest pageRequest = PageRequest.of(searchDTO.getPageNum() - 1, searchDTO.getPageSize());
//        Page<Server> result = serverDao.findAll(search, pageRequest);
//        return GlobalResultDTO.SUCCESS(result);
//    }
//
//    @Override
//    public GlobalResultDTO configPath(ServerSearchDTO searchDTO) {
//        try (
//            BufferedReader reader = new BufferedReader(new FileReader(new File(searchDTO.getConfigPath())));
//        )
//        {
//            List<String> result = new ArrayList<>();
//            while (reader.ready()){
//                String line = reader.readLine();
//                String[] lineSplit = line.split(":");
//                String compareKey = lineSplit[0].trim();
//                if(keyWordFilter.contains(compareKey)){
//                    line = lineSplit[0] + ": " + "****";
//                }
//                result.add(line);
//            }
//            return GlobalResultDTO.SUCCESS(result);
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return GlobalResultDTO.FAIL(e.getMessage());
//        }
//    }
//}
