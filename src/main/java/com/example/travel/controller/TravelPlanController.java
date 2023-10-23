package com.example.travel.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.TravelBoardRepository;

@Controller
public class TravelPlanController {
    
        @Autowired
    HttpSession session;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TravelBoardRepository TravelBoardRepository;

    @GetMapping("/plan")
    public String plan(){
        return "/board/travelplan";
    }
}
