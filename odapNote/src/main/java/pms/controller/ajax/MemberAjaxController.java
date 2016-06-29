package pms.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import pms.service.MemberService;
import pms.vo.Member;

@Controller
@RequestMapping("/ajax/member/")
public class MemberAjaxController {
  @Autowired
  MemberService memberService;
  @Autowired
  ServletContext servletContext;

  @RequestMapping(value = "add", produces = "application/json;charset=UTF-8")
  public String add(String id, String password, String name, String tel, String email, HttpSession session)
      throws ServletException, IOException {

    Member member = new Member();
    member.setMid(id);
    member.setMem(email);
    member.setMpw(password);
    member.setMnm(name);
    member.setMtel(tel);

    try {
      memberService.add(member);
      Member member2 = memberService.retrieveById(id);
      session.setAttribute("loginUser", member2);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "redirect:../../ver1_m/mypage_han.html";
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
  public String detail(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    System.out.println(member);
    return new Gson().toJson(member);
  }
  
  @RequestMapping(value="classmaster", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String classMaster(int mno) throws ServletException, IOException {
    Member member = memberService.retrieve(mno);
    System.out.println(member);
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
  public String update(int no, String pw, String name,String email, String tel, String adress, String job, String homepage) throws ServletException, IOException {
    Member member = new Member();
    member.setMno(no);
    member.setMpw(pw);
    member.setMnm(name);
    member.setMem(email);
    member.setMtel(tel);
    member.setMadr(adress);
    member.setMjob(job);
    member.setMpg(homepage);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      memberService.change(member);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
      e.printStackTrace();
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value = "upload", method = RequestMethod.POST)
  public String insert(MultipartHttpServletRequest request,HttpSession session, String mpw,String mnm, String mtel, String mem, String mjob, String madr, String mpg){
    Member member = (Member)session.getAttribute("loginUser");
    
    Map<String, MultipartFile> files = request.getFileMap();
    CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("uploadFile");
    int extPoint = cmf.getOriginalFilename().lastIndexOf(".");
    String filename="";
    if (extPoint > 0){
      filename = System.currentTimeMillis() + cmf.getOriginalFilename().substring(extPoint);      
      System.out.printf("새파일명=%s\n", filename);
      
      String realPath = servletContext.getRealPath("ver1_m/img/" + filename);
      System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
      try {
        member.setMpic("img/"+filename);
        cmf.transferTo(new File(realPath));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    member.setMpw(mpw);
    member.setMnm(mnm);
    member.setMem(mem);
    member.setMtel(mtel);
    member.setMadr(madr);
    member.setMjob(mjob);
    member.setMpg(mpg);
    try {
      memberService.change(member);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:../../ver1_m/profile.html";
  }
}
