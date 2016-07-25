package pms.dao;

import java.util.List;
import java.util.Map;

import pms.vo.Class;
import pms.vo.ClassWithName;

public interface ClassDao {
  List<Class> selectList();
  int insert(Class clazz);
  Class selectOne(int no);
  int update(Class clazz);
  int delete(int no);
  int countAll();
  List<ClassWithName> mylist(int mno);
  List<ClassWithName> search(Map<String,Object> paramMap);
  List<ClassWithName> myClass(int mno);
}