package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserSearchDTO extends PageDTO {

    private Long id;
    //用户名
    private String username;
    //真实姓名
    private String realName;
    //手机
    private String mobile;
    //座机
    private String telephone;
    //性别
    private Integer gender;
    //组织机构代码
    private String orgCode;

}
