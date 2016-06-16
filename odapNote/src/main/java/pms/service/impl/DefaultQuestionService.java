package pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.QuestionDao;
import pms.service.QuestionService;
import pms.vo.Question;

@Service
public class DefaultQuestionService implements QuestionService {
  @Autowired QuestionDao questionDao;

  public void add(Question question) {
    questionDao.insert(question);
  }

  public void delete(int no) {
    questionDao.delete(no);
  }

  public Question retrieve(int no) {
    return questionDao.selectOne(no);
  }

  public List<Question> list() {
    return questionDao.selectList();
  }

  public void change(Question question) {
    questionDao.update(question);
  }

  public int countPage(int pageSize) {
    int count = questionDao.countAll();
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }
}

