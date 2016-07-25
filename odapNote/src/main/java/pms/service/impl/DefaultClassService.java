package pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.ClassDao;
import pms.service.ClassService;
import pms.vo.Class;
import pms.vo.ClassWithName;

@Service
public class DefaultClassService implements ClassService {
  @Autowired ClassDao classDao;

  public void add(Class clazz) {
    classDao.insert(clazz);
  }

  public void delete(int no) {
    classDao.delete(no);
  }

  public Class retrieve(int no) {
    return classDao.selectOne(no);
  }

  public List<Class> list() {
    return classDao.selectList();
  }

  public void change(Class clazz) {
    classDao.update(clazz);
  }
  
  public List<ClassWithName> myclasslist(int mno) {
    return classDao.mylist(mno);
  }

  @Override
  public List<ClassWithName> search(String key) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("key", key);
    return classDao.search(paramMap);
  }

  @Override
  public List<ClassWithName> myClass(int mno) {
      return classDao.myClass(mno);
  };
  
}
