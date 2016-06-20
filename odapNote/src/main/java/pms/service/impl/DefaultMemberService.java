package pms.service.impl;

import java.util.List;

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
    return memberDao.selectOne(no);
  }

  public List<Member> list() {
    return memberDao.selectList();
  }

  public void change(Member mamber) {
    memberDao.update(mamber);
  }
  


}
