package pms.service;

import java.util.List;

import pms.vo.Answer;

public interface AnswerService {
  void add(Answer answer);
  void change(Answer answer);
  void delete(int no);
  List<Answer> userAnswer(int no);
  Answer answer(int no);
  int count(int qno);
}
