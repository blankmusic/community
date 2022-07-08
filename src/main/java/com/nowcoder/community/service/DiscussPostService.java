package com.nowcoder.community.service;

import com.nowcoder.community.dao.DisscussPostMapper;
import com.nowcoder.community.entity.DisscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//这一层的实现无论有多么简单，都需要，不能直接通过controller层直接调用，不利于后期维护
@Service
public class DiscussPostService {

    @Autowired
    DisscussPostMapper disscussPostMapper;
/*    userId是外键，后面显示的时候显示的用户的名字，有两种方式
    1 直接在sql中查询，并增加实体类的属性username
    2 对已经查出来的userid再一次，查询username 并在后期和disscusspots实体关联
            这里选择2，为了后期使用redis更直观和高效。
            这个二次查询的方法需要放在userService中。
            */
    public List<DisscussPost> findDiscussPosts(int userId,int offset,int limit){
        return  disscussPostMapper.selectDiscussPosts(userId,offset,limit);
    }
    public int findDiscussPostRows(int userId){
        return disscussPostMapper.selectDiscussPostRows(userId);
    }

}

