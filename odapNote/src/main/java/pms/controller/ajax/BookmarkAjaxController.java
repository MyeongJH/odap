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

import pms.service.BookmarkService;
import pms.vo.Bookmark;
import pms.vo.Member;
import pms.vo.Question;

@Controller
@RequestMapping("/ajax/bookmark/")
public class BookmarkAjaxController {
@Autowired BookmarkService bookmarkService;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(HttpSession session, int qno) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    Bookmark bookmark = new Bookmark();
    bookmark.setMno(member.getMno());
    bookmark.setQno(qno);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      bookmarkService.add(bookmark);
      result.put("status", "success");
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="delete", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(HttpSession session, int qno) throws ServletException, IOException {
    HashMap<String,Object> paramMap = new HashMap<>();
    Member member = (Member)session.getAttribute("loginUser");
    paramMap.put("qno", qno);
    paramMap.put("mno", member.getMno());
    int bno = bookmarkService.findBookmark(paramMap);
    HashMap<String,Object> result = new HashMap<>();
    try {
      bookmarkService.delete(bno);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(HttpSession session) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    System.out.println(member);
    List<Question> list = bookmarkService.userBookmark(member.getMno());
    return new Gson().toJson(list);
  }
  
   
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int bno, int mno, int qno) throws ServletException, IOException {
    
    Bookmark bookmark = new Bookmark();
    bookmark.setMno(mno);
    bookmark.setQno(qno);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      bookmarkService.change(bookmark);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="/isbookmark",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public boolean isbookmark(HttpSession session, int qno) throws ServletException, IOException {    
    
    HashMap<String,Object> paramMap = new HashMap<>();
    Member member = (Member)session.getAttribute("loginUser");
    paramMap.put("qno", qno);
    paramMap.put("mno", member.getMno());
    boolean result = bookmarkService.isBookmark(paramMap);   
    return result;
  }
  
  @RequestMapping(value="/findBookmark",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public int findBookmark(HttpSession session, int qno) throws ServletException, IOException {    
    
    HashMap<String,Object> paramMap = new HashMap<>();
    Member member = (Member)session.getAttribute("loginUser");
    paramMap.put("qno", qno);
    paramMap.put("mno", member.getMno());
    int result = bookmarkService.findBookmark(paramMap);   
    return result;
  }
  
}
