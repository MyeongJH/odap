package pms.vo;

import java.sql.Date;

public class ClassWithName {
  int cno;
  String mnm;
  String cnm;
  Date ccd;
  String cdes;
  String csub;
        
  @Override
  public String toString() {
    return "ClassWithName [cno=" + cno + ", mnm=" + mnm + ", cnm=" + cnm + ", ccd=" + ccd + ", cdes=" + cdes + ", csub="
        + csub + "]";
  }
  
  public int getCno() {
    return cno;
  }
  public void setCno(int cno) {
    this.cno = cno;
  }
  public String getMnm() {
    return mnm;
  }
  public void setMnm(String mnm) {
    this.mnm = mnm;
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