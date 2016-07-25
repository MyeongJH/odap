package pms.service;

import java.util.List;

import pms.vo.Class;
import pms.vo.ClassWithName;

public interface ClassService {
  void add(Class clazz);
  void delete(int no);
  Class retrieve(int no);
  List<Class> list();
  void change(Class clazz);
  List<ClassWithName> myclasslist(int mno);
  List<ClassWithName> search(String key);
  List<ClassWithName> myClass(int mno);
}