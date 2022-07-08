package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;


//有注解spring容器才能扫描和装配
//和数据库交互的类用的注解是
//自定义bean的名字 通过名字获取bean
@Repository("alphaHibernate")
public class AlphaDaoHibernateImp implements  AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
