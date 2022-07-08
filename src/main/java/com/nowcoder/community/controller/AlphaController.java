package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHell(){
       return "bey~";
    }
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }
    //演示在SpringMVC中如何获得请求对象和响应对象
    //请求对象封装请求数据 响应对象 封装响应数据
    //实际上SpringMVC对上述做了封装，有更简便的方式，单位了学习，了解底层实现是很重要的
    @RequestMapping("/http")
    //没有返回值是因为通过response对象可以直接想浏览器返回数据 所以这里是void
    public void http(HttpServletRequest request, HttpServletResponse response){
       //获取请求数据 状态行
       System.out.println(request.getMethod());
       System.out.println(request.getServletPath());//请求的路径
        //请求头 是key value 结构 很老的迭代器
       Enumeration<String> enumeration= request.getHeaderNames();
       while(enumeration.hasMoreElements()){
           String name = enumeration.nextElement();
           String value = request.getHeader(name);
           System.out.println(name+":"+value);
       }
      //请求体
        System.out.println(request.getParameter("code"));
      //返回响应数据
        response.setContentType("text/html;charset=utf-8");//生么样的响应内容
        try(
                //自动在finally里面close输出流
                PrintWriter writer=response.getWriter();)//获取输出流
         {

            writer.write("<h1>牛客网<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //GET请求 用于获取数据
    // /students?current=1&limit=20 第几页多少条数据
    //声明请求的路径
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current",required = false,defaultValue = "1") int current,
            @RequestParam(name="limit",required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
//GET /student/123 直接把参数编排到路径的一部分
    //使用路径变量 PathVariable
@RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
}

//POST请求 浏览器提交数据 需要通过网页 所以需要创建一个网页来提交post请求
    //template是动态的 这里要创建一个可以提交表单静态的网页 存放在static下
    //创建一个html目录 在目录下床架一个html文件
//    如何获取post请求中的参数，方法中的参数和静态页面的表单中的参数名一致就可以获得
@RequestMapping(path="/student",method=RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
         System.out.println(name);
         System.out.println(age);
        return "success";
}

//响应
//如何向浏览器响应一个动态的html数据
//浏览器查老师，服务器查到 ，响应给浏览器
    //不加@ResponseBody注解 默认返回html
    //返回类型ModelAndView 封装了dispatchServlet所需要的model和view
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
          ModelAndView modelAndView =new ModelAndView();
          //村动态的值
        modelAndView.addObject("name","zhangdan");
        modelAndView.addObject("age",20);
        //template文件旧爱不需要，但其下级目录demo需要，这里的view也就是view.html
        //在template路径下创建demo文件夹，在demo文件夹下创建view.html文件，
        //需要在view中声明这个是模板，而不是普通的html文件 xmlns:th="http://www.thymeleaf.org"
        modelAndView.setViewName("/demo/view");
       return modelAndView;
    }

    //更简单的方式
    //dispatherServelt发现参数列表的Model对象，就会自动创建model传给模板，dispatcherservlet拥有这个对象的引用
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","pking");
        model.addAttribute("age","99");
        return "/demo/view";
    }
    //响应Json数据（异步请求）
    //异步请求-如注册b站--输入昵称--显示改昵称是否被占用
    //显示操作时访问了服务器的，但是当前的网页并没有刷新，所以返回的不是html，这就是异步
    //Java对象 --> 通过Json返回给 -->Js对象
    //因为不加ResponseBody注解，默认返回的是html，所以这里返回json需要加上这个注解,
    // 自动把，map转换为json格式
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String, Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age",22);
        emp.put("salary",8000.00);
      return emp;

    }


    //返回所有的员工
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list =new ArrayList<>();
        Map<String, Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age",22);
        emp.put("salary",8000.00);
        list.add(emp);
        emp=new HashMap<>();
        emp.put("name","张三1");
        emp.put("age",21);
        emp.put("salary",9000.00);
        list.add(emp);
        return list;

    }
}
