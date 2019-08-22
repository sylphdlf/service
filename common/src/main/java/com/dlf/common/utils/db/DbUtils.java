package com.dlf.common.utils.db;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtils{

    private Logger logger = LoggerFactory.getLogger(DbUtils.class);

    private Connection conn = null;
    private ScriptRunner runner = null;

    public DbUtils(String driver, String url, String username, String password) throws Exception{
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        runner = new ScriptRunner(conn);
        //下面配置不要随意更改，否则会出现各种问题
        runner.setAutoCommit(true);//自动提交
        runner.setFullLineDelimiter(false);
        runner.setDelimiter(";");////每条命令间的分隔符
        runner.setSendFullScript(false);
        runner.setStopOnError(false);
        //runner.setLogWriter(null);//设置是否输出日志
    }

    /**
     * 执行sql脚本
     * @param inputStream
     * @throws Exception
     */
    public void execSqlStreamByMysql(InputStream... inputStream) throws Exception{
        try {
            //如果又多个sql文件，可以写多个runner.runScript(xxx),
            for(InputStream thisStream : inputStream){
                runner.runScript(new InputStreamReader(thisStream,"utf-8"));
            }
            conn.close();
        } catch (Exception e) {
            logger.error("执行sql文件失败....",e);
            throw e;
        }finally{
            if(null != conn){
                conn.close();
            }
        }
    }

    /**
     * 执行sql脚本
     * @param files
     * @throws Exception
     */
    public void execSqlFileByMysql(File... files) throws Exception{
        try {
            for(File thisFile : files){
                runner.runScript(new FileReader(thisFile));
            }
            conn.close();
        } catch (Exception e) {
            logger.error("执行sql文件失败....",e);
            throw e;
        }finally{
            if(null != conn){
                conn.close();
            }
        }
    }
}
