package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//入口配置类用SpringBootApplication注解
//普通的配置类用@Configation

@Configuration
public class AlphaConfig {
    //装配SimpleDateGormat 实例化一次反复用
    //方法名就是Bean的名字
    @Bean
    public SimpleDateFormat simpleDateFormat(){
       return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }
}
