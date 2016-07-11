package pms.service;

import java.util.List;

import pms.vo.Question;

public interface QuestionService {
  void add(Question question);
  void delete(int no);
  Question retrieve(int no);
  List<Question> list();
  List<Question> mylist(int mno);
  void change(Question quetion);
  int countPage(int pageSize);
  List<Question> search(String key);
}
