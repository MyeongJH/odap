package pms.vo;

public class ClassList {
  ClassWithName classwithName;
  int questioncount;
  int membercount;
  
  @Override
  public String toString() {
    return "ClassList [classwithName=" + classwithName + ", questioncount=" + questioncount + ", membercount="
        + membercount + "]";
  }
  public ClassWithName getClasswithName() {
    return classwithName;
  }
  public void setClasswithName(ClassWithName classwithName) {
    this.classwithName = classwithName;
  }
  public int getQuestioncount() {
    return questioncount;
  }
  public void setQuestioncount(int questioncount) {
    this.questioncount = questioncount;
  }
  public int getMembercount() {
    return membercount;
  }
  public void setMembercount(int membercount) {
    this.membercount = membercount;
  }
  
  
  
  
}