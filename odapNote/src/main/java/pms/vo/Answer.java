package pms.vo;

public class Answer {
  int ano;
  String acon;
  int qno;
  
  public int getAno() {
    return ano;
  }
  public void setAno(int ano) {
    this.ano = ano;
  }
  public String getAcon() {
    return acon;
  }
  public void setAcon(String acon) {
    this.acon = acon;
  }
  public int getQno() {
    return qno;
  }
  public void setQno(int qno) {
    this.qno = qno;
  }
  
  @Override
  public String toString() {
    return "Answer [ano=" + ano + ", acon=" + acon + ", qno=" + qno + "]";
  }  
}
