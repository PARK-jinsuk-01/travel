package com.example.travel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @Autowired
    HttpSession session;

    @GetMapping("/")
    public String index(Model model) {

        System.out.println(session.getAttribute("email"));
        model.addAttribute("email",  session.getAttribute("email"));

        return "/index/index";
    }
}
