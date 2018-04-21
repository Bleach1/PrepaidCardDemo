package com.example.administrator.prepaidcarddemo.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReCordBean extends RealmObject {

    private String money;
    private String num;
    private String balance;
    @PrimaryKey
    private String time;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
