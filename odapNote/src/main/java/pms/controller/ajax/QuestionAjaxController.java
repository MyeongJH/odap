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

import net.coobird.thumbnailator.Thumbnails;
import pms.service.QuestionService;
import pms.vo.Member;
import pms.vo.Question;

@Controller
@RequestMapping("/ajax/question/")
public class QuestionAjaxController {
@Autowired QuestionService questionService;
@Autowired ServletContext servletContext;
  
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
  public String list() throws ServletException, IOException {    
    List<Question> list = questionService.list();
    return new Gson().toJson(list);
  }
  
  @RequestMapping(value="mylist", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String mylist(HttpSession session) throws ServletException, IOException {    
    Member member = (Member)session.getAttribute("loginUser");
    List<Question> list = questionService.mylist(member.getMno());
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
  
  @RequestMapping(value="search",
      method=RequestMethod.GET,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String search(String key) throws ServletException, IOException {
      System.out.println(key);
      List<Question> list = questionService.search(key);
    return new Gson().toJson(list);
  }
  
  @RequestMapping(value = "upload", method = RequestMethod.POST)
  public String insert(MultipartHttpServletRequest request,HttpSession session, String qtit, String qcnt){
    Member member = (Member)session.getAttribute("loginUser");
    Question question = new Question();
    Map<String, MultipartFile> files = request.getFileMap();
    CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("uploadFile");
    int extPoint = cmf.getOriginalFilename().lastIndexOf(".");
    System.out.println(extPoint);
    String filename="";
    if (extPoint > 0){
      filename =  System.currentTimeMillis() + cmf.getOriginalFilename().substring(extPoint);
      System.out.printf("새파일명=%s\n", filename);
      //File thumbnailFileNm = new File("C:/bitcamp/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp6/wtpwebapps/odapNote/ver4_han/img/thum_"+filename);
      //int width = 50;
      //int height = 50;
   // 썸네일 이미지 생성
      String realPath = servletContext.getRealPath("ver4_han/img/" + filename);
      
      System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
      String[] splits = realPath.split(filename);
      try {
        question.setQpic("img/"+filename);
        cmf.transferTo(new File(realPath));
        //BufferedImage thumbnailImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //ImageIO.write(thumbnailImg, "jpg", thumbnailFileNm);
        Thumbnails.of(realPath).size(400, 285).toFile(new File(splits[0]+"thumb_"+filename));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    question.setCno(10);
    question.setQtit(qtit);
    question.setQcnt(qcnt);
    question.setQst(false);
    question.setMno(member.getMno());
   
    try {
      questionService.add(question);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:../../ver4_han/firstpage2.html";
  }
  
  
}
