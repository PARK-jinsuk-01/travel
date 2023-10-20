package com.example.travel.email;

import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mailer {

  

  @PostMapping("/emailcheck")
  // @PostMapping("/emailcheck")
  @ResponseBody
  public String sendMail(@RequestBody Map<String, Object> data,
       SMTPAuthenticator smtp, HttpSession session) {
        
        String email= (String)data.get("email");
    Properties p = new Properties();
    String response= "123123321";
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "465");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.debug", "true");
    p.put("mail.smtp.socketFactory.port", "465");
    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    p.put("mail.smtp.socketFactory.fallback", "false");

    Random random = new Random();
    

   String content= "";
    for ( int i=0; i<5; i++){
        content += random.nextInt(10);

    }
    
    try {
      Session ses = Session.getInstance(p, smtp);
      ses.setDebug(true);
      MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
      msg.setSubject("subject"); // 제목
      Address fromAddr = new InternetAddress("rudals23yo@gmail.com"); //보내는 사람의 메일
      msg.setFrom(fromAddr); // 보내는 사람
      Address toAddr = new InternetAddress(email);
      msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
      msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
      session.setAttribute("content",content);
      System.out.println("#@#@#@#@#@#fxddxfxdf@#"+session.getAttribute("content"));
      
      Transport.send(msg); // 전송
      System.out.println("@@@@@@@@@@@@"+123);
       response=content;
    } catch (Exception e) {
      e.printStackTrace();
            System.out.println("@@@@@@@@@@@@x");

      response="전송실패";
    }
    
    return response;
  }


  // @PostMapping("/emailcheck")
  // @GetMapping("/emailcheck")
  // @ResponseBody
  // public String checkEmail(@RequestBody Map<String, Object> data, HttpSession session) {
  //     // String emailCheck = (String) data.get("echeck");
  //     String sessionData = (String) session.getAttribute("content");
  //     if(sessionData != null){
  //       System.out.println(sessionData+"&&&&&&&&&&&&&&&&&");
  //       return "";
  //     }else{
  //       return "";
  //     }
      
      // if (emailCheck.equals(sessionData)) {
      //   System.out.println("#########");
      //     return "인증 성공";
      // } else {
      //     return "인증 실패";
      // }
  // }
}
