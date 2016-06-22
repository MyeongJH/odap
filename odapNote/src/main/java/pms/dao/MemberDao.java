package pms.dao;

import java.util.List;
import java.util.Map;

import pms.vo.Member;

public interface MemberDao {
  List<Member> selectList();
  Member selectOne(int no);
  int insert(Member member);
  int update(Member member);
  int delete(int no);
  int isMember(Map<String,Object> paramMap);
  int isMember2(Map<String,Object> paramMap);
  int countAll();
}