package pms.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import net.coobird.thumbnailator.Thumbnails;
import pms.service.AnswerService;
import pms.vo.Answer;

@Controller
@RequestMapping("/ajax/answer/")
public class AnswerAjaxController {
@Autowired AnswerService answerService;
@Autowired
ServletContext servletContext;
  
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(String title, String content) throws ServletException, IOException {

    Answer answer = new Answer();
    HashMap<String,Object> result = new HashMap<>();
    try {
      answerService.add(answer);
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
      answerService.delete(no);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="deletelog", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String deletelog(int qno) throws ServletException, IOException {
    File logfile = new File("C:/bitcamp/workspace/WEBSOC~1/file/data_room"+qno+".txt");
    System.out.println("exists : " + logfile.exists());
    HashMap<String,Object> result = new HashMap<>();
    try {
    if(logfile.exists()) {
      boolean delFlag = logfile.delete();
      System.out.println("삭제여부 : " + delFlag);
    } 
    result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list(int qno) 
      throws ServletException, IOException {    
    
    List<Answer> list = answerService.userAnswer(qno);
    return new Gson().toJson(list);
  }
  
  @RequestMapping(value="count", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String count(int qno) 
      throws ServletException, IOException {
    System.out.println(qno);
    int count = answerService.count(qno);
    return new Gson().toJson(count);
  }
  
  @RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(MultipartFile image, int qno) throws ServletException, IOException {
    String filename = qno+"QNO"+System.currentTimeMillis()+".jpg";
    System.out.printf("새파일명=%s\n", filename);
    
    String realPath = servletContext.getRealPath("ver4_han/img/" + filename);
    System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
    image.transferTo(new File(realPath));
    String[] splits = realPath.split(filename);
    
    Thumbnails.of(realPath).size(260, 215).toFile(new File(splits[0]+"thumb_"+filename));
    
    Answer answer = new Answer();
    answer.setAcon(filename);
    answer.setQno(qno);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      answerService.add(answer);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
}
