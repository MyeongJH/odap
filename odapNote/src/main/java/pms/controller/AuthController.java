package pms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pms.service.MemberService;
import pms.vo.Member;

@Controller
@RequestMapping("/auth")
@SessionAttributes("loginUser")
public class AuthController {
  @Autowired MemberService memberService;
  
  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String form() {    
    return "redirect:../ver4_han/signup.html";
  }
  
  @RequestMapping(value="/login", method=RequestMethod.POST)
  public String login(
      String id,
      String password,
      Model model) {
    if (memberService.exist(id, password)) {
      Member member = memberService.retrieveById(id);
      model.addAttribute("loginUser", member);
      return "redirect:../ver4_han/firstpage2.html";
    } else { // 로그인 실패!
      return "redirect:login.do";
    }
  }  
 
  
  @RequestMapping("/logout")
  public String logout(HttpSession session, SessionStatus status) {
    status.setComplete(); // @SessionAttributes 로 관리하는 값 제거
    session.invalidate(); // HttpSession 객체 무효화시킨다.
                          // => invalidate()는 스프링에서 @SessionAttributes로
                          //    관리하는 값을 제거하지 못한다.
    return "redirect:login.do";
  }

  @RequestMapping(value="/overlap",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public boolean overlap(String id) throws ServletException, IOException {    
    boolean result = memberService.existId(id);   
    return result;
  }
}