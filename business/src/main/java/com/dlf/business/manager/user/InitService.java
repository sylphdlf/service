package com.dlf.business.manager.user;

import java.io.File;

public interface InitService {
    /**
     * 项目第一次启动后的初始化操作
     * @param files
     */
    void init(File... files);

    void initSource();

    void initAdmin();
}
