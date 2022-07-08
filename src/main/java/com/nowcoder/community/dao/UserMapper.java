package com.nowcoder.community.dao;


import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
 //声明一些增删改查的方法
//写明对应的sql的配置文件
    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    //返回插入的行数
    int insertUser(User user);

    // 返回修改的条数
    int updateStatus(int id,int status);

    int updateHeader(int id ,String headerUrl);

    int updatePassword(int id,String password);

}
