package pms.vo;

import java.sql.Date;

public class Cmember {
    int mno;
    int cno;
    String cmitr;
    Date cmad;
    boolean cmst;
    
    @Override
    public String toString() {
      return "Cmember [mno=" + mno + ", cno=" + cno + ", cmitr=" + cmitr + ", cmad=" + cmad + ", cmst=" + cmst + "]";
    }
    public int getMno() {
      return mno;
    }
    public void setMno(int mno) {
      this.mno = mno;
    }
    public int getCno() {
      return cno;
    }
    public void setCno(int cno) {
      this.cno = cno;
    }
    public String getCmitr() {
      return cmitr;
    }
    public void setCmitr(String cmitr) {
      this.cmitr = cmitr;
    }
    public Date getCmad() {
      return cmad;
    }
    public void setCmad(Date cmad) {
      this.cmad = cmad;
    }
    public boolean isCmst() {
      return cmst;
    }
    public void setCmst(boolean cmst) {
      this.cmst = cmst;
    }
}   
   
   

