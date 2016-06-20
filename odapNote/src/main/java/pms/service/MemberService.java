package pms.service;

import java.util.List;

import pms.vo.Member;

public interface MemberService {
  void add(Member member);
  void delete(int no);
  Member retrieve(int no);
  List<Member> list();
  void change(Member member);
}
