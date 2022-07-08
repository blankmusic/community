package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoggerTest {
    private static final Logger logger= LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void testLogger(){
        //还需要在配置文件中配置使用的级别
        // 系统上线之后不能打印控制台 需要存储到指定的文件里 properties配置
        System.out.println(logger.getName());
        logger.debug("debug Log");
        logger.info("infor Log");;
        logger.warn("warn log");
        logger.error("error log");
    }
}
