package pms.dao;

import java.util.List;
import java.util.Map;

import pms.vo.Bookmark;
import pms.vo.Question;

public interface BookmarkDao {
  int insert(Bookmark bookmark);
  int update(Bookmark bookmark);
  int delete(int no);
  List<Question> selectUserBookmark(int no);
  int isBookmark(Map<String,Object> paramMap); 
  int findBookmark(Map<String,Object> paramMap);
}