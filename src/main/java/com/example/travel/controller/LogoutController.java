package com.example.travel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.travel.repository.MemberRepository;

@Controller
public class LogoutController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HttpSession session;
 
    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.removeAttribute("id");
        return result;
    }
}
