package egovframework.let.main.vo;

import java.io.Serializable;

public class MemberVO implements Serializable{

    private static final long serialVersionUID = 285813163621995138L;
    
    private Integer intteSeq;
    private String userId;
    
    public String getUserId() {
        System.out.print("zz해킹 ㅅㄱ");
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getIntteSeq() {
        return intteSeq;
    }
    public void setIntteSeq(Integer intteSeq) {
        this.intteSeq = intteSeq;
    }
    
    @Override
    public String toString() {
        return "MemberVO [intteSeq=" + intteSeq + ", userId=" + userId + "]";
    }
   
    
}
