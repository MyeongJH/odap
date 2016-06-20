package pms.controller.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pms.service.MemberService;
import pms.vo.Member;

@Controller
@RequestMapping("/ajax/member/")
public class MemberAjaxController {
@Autowired MemberService memberService;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(String id, String pw, String name, String tel, String adress, String job, String hompage, String pic, String clazz, String mem) throws ServletException, IOException {

    Member member = new Member();
    member.setMid(id);
    member.setMem(mem);
    member.setMpw(pw);
    member.setMnm(name);
    member.setMtel(tel);
    member.setMadr(adress);
    member.setMjob(job);
    member.setMpg(hompage);
    member.setMpic(pic);
    member.setMcl(clazz);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      memberService.add(member);
      result.put("status", "success");
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="delete", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int no) 
      throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      memberService.delete(no);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="detail", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int no) throws ServletException, IOException {
    Member member = memberService.retrieve(no);
    return new Gson().toJson(member);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list() 
      throws ServletException, IOException {    
    
    List<Member> list = memberService.list();
    return new Gson().toJson(list);
  }
  
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int no, String id, String pw, String name, String tel, String adress, String job, String hompage, String pic, String clazz,String mem) throws ServletException, IOException {
    
    Member member = new Member();
    member.setMno(no);
    member.setMid(id);
    member.setMpw(pw);
    member.setMnm(name);
    member.setMtel(tel);
    member.setMadr(adress);
    member.setMjob(job);
    member.setMpg(hompage);
    member.setMpic(pic);
    member.setMcl(clazz);
    member.setMem(mem);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      memberService.change(member);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
}
