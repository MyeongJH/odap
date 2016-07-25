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
import pms.vo.Class;
import pms.vo.Cmember;
import pms.vo.JoinCmember;
import pms.vo.Member;

@Controller
@RequestMapping("/ajax/cmember/")
public class CmemberAjaxController {
@Autowired ClassService classService;
@Autowired CmemberService cmemberService;

  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8", method=RequestMethod.POST)
  @ResponseBody
  public String add(int cno, String cmitr, HttpSession session) throws ServletException, IOException {
    System.out.println(cmitr);
    Member member = (Member)session.getAttribute("loginUser");
    System.out.println(member);
    Cmember cmember = new Cmember();
    cmember.setMno(member.getMno());    
    cmember.setCno(cno);
    cmember.setCmitr(cmitr);
    cmember.setCmst(false);
    System.out.println(cno);
    System.out.println(cmitr);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      cmemberService.add(cmember);
      result.put("status", "success");
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }    
    return new Gson().toJson(result);
  }


  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list(int cno) 
      throws ServletException, IOException {    
    
    List<JoinCmember> list = cmemberService.list(cno);
    return new Gson().toJson(list);
  }

  @RequestMapping(value="delete",
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int mno, int cno) throws ServletException, IOException {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("mno", mno);
    paramMap.put("cno", cno);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      cmemberService.delete(paramMap);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  
  @RequestMapping(value="apply",
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String apply(int mno, int cno) throws ServletException, IOException {
    HashMap<String,Object> paramMap = new HashMap<>();
  paramMap.put("mno", mno);
  paramMap.put("cno", cno);
    
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      cmemberService.apply(paramMap);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
      e.printStackTrace();
    }
    return new Gson().toJson(result);
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
  
  @RequestMapping(value="/memorn",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public boolean memorn(HttpSession session, int cno) throws ServletException, IOException {    
    
    HashMap<String,Object> paramMap = new HashMap<>();
    Member member = (Member)session.getAttribute("loginUser");
    System.out.println(cno);
    paramMap.put("cno", cno);
    paramMap.put("mno", member.getMno());
    boolean result = cmemberService.isCmember(paramMap);   
    return result;
  }
  
  @RequestMapping(value="/ismaster",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public boolean ismaster(HttpSession session, int cno) throws ServletException, IOException {    
    int classmaster = classService.retrieve(cno).getMno();
    Member member = (Member)session.getAttribute("loginUser");
    System.out.println(classmaster);
    System.out.println(member.getMno());
    if(classmaster == member.getMno()) {
      return true;
    }       
    return false;
  }
  
  
}