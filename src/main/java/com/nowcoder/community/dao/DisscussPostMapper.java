package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DisscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DisscussPostMapper {
    //个人主页我发布的帖子功能
    //d动态SQl有时候需要userid 有时候不需要
    //SQL有文章

    /**
     * 分页查询
     * @param userId 可有可无 动态拼接
     * @param offset 分页的起始行号
     * @param limit  每一页最多多少行
     * @return
     */
    List<DisscussPost> selectDiscussPosts(int userId,int offset,int limit);
     //为了显示页码 需要知道一共有多少条数据

    /**
     * @Param 给参数取别名，如果需要动态的<if></>拼接这个条件，而这个方法又只有一个参数
     * 这个参数一定要有别名
     * @param userId
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);

}
