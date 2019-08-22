//package com.dlf.api.file;
//
//import com.dlf.business.exception.MyException;
//import com.dlf.business.manager.file.ChatFileService;
//import com.dlf.business.manager.file.FileService;
//import com.dlf.model.dto.BaseReqDTO;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.file.FileReqDTO;
//import com.dlf.model.dto.file.FileResDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    @Value("${comm.upload.root}")
//    private String fileUploadRoot;
//    @Autowired
//    FileService fileService;
//    @Autowired
//    ChatFileService chatFileService;
//
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public GlobalResultDTO uploadFile(@RequestParam("userId") String userId,
//                                      @RequestParam("targetUserId") String targetUserId,
//                                      @RequestPart("file")MultipartFile file) throws MyException {
//        return chatFileService.uploadFile(userId, targetUserId, file);
//    }
//
//    /**
//     * 以流的方式获取图片
//     * @param response
//     * @param type
//     */
//    @RequestMapping(value = "/showImg")
//    public void showImg(HttpServletResponse response, @RequestParam("userId")String userId,
//                        @RequestParam("targetUserId")String targetUserId,
//                        @RequestParam("id")String id,
//                        @RequestParam("type") Integer type) throws IOException{
//        GlobalResultDTO<FileResDTO> resultDTO = chatFileService.getFilePath(id, userId, targetUserId, type);
//        FileInputStream inputStream = new FileInputStream(resultDTO.getData().getPath());
//        int i = inputStream.available();
//        //byte数组用于存放图片字节数据
//        byte[] buff = new byte[i];
//        final int read = inputStream.read(buff);
//        //记得关闭输入流
//        inputStream.close();
//        //设置发送到客户端的响应内容类型
//        response.setContentType("image/*");
//        OutputStream out = response.getOutputStream();
//        out.write(buff);
//        out.close();
//    }
//}
