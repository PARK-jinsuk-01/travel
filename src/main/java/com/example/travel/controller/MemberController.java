package com.example.travel.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // @GetMapping("/signin")
    // public String signinPage() {
    // return "sign/signin";
    // }

    // @PostMapping("/signin")
    // public String signinPost(@ModelAttribute Member member, Model model) {
    // Member dbUser = memberRepository.findByEmail(member.getEmail());

    // if (dbUser != null) {
    // if (passwordEncoder.matches(member.getPw(), dbUser.getPw())) {
    // // 로그인 성공
    // session.setAttribute("user_id", dbUser.getId());
    // session.setAttribute("user_email", dbUser.getEmail());
    // return "redirect:/";
    // } else {
    // model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
    // }
    // } else {
    // model.addAttribute("error", "사용자가 존재하지 않습니다.");
    // }
    // return "sign/signin";
    // }

    @GetMapping("/signup")
    public String signup() {
        return "sign/signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    @Transactional
    public String signupPost(@ModelAttribute Member member, BindingResult bindingResult) {
        // 빈 필드 검사
        if (member.getEmail() == null || member.getEmail().isEmpty() ||
            member.getPw() == null || member.getPw().isEmpty() ||
            member.getPhone() == null || member.getPhone().isEmpty()) {
            return "모든 필드를 작성해주세요.";
        }
       
        String birth = member.getYy() + "-" + member.getMm() + "-" + member.getDd();
        member.setBirth(birth);
        System.out.println(birth);

        Member emailCheck = memberRepository.findByEmail(member.getEmail());
        if (emailCheck != null) {
            return "이미 가입된 이메일입니다.";
        }
        Member phoneCheck = memberRepository.findByPhone(member.getPhone());
        if (phoneCheck != null) {
            return "중복된 핸드폰 번호입니다.";
        }

        String rawPassword = member.getPw();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        member.setPw(encodedPassword);

        memberRepository.save(member);
        return "가입이 완료되었습니다.";
    }

    @RequestMapping("/checkEmailAvailability")
    @ResponseBody
    public String checkEmailAvailability(@RequestParam String email) {
        Member emailCheck = memberRepository.findByEmail(email);
        if (emailCheck != null) {
            return "가입불가";
        } else {
            return "가입가능";
        }
    }
}
