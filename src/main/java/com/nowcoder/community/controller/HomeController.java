package com.nowcoder.community.controller;

import com.nowcoder.community.dao.DisscussPostMapper;
import com.nowcoder.community.entity.DisscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    //返回String 为视图的名字--模板的路径 方法响应的是网页不用responbody
    //需要Model 返回数据传给模板
    //为了分页 传入Page
    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法嗲用前，S平日那个MVC会自动实例化Model和Page，并将Page注入Model
        //所以在Thymeleaf中可以直接访问对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

      List<DisscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        List<Map<String,Object>> discussPosts =new ArrayList<>();
        if(list !=null) {
            for (DisscussPost post:list){
            Map<String,Object> map=new HashMap<>();
            map.put("post",post);
            User user = userService.findUserById(post.getUserId());
            map.put("user",user);
            discussPosts.add(map);
            }

        }
        //给页面展示的结果存入model 返回给模板
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
