package pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.AnswerDao;
import pms.service.AnswerService;
import pms.vo.Answer;

@Service
public class DefaultAnswerService implements AnswerService {
  @Autowired AnswerDao answerDao;

  public void add(Answer answer) {
    answerDao.insert(answer);
  }

  public void delete(int no) {
    answerDao.delete(no);
  }

  public void change(Answer answer) {
    answerDao.update(answer);
  }
  
  public List<Answer> userAnswer(int no) {
    return answerDao.selectList(no);
  }
  public Answer answer(int no) {
    return answerDao.selectOne(no);
  }
  
  public int count(int qno) {
    return answerDao.count(qno);
  }

}
