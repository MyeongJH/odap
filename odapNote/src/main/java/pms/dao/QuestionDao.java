package pms.dao;

import java.util.List;
import java.util.Map;

import pms.vo.Question;

public interface QuestionDao {
  List<Question> selectList();
  List<Question> selectMyList(int mno);
  List<Question> selectClassList(int cno);
  int insert(Question question);
  Question selectOne(int no);
  int update(Question question);
  int delete(int no);
  int countAll();
  List<Question> search(Map<String,Object> paramMap);
  List<Question> searchInclass(Map<String,Object> paramMap);
}
