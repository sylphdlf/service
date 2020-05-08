package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserSearchDTO extends PageDTO {

    //用户名
    private String username;
    //手机
    private String mobile;
    //组织机构代码
    private String orgCode;

}
