package pms.service;

import java.util.List;
import java.util.Map;

import pms.vo.Bookmark;
import pms.vo.Question;

public interface BookmarkService {
  void add(Bookmark bookmark);
  void change(Bookmark bookmark);
  void delete(int no);
  List<Question> userBookmark(int no);
  boolean isBookmark(Map<String,Object> paramMap);
  int findBookmark(Map<String,Object> paramMap);
}
