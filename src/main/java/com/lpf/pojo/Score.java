package com.lpf.pojo;

public class Score {
    private int sNo;
    private String sName;
    private int couNum;
    private String couName;
    private String scores;

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getCouNum() {
        return couNum;
    }

    public void setCouNum(int couNum) {
        this.couNum = couNum;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }
}
