package com.example.account.data;

import android.text.TextUtils;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class CeramicsInfo extends RealmObject implements Serializable{


    public CeramicsInfo(String id, String number, String date, String specification, String amount, String price, String total, String balance, String remark) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.specification = specification;
        this.amount = amount;
        this.price = price;
        this.total = total;
        this.balance = balance;
        this.remark = remark;
    }

    @PrimaryKey
    private String id;

    private String number;

    private String date;

    private String specification;

    private String amount;

    private String price;

    private String total;

    private String balance;

    private String remark;

    public CeramicsInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public boolean isEmpty() {
        return TextUtils.isEmpty(date) ||
                TextUtils.isEmpty(number) ||
                TextUtils.isEmpty(specification) ||
                TextUtils.isEmpty(amount) ||
                TextUtils.isEmpty(price);
    }

}
