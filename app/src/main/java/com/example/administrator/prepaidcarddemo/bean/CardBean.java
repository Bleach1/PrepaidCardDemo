package com.example.administrator.prepaidcarddemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardBean extends RealmObject{

    private String balance;
    private String num;
    private int type;
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String no) {
        this.balance = no;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
