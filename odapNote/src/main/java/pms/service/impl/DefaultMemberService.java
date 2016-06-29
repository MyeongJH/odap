package pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.MemberDao;
import pms.service.MemberService;
import pms.vo.Member;
@Service
public class DefaultMemberService implements MemberService {
  @Autowired MemberDao memberDao;
  
  public void add(Member mamber) {
    memberDao.insert(mamber);
  }

  public void delete(int no) {
    memberDao.delete(no);
  }

  public Member retrieve(int no) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    
    return memberDao.selectOne(paramMap);
  }
  
  public Member retrieveById(String id) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);   
    return memberDao.selectOne(paramMap);
  }

  public List<Member> list() {
    return memberDao.selectList();
  }

  public void change(Member mamber) {
    memberDao.update(mamber);
  }
  
  public boolean exist(String id, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);
    
    if (memberDao.isMember(paramMap) > 0) {
      return true;
    }
    
    return false;
  }
  
  public boolean existId(String id) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);       
    if (memberDao.isMember2(paramMap) > 0) {
      return false;
    }    
    return true;
  }
}
