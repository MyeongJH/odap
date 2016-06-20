package pms.vo;

public class Member {
  int mno;
  String mid;
  String mpw;
  String mnm;
  String mtel;
  String madr;
  String mjob;
  String mpg;
  String mpic;
  String mcl;
  @Override
  public String toString() {
    return "Member [mno=" + mno + ", mid=" + mid + ", mpw=" + mpw + ", mnm=" + mnm + ", mtel=" + mtel + ", madr=" + madr
        + ", mjob=" + mjob + ", mpg=" + mpg + ", mpic=" + mpic + ", mcl=" + mcl + "]";
  }
  public int getMno() {
    return mno;
  }
  public void setMno(int mno) {
    this.mno = mno;
  }
  public String getMid() {
    return mid;
  }
  public void setMid(String mid) {
    this.mid = mid;
  }
  public String getMpw() {
    return mpw;
  }
  public void setMpw(String mpw) {
    this.mpw = mpw;
  }
  public String getMnm() {
    return mnm;
  }
  public void setMnm(String mnm) {
    this.mnm = mnm;
  }
  public String getMtel() {
    return mtel;
  }
  public void setMtel(String mtel) {
    this.mtel = mtel;
  }
  public String getMadr() {
    return madr;
  }
  public void setMadr(String madr) {
    this.madr = madr;
  }
  public String getMjob() {
    return mjob;
  }
  public void setMjob(String mjob) {
    this.mjob = mjob;
  }
  public String getMpg() {
    return mpg;
  }
  public void setMpg(String mpg) {
    this.mpg = mpg;
  }
  public String getMpic() {
    return mpic;
  }
  public void setMpic(String mpic) {
    this.mpic = mpic;
  }
  public String getMcl() {
    return mcl;
  }
  public void setMcl(String mcl) {
    this.mcl = mcl;
  } 
  
  
}
