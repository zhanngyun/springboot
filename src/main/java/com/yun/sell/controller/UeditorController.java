package com.yun.sell.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: yzhang
 * @Date: 2018/2/1 15:40
 */
@Controller
@RequestMapping("/config")
public class UeditorController {



    @RequestMapping("/showPage")
    private String showPage(){
        return "ueditor";
    }



    @RequestMapping(value = "/config")
    public void config(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        String rootpath = request.getSession().getServletContext().getRealPath("/");
        String action = request.getParameter("action");
        System.out.println(action);
        try{
            String exec = new ActionEnter(request, rootpath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
