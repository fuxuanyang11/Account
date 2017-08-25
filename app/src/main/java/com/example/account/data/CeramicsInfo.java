package com.example.account.data;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class CeramicsInfo extends RealmObject {

//    @PrimaryKey
//    private int id;

    private String number;

    private String date;

    private String specification;

    private int amount;

    private float price;

    private float total;

    private String balance;

    private String remark;

//    public CeramicsInfo(Builder builder) {
////        this.id = builder.id;
//        this.number = builder.number;
//        this.date = builder.date;
//        this.specification = builder.specification;
//        this.amount = builder.amount;
//        this.price = builder.price;
//        this.total = builder.total;
//        this.balance = builder.balance;
//        this.remark = builder.remark;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
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

}
