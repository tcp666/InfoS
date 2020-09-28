package com.keshe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/5/27 8:32
 */
@Controller
public class ViueController {

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }
}
