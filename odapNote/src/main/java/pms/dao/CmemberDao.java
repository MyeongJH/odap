package pms.dao;

import java.util.List;
import java.util.Map;

import pms.vo.Cmember;
import pms.vo.JoinCmember;
import pms.vo.Member;

public interface CmemberDao {
  List<Member> selectList();
  Member selectOne(Map<String,Object> paramMap);
  int insert(Cmember cmember);
  int update(Cmember cmember);
  int delete(int no);
  int isCmember(Map<String,Object> paramMap); 
  List<JoinCmember> selectList(int cno);
  int apply(Map<String,Object> paramMap);
  int delete(Map<String,Object> paramMap);
  int countAll(int cno);
}