package pms.vo;

import java.sql.Date;

public class Class {
  int cno;
  int mno;
  String cnm;
  Date ccd;
  String cdes;
  String csub;
  int questionNo;
  int cmemberNo;
  String teacherName;
  
  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public void setQuestionNo(int questionNo) {
    this.questionNo = questionNo;
  }

  public void setCmemberNo(int cmemberNo) {
    this.cmemberNo = cmemberNo;
  }

  @Override
  public String toString() {
    return "Class [cno=" + cno + ", mno=" + mno + ", cnm=" + cnm + ", ccd=" + ccd + ", cdes=" + cdes + ", csub=" + csub
        + "]";
  }
  
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public String getCnm() {
    return cnm;
  }
  public void setCnm(String cnm) {
    this.cnm = cnm;
  }
  public Date getCcd() {
    return ccd;
  }
  public void setCcd(Date ccd) {
    this.ccd = ccd;
  }
  public String getCdes() {
    return cdes;
  }
  public void setCdes(String cdes) {
    this.cdes = cdes;
  }
  public String getCsub() {
    return csub;
  }
  public void setCsub(String csub) {
    this.csub = csub;
  }
  
  
}