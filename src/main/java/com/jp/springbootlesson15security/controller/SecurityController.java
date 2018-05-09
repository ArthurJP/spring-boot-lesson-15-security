package com.jp.springbootlesson15security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO:
 *
 * @author <a href="mailto:zhangtylord@gmail.com>张俊鹏</a>
 * @see
 * @since 5/7/2018
 */
@Controller
public class SecurityController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("/xss")
    public String xss(Model model) {

        // JS Code 需要被 Escape
        model.addAttribute("jsCode", "<script>alert('XSS attack')</script>");
        // HTML Code 不需要被 Escape（Unescape）
        model.addAttribute("htmlCode", "<span>Hello,World</span>");

        return "xss";
    }

}
