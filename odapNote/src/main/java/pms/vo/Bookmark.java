package pms.vo;

public class Bookmark {
 int bno;
 int mno;
 int qno;
 
public int getBno() {
  return bno;
}

public void setBno(int bno) {
  this.bno = bno;
}

public int getMno() {
  return mno;
}

public void setMno(int mno) {
  this.mno = mno;
}

public int getQno() {
  return qno;
}

public void setQno(int qno) {
  this.qno = qno;
}

@Override
public String toString() {
  return "Bookmark [bno=" + bno + ", mno=" + mno + ", qno=" + qno + "]";
  }

}
