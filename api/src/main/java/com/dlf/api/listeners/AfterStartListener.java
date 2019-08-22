package com.dlf.api.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created by Administrator on 2017/5/7.
 */
@Component
public class AfterStartListener implements CommandLineRunner{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化操作
     * @param strings
     * @throws IOException
     */
    public void run(String... strings) throws IOException {
        logger.info("初始化方法-------------------------");
    }
}
