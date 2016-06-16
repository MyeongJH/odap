package src.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.dao.QuestionDao;
import src.service.QuestionService;
import src.vo.Question;

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

  public List<Question> list(int pageNo, int pageSize) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);

    return questionDao.selectList(paramMap);
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

