package pms.service;

import java.util.List;
import java.util.Map;

import pms.vo.Cmember;
import pms.vo.JoinCmember;

public interface CmemberService {
  void add(Cmember cmember);
  boolean isCmember(Map<String,Object> paramMap);
  void delete(Map<String,Object> paramMap);
  void apply(Map<String,Object> paramMap);
  List<JoinCmember> list(int cno); 
}
