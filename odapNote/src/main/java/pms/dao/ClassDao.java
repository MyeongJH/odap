package pms.dao;

import java.util.List;
import pms.vo.Class;

public interface ClassDao {
  List<Class> selectList();
  int insert(Class clazz);
  Class selectOne(int no);
  int update(Class clazz);
  int delete(int no);
  int countAll();
  List<Class> mylist(int mno);
}