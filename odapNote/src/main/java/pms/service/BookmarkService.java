package pms.service;

import java.util.List;

import pms.vo.Bookmark;
import pms.vo.Question;

public interface BookmarkService {
  void add(Bookmark bookmark);
  void change(Bookmark bookmark);
  void delete(int no);
  List<Question> userBookmark(int no);
}
