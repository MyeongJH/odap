package src.service;

import java.util.List;

import src.vo.Question;

public interface QuestionService {
  void add(Question question);
  void delete(int no);
  Question retrieve(int no);
  List<Question> list(int pageNo, int pageSize);
  void change(Question quetion);
  int countPage(int pageSize);
}
