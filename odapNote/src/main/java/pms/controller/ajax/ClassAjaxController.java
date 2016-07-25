package pms.controller.ajax;

import java.io.IOException;
import java.util.ArrayList;
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
import pms.service.QuestionService;
import pms.vo.Class;
import pms.vo.ClassList;
import pms.vo.ClassWithName;
import pms.vo.Member;

@Controller
@RequestMapping("/ajax/class/")
public class ClassAjaxController {
@Autowired ClassService classService;
@Autowired CmemberService cmemberService;
@Autowired QuestionService questionService;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
  @ResponseBody
  public String add(String cnm, String csub, String cdes, HttpSession session) throws ServletException, IOException {

    Class clazz = new Class();
    clazz.setMno(((Member)session.getAttribute("loginUser")).getMno());
    System.out.println(cnm);
    System.out.println(csub);
    System.out.println(cdes);

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
  
  @RequestMapping(value="search",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String search(String key) throws ServletException, IOException {
      List<ClassList> lists = new ArrayList<ClassList>();
      List<ClassWithName> list2 = classService.search(key);  
      for(int i=0; i<list2.size(); i++){
        ClassList classList = new ClassList();
        classList.setClasswithName(list2.get(i));
        classList.setQuestioncount(questionService.countInClass(list2.get(i).getCno()));
        classList.setMembercount(cmemberService.countAll(list2.get(i).getCno()));
        lists.add(classList);
      }
      return new Gson().toJson(lists);
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
    List<ClassList> lists = new ArrayList<ClassList>();
    List<ClassWithName> list2 = classService.myclasslist(mno);  
    for(int i=0; i<list2.size(); i++){
      ClassList classList = new ClassList();
      classList.setClasswithName(list2.get(i));
      classList.setQuestioncount(questionService.countInClass(list2.get(i).getCno()));
      classList.setMembercount(cmemberService.countAll(list2.get(i).getCno()));
      lists.add(classList);
    }
    return new Gson().toJson(lists);
  }
  
  @RequestMapping(value="myClass", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String myClass(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    int mno = member.getMno();
    List<ClassList> lists = new ArrayList<ClassList>();
    List<ClassWithName> list2 = classService.myClass(mno);  
    for(int i=0; i<list2.size(); i++){
      ClassList classList = new ClassList();
      classList.setClasswithName(list2.get(i));
      classList.setQuestioncount(questionService.countInClass(list2.get(i).getCno()));
      classList.setMembercount(cmemberService.countAll(list2.get(i).getCno()));
      lists.add(classList);
    }
    return new Gson().toJson(lists);
  }
  
  
  @RequestMapping(value="mememe", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String mememe(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    int mno = member.getMno();
    List<ClassWithName> list = classService.myClass(mno);    
    
    return new Gson().toJson(list);
  }
  
  
  
}