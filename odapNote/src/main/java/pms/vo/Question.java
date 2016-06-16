package pms.vo;

import java.sql.Date;

public class Question {
  int qno;
  int cno;
  String qtit;
  String qcnt;
  Date qcd;
  boolean qst;
  String qpic;
  int mno;  
  
  public int getQno() {
    return qno;
  }
  public void setQno(int qno) {
    this.qno = qno;
  }
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public String getQtit() {
    return qtit;
  }
  public void setQtit(String qtit) {
    this.qtit = qtit;
  }
  public String getQcnt() {
    return qcnt;
  }
  public void setQcnt(String qcnt) {
    this.qcnt = qcnt;
  }
  public Date getQcd() {
    return qcd;
  }
  public void setQcd(Date qcd) {
    this.qcd = qcd;
  }
  public boolean isQst() {
    return qst;
  }
  public void setQst(boolean qst) {
    this.qst = qst;
  }
  public String getQpic() {
    return qpic;
  }
  public void setQpic(String qpic) {
    this.qpic = qpic;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }  
}
