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

import pms.service.QuestionService;
import pms.vo.Question;

@Controller
@RequestMapping("/ajax/question/")
public class QuestionAjaxController {
@Autowired QuestionService questionService;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(String title, String content) throws ServletException, IOException {

    Question question = new Question();
    question.setQtit(title);
    question.setQcnt(content);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      questionService.add(question);
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
      questionService.delete(no);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="detail", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int no) throws ServletException, IOException {
    Question question = questionService.retrieve(no);
    return new Gson().toJson(question);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list() 
      throws ServletException, IOException {    
    
    List<Question> list = questionService.list();
    return new Gson().toJson(list);
  }
  
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int no, String title, String content) throws ServletException, IOException {
    
    Question question = new Question();
    question.setQno(no);
    question.setQtit(title);
    question.setQcnt(content);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      questionService.change(question);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
}
