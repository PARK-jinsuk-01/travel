package com.example.travel.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.travel.model.Member;
import com.example.travel.model.TravelBoard;
import com.example.travel.model.TravelLike;
import com.example.travel.model.TravelPlan;
import com.example.travel.model.View;
import com.example.travel.repository.TravelLikeRepository;
import com.example.travel.repository.MemberRepository;
import com.example.travel.repository.TravelBoardRepository;
import com.example.travel.repository.TravelPlanRepository;
import com.example.travel.repository.ViewRepository;

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

    @Autowired
    ViewRepository viewRepository;

    @Autowired
    TravelLikeRepository travelLikeRepository;

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable int id, Model model) {
        Optional<TravelBoard> boardOptional = travelBoardRepository.findById(id);
        if (!boardOptional.isPresent()) {
            // 게시글이 없는 경우의 처리
            // 예: 404 페이지로 리다이렉트
            return "redirect:/error/404";
        }
        TravelBoard board = boardOptional.get();
        model.addAttribute("travelBoard", board);
        // 조회수를 가져옵니다.

        // 로그인한 사용자 정보를 가져옵니다.
        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent()) {
                // 조회수를 증가시킵니다.
                addView(member.get(), board);
            }
        }

        System.out.println(board.getId());// 1
        int viewCount = board.getViewCount();
        model.addAttribute("viewCount", viewCount);

        return "board/boardDetail";
    }

    @GetMapping("/plan/{id}")
    public String planDetail(@PathVariable int id, Model model) {
        Optional<TravelPlan> planOptional = travelPlanRepository.findById(id);
        if (!planOptional.isPresent()) {
            // 게시글이 없는 경우의 처리
            // 예: 404 페이지로 리다이렉트
            return "redirect:/error/404";
        }
        TravelPlan plan = planOptional.get();
        model.addAttribute("travelPlan", plan);
        // 조회수를 가져옵니다.

        // 로그인한 사용자 정보를 가져옵니다.
        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");
            Optional<Member> member = memberRepository.findById(memberId);
            if (member.isPresent()) {
                // 조회수를 증가시킵니다.
                addView(member.get(), plan);
            }
        }

        System.out.println(plan.getId());// 1
        int viewCount = plan.getViewCount();
        model.addAttribute("viewCount", viewCount);

        return "board/planDetail";
    }

    //////////////////////////////// 조회수 board
    public void addView(Member member, TravelBoard travelBoard) {
        if (!isAlreadyViewedToday(member, travelBoard)) {
            View view = new View();
            view.setMember(member);
            view.setTravelBoard(travelBoard);
            viewRepository.save(view);
        }
    }

    private boolean isAlreadyViewedToday(Member member, TravelBoard travelBoard) {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        System.out.println(LocalDateTime.now());
        Optional<View> views = viewRepository.findByMemberAndTravelBoardAndViewTimeBetween(
                member,
                travelBoard,
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS),
                LocalDateTime.now());
        System.out.println(views.isPresent());
        return views.isPresent();
    }

    //////////////////////////////// 조회수 plan

    public void addView(Member member, TravelPlan travelPlan) {
        if (!isAlreadyViewedToday(member, travelPlan)) {
            View view = new View();
            view.setMember(member);
            view.setTravelPlan(travelPlan);
            viewRepository.save(view);
        }
    }

    private boolean isAlreadyViewedToday(Member member, TravelPlan travelPlan) {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
        System.out.println(LocalDateTime.now());
        Optional<View> views = viewRepository.findByMemberAndTravelPlanAndViewTimeBetween(
                member,
                travelPlan,
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS),
                LocalDateTime.now());
        System.out.println(views.isPresent());
        return views.isPresent();
    }

    ///////////////////////////// Like 버튼

    @ResponseBody
    @PostMapping("/pbtnlike")
    public Map<String, Object> planLikePost(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        Integer planId = requestMap.get("planId");

        Integer memberId = (Integer) session.getAttribute("id");
    try {

        if (memberId == null) {
            // 로그인 x
            map.put("msg", "로그인 후 이용해주세요.");
            map.put("result", false); // 좋아요 또는 취소 기능 수행 실패
            return map;
        }

        Optional<TravelLike> opt = travelLikeRepository.findByMemberIdAndTravelPlanId(memberId, planId);
        if(opt.isPresent()) {
            TravelLike like = opt.get();
            travelLikeRepository.delete(like);
            map.put("msg", "좋아요를 취소했습니다.");
            map.put("result", true);
            return map;
        } else { // 좋아요 데이터 없음
            TravelLike like = new TravelLike();
            Member member = new Member();
            TravelPlan plan = new TravelPlan();

            member.setId(memberId);
            plan.setId(planId);

            like.setMember(member);
            like.setTravelPlan(plan);
            travelLikeRepository.save(like);

            map.put("msg", "좋아요를 눌렀습니다.");
            map.put("result", true);
            return map;
        }
    } catch (Exception e) {
        e.printStackTrace();
        map.put("msg", "좋아요 처리중 요류.");
        map.put("result", false);

        return map;
    }
}

@ResponseBody
@PostMapping("/pcheckLike")
public Map<String, Object> planCheckLike(@RequestBody Map<String, Integer> requestMap) {
    Integer memberId = requestMap.get("memberId");
    Integer planId = requestMap.get("planId");
    Map<String, Object> map = new HashMap<>();
    try {
        TravelLike like = travelLikeRepository.findByMemberIdAndTravelPlanId(memberId, planId).orElse(null);

        if (like != null) {
            //값이 있을때 활성상태
            map.put("status", true);
            map.put("like", travelLikeRepository.countByTravelPlanId(planId));
            return map;
        } else {
            //값이 없을때 비활성화상태
            map.put("status", false);
            map.put("like", travelLikeRepository.countByTravelPlanId(planId));
            return map;
        }
    } catch (Exception e) {
        map.put("status", false);
        map.put("statustext","좋아요 확인 중 오류가 발생했습니다." );
        return map;
    }
}

   @ResponseBody
    @PostMapping("/bbtnlike")
    public Map<String, Object> boardLikePost(@RequestBody Map<String, Integer> requestMap) {
        Map<String, Object> map = new HashMap<>();
        Integer boardId = requestMap.get("boardId");

        Integer memberId = (Integer) session.getAttribute("id");
    try {

        if (memberId == null) {
            // 로그인 x
            map.put("msg", "로그인 후 이용해주세요.");
            map.put("result", false); // 좋아요 또는 취소 기능 수행 실패
            return map;
        }

        Optional<TravelLike> opt = travelLikeRepository.findByMemberIdAndTravelBoardId(memberId, boardId);
        if(opt.isPresent()) {
            TravelLike like = opt.get();
            travelLikeRepository.delete(like);
            map.put("msg", "취소했습니다.");
            map.put("result", true);
            return map;
        } else { // 좋아요 데이터 없음
            TravelLike like = new TravelLike();
            Member member = new Member();
            TravelBoard board = new TravelBoard();

            member.setId(memberId);
            board.setId(boardId);

            like.setMember(member);
            like.setTravelBoard(board);
            travelLikeRepository.save(like);

            map.put("msg", "좋아요 성공");
            map.put("result", true);
            return map;
        }
    } catch (Exception e) {
        e.printStackTrace();
        map.put("msg", "좋아요 처리중 요류.");
        map.put("result", false);

        return map;
    }
}

@ResponseBody
@PostMapping("/bcheckLike")
public Map<String, Object> boardCheckLike(@RequestBody Map<String, Integer> requestMap) {
    Integer memberId = requestMap.get("memberId");
    Integer boardId = requestMap.get("boardId");
    Map<String, Object> map = new HashMap<>();
    try {
        TravelLike like = travelLikeRepository.findByMemberIdAndTravelBoardId(memberId, boardId).orElse(null);

        if (like != null) {
            //값이 있을때 활성상태
            map.put("status", true);
            map.put("like", travelLikeRepository.countByTravelBoardId(boardId));
            return map;
        } else {
            //값이 없을때 비활성화상태
            map.put("status", false);
            map.put("like", travelLikeRepository.countByTravelBoardId(boardId));
            return map;
        }
    } catch (Exception e) {
        map.put("status", false);
        map.put("statustext","좋아요 확인 중 오류가 발생했습니다." );
        return map;
    }
}
}

