package com.example.travel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.travel.model.Member;
import com.example.travel.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignContoller {
    private static final Logger logger = LoggerFactory.getLogger(SignContoller.class);

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/signin")
    public String signin() {
        return "/sign/signin";
    }

    @PostMapping("/signin")
    @ResponseBody
    public Map<String, Object> signinpost(@ModelAttribute Member member2,
        @RequestBody Member member) {
        Map<String, Object> result = new HashMap<>();

        if(member.getEmail() == null || member.getEmail().isEmpty() || member.getPw() == null || member.getPw().isEmpty()) {
            log.error("User entered email: " + member.getEmail() + " and password: " + member.getPw());
            result.put("code", -1);
            result.put("errorMsg", "Email과 Pw를 입력해주세요.");
            } else {
            Member existingMember = memberRepository.findByEmail(member.getEmail());
            if (existingMember != null) {
                String encodedPw = existingMember.getPw();
                if (passwordEncoder.matches(member.getPw(), encodedPw)) {
                    session.setAttribute("email", existingMember.getEmail());
                    session.setAttribute("name", existingMember.getName());
                    session.setAttribute("id", existingMember.getId());
                    

                    result.put("code", 1);
                    result.put("errorMsg", "로그인 성공");
                    logger.info("비밀번호 일치: " + existingMember.getEmail());
                } else {
                    result.put("code", 2);
                    result.put("errorMsg", "Email과 Pw를 확인해주세요");
                    logger.error("비밀번호 불일치: " + existingMember.getEmail());
                }
            } else {
                result.put("code", 3);
                result.put("errorMsg", "사용자가 존재하지 않습니다");
            }
        }
        return result;
    }
    }

