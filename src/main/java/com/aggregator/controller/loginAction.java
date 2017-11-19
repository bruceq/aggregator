package com.aggregator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 鑫 on 2017/6/24.
 */
@Controller
public class loginAction {

    @RequestMapping("login.do")
    public ModelAndView login(String username, String password) {
        if ("admin".equals(username) || "admin".equals(password)) {
            System.out.println(username + " 登录成功");
            return new ModelAndView("loginSuccess");//逻辑视图名       跳转页面默认为转发
        }
        return new ModelAndView("loginError");
    }
}