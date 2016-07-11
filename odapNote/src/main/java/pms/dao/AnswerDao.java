package pms.dao;

import java.util.List;

import pms.vo.Answer;

public interface AnswerDao {
  int update(Answer answer);
  int delete(int no);
  List<Answer> selectList(int no);
  Answer selectOne(int no);
  void insert(Answer answer);
  int count(int quno);
}