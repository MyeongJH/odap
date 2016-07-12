package pms.dao;

import java.util.List;
import java.util.Map;

import pms.vo.Class;
import pms.vo.Question;

public interface ClassDao {
  List<Class> selectList();
  int insert(Class clazz);
  Class selectOne(int no);
  int update(Class clazz);
  int delete(int no);
  int countAll();
  List<Class> mylist(int mno);
  List<Question> search(Map<String,Object> paramMap);
}