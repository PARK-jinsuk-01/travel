package com.example.travel.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.travel.model.Member;
import com.example.travel.repository.MemberRepository;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    HttpSession session;

    @GetMapping("/signup")
    public String signup() {
        return "sign/signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    @Transactional
    public String signupPost(@RequestBody Map<String, Object>data, BindingResult bindingResult, HttpSession session) {
        // 빈 필드 검사
        String email =(String) data.get("email");
        String pw =(String) data.get("pw");
        String name =(String) data.get("name");
        String phone =(String) data.get("phone");
        String yy =(String) data.get("yy");
        String mm =(String) data.get("mm");
        String dd =(String) data.get("dd");
        

        System.out.println(data.get("yy"));
        if (email == null || email.isEmpty() ||
           pw == null || pw.isEmpty() ||
           phone == null || phone.isEmpty()) {
            return "모든 필드를 작성해주세요.";
        }

        String birth = (String)(yy + "-" + mm + "-" + dd);  
        
        

        
        System.out.println(birth);
        // 세션 데이터와 입력 데이터 비교
       
        Member emailCheck = memberRepository.findByEmail(email);
        if (emailCheck != null) {
            return "이미 가입된 이메일입니다.";
        }
        Member phoneCheck = memberRepository.findByPhone(phone);
        if (phoneCheck != null) {
            return "중복된 핸드폰 번호입니다.";
        }

        String rawPassword = pw;
        String encodedPassword = passwordEncoder.encode(rawPassword);
        
        Member member = new Member();
        
        member.setEmail(email);
        member.setPw(encodedPassword);
        member.setName(name);
        member.setPhone(phone);
        member.setYy(yy);
        member.setMm(mm);
        member.setDd(dd);
        member.setBirth(birth);

        // Member 엔터티를 데이터베이스에 저장
        memberRepository.save(member);

        return "가입이 완료되었습니다.";
    }

    @GetMapping("/checkEmailAvailability")
    @ResponseBody
    public String checkEmailAvailability(String email) {
        Member emailCheck = memberRepository.findByEmail(email);
        if (emailCheck != null) {
            return "가입불가";
        } else {
            return "가입가능";
        }
    }
}
