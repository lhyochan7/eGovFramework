package egovframework.let.main.vo;

import java.io.Serializable;

public class IntteVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3180487381066004336L;
    
    private Integer intteSeq;
    
    private Integer clsSeq;

    public Integer getIntteSeq() {
        return intteSeq;
    }

    public void setIntteSeq(Integer intteSeq) {
        this.intteSeq = intteSeq;
    }

    public Integer getClsSeq() {
        return clsSeq;
    }

    public void setClsSeq(Integer clsSeq) {
        this.clsSeq = clsSeq;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}