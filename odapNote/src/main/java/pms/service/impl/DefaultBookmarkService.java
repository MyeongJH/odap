package pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pms.dao.BookmarkDao;
import pms.service.BookmarkService;
import pms.vo.Bookmark;
import pms.vo.Question;

@Service
public class DefaultBookmarkService implements BookmarkService {
  @Autowired BookmarkDao bookmarkDao;

  public void add(Bookmark bookmark) {
    bookmarkDao.insert(bookmark);
  }

  public void delete(int no) {
    bookmarkDao.delete(no);
  }

  public void change(Bookmark bookmark) {
    bookmarkDao.update(bookmark);
  }
  
  public List<Question> userBookmark(int no) {
    return bookmarkDao.selectUserBookmark(no);
  }

  public boolean isBookmark(Map<String, Object> paramMap) {
    if(bookmarkDao.isBookmark(paramMap)>0) {
      return true;
    } else {
    return false;
    }
  }

  public int findBookmark(Map<String, Object> paramMap) {
    return bookmarkDao.findBookmark(paramMap);
  }

}
