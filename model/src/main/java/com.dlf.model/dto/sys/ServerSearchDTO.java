package com.dlf.model.dto.sys;

import com.dlf.model.dto.BaseReqDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerSearchDTO extends BaseReqDTO {

    private String name;
    private String ip;
    private Integer port;
    private String serverPath;
    private String configPath;
}
