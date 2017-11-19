package com.aggregator.utils;


import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class LogUtil {
//    private final static Logger logger = (Logger) LoggerFactory.getLogger(LogUtil.class);

    /**
     * 动态改变日志输出路径
     *
     * @param comId:商家编号
     * @author yangliang
     * @created 2011-9-17 下午03:22:41
     */
    public static void WriteLog(String comId) {
// 初始化logger后，通过获取logger的appender，来修改appender写入文件的文件名
        Appender appender = Logger.getRootLogger().getAppender("file");
        if (appender instanceof FileAppender) {
            FileAppender fappender = (FileAppender) appender;

// 设置文件的输出路径
//	fappender.setFile("D:/" + comId + ".log");
            fappender.setFile("D:/" + comId + ".log");

// 通过调用appender的activateOptions方法来激活针对appender的修改，
// 之后的日志就记录在新的日志文件中了
            fappender.activateOptions();
        }
    }

    public static void main(String[] args) {
//        PropertyConfigurator.configure("D:/log4j.properties");// 读取log4j配置文件
        Logger logger = Logger.getLogger(LogUtil.class);

        String comId = "yangliang";// 根据需求的不同输出到不同的日志文件

// 初始化logger后，通过获取logger的appender，来修改appender写入文件的文件名
        Appender appender = Logger.getRootLogger().getAppender("file");
        if (appender instanceof FileAppender) {
            FileAppender fappender = (FileAppender) appender;
            fappender.setFile("D:/log/" + comId + ".log");

// 通过调用appender的activateOptions方法来激活针对appender的修改，之后的日志就记录在新的日志文件中了
            fappender.activateOptions();
        }

        for (int i = 0; i < 3; i++) {
            logger.info("-----测试log4j--------- ");
            logger.error(" ----测试log4j---------  ");
        }
        String test = "test.log";
        System.setProperty("log.name", test);

        logger.info(System.getProperty("log.name"));
    }
}
