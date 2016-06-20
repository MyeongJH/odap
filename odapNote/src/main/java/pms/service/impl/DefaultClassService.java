package pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.ClassDao;
import pms.service.ClassService;
import pms.vo.Class;

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

}
