package pms.service;

import java.util.List;

import pms.vo.Class;

public interface ClassService {
  void add(Class clazz);
  void delete(int no);
  Class retrieve(int no);
  List<Class> list();
  void change(Class clazz);
  List<Class> myclasslist(int mno);
}