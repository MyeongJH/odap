package pms.dao;

import java.util.List;

import pms.vo.Member;

public interface MemberDao {
  List<Member> selectList();
  int insert(Member member);
  Member selectOne(int no);
  int update(Member member);
  int delete(int no);
  int countAll();
}