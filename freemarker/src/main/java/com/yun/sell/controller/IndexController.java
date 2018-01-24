package com.yun.sell.controller;

import com.yun.sell.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: yzhang
 * @Date: 2018/1/19 9:17
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/welcome")
    public String welcome(Map<String, Object> model, HttpServletRequest request, Model model2){
        model.put("username","yun");
        User user = new User();
        user.setUsername("yzhang");
        user.setPassword("123");
        model.put("user",user);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        model.put("list",list);
        model.put("readonly","readonly");
        model2.addAttribute("aa",12);
        model2.addAttribute("lastUpdated",new Date());
        model2.addAttribute("foo",false);
        return "index";
    }


}
