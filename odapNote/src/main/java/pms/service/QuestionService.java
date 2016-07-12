package pms.service;

import java.util.List;

import pms.vo.Question;

public interface QuestionService {
  void add(Question question);
  void delete(int no);
  Question retrieve(int no);
  List<Question> list();
  List<Question> mylist(int mno);
  List<Question> classlist(int cno);
  void change(Question quetion);
  int countPage(int pageSize);
  List<Question> search(String key);
  List<Question> searchInclass(String key,int cno);
  int questionNo(int no);
}
