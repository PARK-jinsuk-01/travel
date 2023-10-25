package com.example.travel.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travel.model.TravelBoard;
import com.example.travel.model.TravelPlan;
import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.TravelBoardRepository;
import com.example.travel.repository.TravelPlanRepository;

@Controller
public class TravelDetail {

    @Autowired
    HttpSession session;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TravelPlanRepository travelPlanRepository;

    @Autowired
    TravelBoardRepository travelBoardRepository;

    @GetMapping("/board/{id}")
    public String boardDetail(
        @PathVariable int id,
            Model model) {
    
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+id);
                Optional<TravelBoard> board = travelBoardRepository.findById(id);
                model.addAttribute("travelBoard", board.get());
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+board.get());
        return "board/boardDetail";
    }
    
    @GetMapping("/plan/{id}")
    public String planDetail(
        @PathVariable int id,
            Model model) {
    
        Optional<TravelPlan> plan = travelPlanRepository.findById(id);
        model.addAttribute("travelPlan", plan.get());
        return "board/planDetail";
    }

    @GetMapping("/delet")
    public String delet(
            @ModelAttribute TravelBoard board,
            @ModelAttribute TravelPlan plan) {
        int loggedName = (int) session.getAttribute("id");
        Optional<TravelBoard> boardId = travelBoardRepository.findById(board.getId());
        Optional<TravelPlan> planId = travelPlanRepository.findById(plan.getId());

        System.out.println(loggedName);
        System.out.println(boardId);

        // int bwriter = boardId.getMember().getId();
        // int pwriter = planId.getMember().getId();

        // if (bwriter == loggedName && pwriter == loggedName) {
        //     travelBoardRepository.delete(boardId);
        //     travelPlanRepository.delete(planId);

   
        System.out.println("erfefvrfgv" + board.getId());
        return "redirect:/list";
    }
}
