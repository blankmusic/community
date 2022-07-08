package com.nowcoder.community;

import com.nowcoder.community.dao.DisscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DisscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
//ApplicationContextAware 是为了手动的获取Beans，现在不需要手动，直接通过注解注入，所以不用实现ApplicationContextAware接口
public class MpperTests {
  /*  private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }*/
    @Autowired
    private UserMapper userMapper;
    @Autowired
    DisscussPostMapper disscussPostMapper;
    @Test
    public void testSelectUser(){
        User user=userMapper.selectById(101);
        System.out.println(user);
        user =userMapper.selectByName("Liubei");
        System.out.println(user);
        user =userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }
   @Test
    public void testInsertUser(){
        User user =new User();
        user.setUsername("ceshi");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        user.setSalt("asdd");
        user.setHeaderUrl("http://www.mowcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(user);
        System.out.println(rows);

   }
   @Test
    public void testUpdateUser(){
        int rows =userMapper.updateStatus(151,1);
        System.out.println(rows);
        rows=userMapper.updateHeader(151,"http://www.nowcoer.com/150.png");
        System.out.println(rows);
        User user=  userMapper.selectById(151);
        System.out.println(user);

   }
    @Test
    public void testSelectPosts(){
       List<DisscussPost> list= disscussPostMapper.selectDiscussPosts(149,0,10);
       for(DisscussPost post:list){
           System.out.println(post);
       }
       int rows = disscussPostMapper.selectDiscussPostRows(149);
       System.out.println(rows);
    }
}
