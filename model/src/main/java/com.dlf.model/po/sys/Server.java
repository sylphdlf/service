package com.dlf.model.po.sys;

import com.dlf.model.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "s_server")
@EqualsAndHashCode(callSuper = true)
public class Server extends BasePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ip;
    private Integer port;
    private String sourcePath;
    private String serverPath;
    private String configPath;
}
