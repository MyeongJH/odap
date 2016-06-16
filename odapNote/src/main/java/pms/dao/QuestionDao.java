package pms.dao;

import java.util.List;

import pms.vo.Question;

public interface QuestionDao {
  List<Question> selectList();
  int insert(Question question);
  Question selectOne(int no);
  int update(Question question);
  int delete(int no);
  int countAll();
}
