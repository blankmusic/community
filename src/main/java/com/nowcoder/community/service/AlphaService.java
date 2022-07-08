package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//业务注解
//如何使容器管理这个类的初始化之后并销毁
//在方法上增加@PostConstruct管理初始化方法
@Service
//默认是Singleton
//多个prototype
//@Scope("prototype")
public class AlphaService {
    //这里没有用Qualifier 使用得是@primary默认的mybatis的实例
    @Autowired
    AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");

    }
    //管理初始化方法
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }
    //管理销毁 在销毁对象之前调用
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }
    public String find(){
        return alphaDao.select();
    }

}
