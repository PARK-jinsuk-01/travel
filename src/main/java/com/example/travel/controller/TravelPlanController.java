package com.example.travel.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.travel.model.Member;
import com.example.travel.model.TravelPlan;
import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.TravelPlanRepository;

@Controller
public class TravelPlanController {
    
    @Autowired
    HttpSession session;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TravelPlanRepository travelPlanRepository;

    @GetMapping("/plan")
    public String plan(Model model) { 
        List<TravelPlan> plans = travelPlanRepository.findAll();
        model.addAttribute("plans", plans);
        model.addAttribute("email",  session.getAttribute("email"));
         // 각 게시물의 첫 번째 이미지 찾기
    for (TravelPlan plan: plans) {
        String firstImageTag = getFirstImageTag(plan.getContent());
        plan.setContent(firstImageTag);  // 여기서는 'content' 필드에 첫 번째 이미지 태그만 저장
    }

    model.addAttribute("plans", plans);
    
    return "/board/travelplan";
}

private String getFirstImageTag(String content) {
    Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
    Matcher matcher = pattern.matcher(content);
    
    if (matcher.find()) {
        return matcher.group();
    } else {
        return "<img src='/img/logo.png'>";  // 이미지가 없는 경우 기본 사이트 logo로 반환
    }
}

    @PostMapping("/plan")
    public String planpost(
        @ModelAttribute Member member,
        @ModelAttribute TravelPlan travelplan,
        Model model) {
            

        return "/plan";
    }
}
