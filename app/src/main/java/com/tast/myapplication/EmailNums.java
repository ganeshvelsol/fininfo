package com.tast.myapplication;

public class EmailNums {
    public String emsilId;
    public String number;

    public EmailNums(String emsilId, String number) {
        this.emsilId = emsilId;
        this.number = number;
    }

    public String getEmsilId() {
        return emsilId;
    }

    public void setEmsilId(String emsilId) {
        this.emsilId = emsilId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
