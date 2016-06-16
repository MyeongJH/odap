package src.dao;

import java.util.List;
import java.util.Map;

import src.vo.Question;

public interface QuestionDao {
  List<Question> selectList(Map<String,Object> paramMap);
  int insert(Question question);
  Question selectOne(int no);
  int update(Question question);
  int delete(int no);
  int countAll();
}
