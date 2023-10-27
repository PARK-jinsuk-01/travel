package com.example.travel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.travel.model.Member;
import com.example.travel.model.TravelBoard;
import com.example.travel.model.TravelPlan;
import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.TravelBoardRepository;
import com.example.travel.repository.TravelPlanRepository;

@Transactional
@Controller
public class WriteConroller {

    @Autowired
    HttpSession session;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TravelBoardRepository travelBoardRepository;

    @Autowired
    TravelPlanRepository travelPlanRepository;

    @GetMapping("/write")
    public String write() {

        return "/board/travelwrite";
    }

    @PostMapping("/write")
    @ResponseBody    
    public Map<String, Object> writepost(
            @RequestParam() String category,
            @RequestParam() String title,
            @RequestParam() String content,
            @RequestParam() Integer id) {
        Map<String, Object> map = new HashMap<>();

                Member member = memberRepository.findById(id).get();
                System.out.println(id);
        if ("plan".equals(category)) {
            TravelPlan travelPlan = new TravelPlan();
            travelPlan.setTitle(title);
            travelPlan.setContent(content);
            travelPlan.setMember(member);
            TravelPlan savedTravelPlan = travelPlanRepository.save(travelPlan);

            map.put("msg", "여행계획 저장 완료");
            map.put("code", 200);
        }else if ("board".equals(category)) {
            TravelBoard travelBoard = new TravelBoard();
            travelBoard.setTitle(title);
            travelBoard.setContent(content);
            travelBoard.setMember(member);

            TravelBoard savedTravelBoard = travelBoardRepository.save(travelBoard);

            map.put("msg", "여행 저장 완료");
            map.put("code", 201);
        } else if (session == null){
        map.put("msg", "로그인을 해주세요.");
        }
        return map;
    }

}