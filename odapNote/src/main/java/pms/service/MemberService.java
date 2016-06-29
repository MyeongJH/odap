package pms.service;

import java.util.List;

import pms.vo.Member;

public interface MemberService {
  void add(Member member);
  void delete(int no);
  Member retrieve(int no);
  Member retrieveById(String id);
  void change(Member member);
  List<Member> list(); 
  boolean exist(String id, String password);
  boolean existId(String id);
}
