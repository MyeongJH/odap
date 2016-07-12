package pms.controller.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pms.service.ClassService;
import pms.service.CmemberService;
import pms.service.MemberService;
import pms.service.QuestionService;
import pms.vo.Class;
import pms.vo.Member;
import pms.vo.Question;

@Controller
@RequestMapping("/ajax/class/")
public class ClassAjaxController {
@Autowired ClassService classService;
@Autowired CmemberService cmemberService;
@Autowired QuestionService questionService;
@Autowired MemberService memberService;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(String cnm, String csub, String cdes, HttpSession session) throws ServletException, IOException {

    Class clazz = new Class();
    clazz.setMno(((Member)session.getAttribute("loginUser")).getMno());
    clazz.setCnm(cnm);
    clazz.setCsub(csub);
    clazz.setCdes(cdes);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      classService.add(clazz);
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
      classService.delete(no);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="detail", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int no) throws ServletException, IOException {
    Class clazz = classService.retrieve(no);
    return new Gson().toJson(clazz);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list() 
      throws ServletException, IOException {    
    
    List<Class> list = classService.list();
    return new Gson().toJson(list);
  }
  
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int no, String cnm, String cdes) throws ServletException, IOException {
    
    Class clazz = new Class();
    clazz.setCno(no);
    clazz.setCnm(cnm);
    clazz.setCdes(cdes);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      classService.change(clazz);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  
  @RequestMapping(value="classmaster", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String classmaster(int cno) throws ServletException, IOException {
    Class clazz = classService.retrieve(cno);
    int classmaster = clazz.getMno();
    return new Gson().toJson(classmaster);
  }
  
  @RequestMapping(value="myclasslist", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String myclasslist(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    int mno = member.getMno();
    List<Class> lists = classService.myclasslist(mno);
    for (Class list : lists) {
      list.setTeacherName(memberService.retrieve(list.getMno()).getMnm());
      list.setCmemberNo(cmemberService.cmemberNo(list.getCno()));
      list.setQuestionNo(questionService.questionNo(list.getCno()));
    }
    
    return new Gson().toJson(lists);
  }
  
  @RequestMapping(value="search",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String search(String key) throws ServletException, IOException {
      System.out.println(key);
      List<Question> list = classService.search(key);
    return new Gson().toJson(list);
  }
  
}