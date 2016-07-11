package pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.CmemberDao;
import pms.service.CmemberService;
import pms.vo.Cmember;
import pms.vo.JoinCmember;
@Service
public class DefaultCmemberService implements CmemberService {
  @Autowired CmemberDao cmemberDao;
  
  public void add(Cmember cmamber) {
    cmemberDao.insert(cmamber);
  }

  @Override
  public boolean isCmember(Map<String,Object> paramMap) {
    if(cmemberDao.isCmember(paramMap)>0) {
      return true;
    }
      return false;
  }
  
  public List<JoinCmember> list(int cno) {
    return cmemberDao.selectList(cno);
  }
  public void delete(Map<String,Object> paramMap) {
    cmemberDao.delete(paramMap);
  }
  
  public void apply(Map<String,Object> paramMap) {
    cmemberDao.apply(paramMap); 
  }


}
