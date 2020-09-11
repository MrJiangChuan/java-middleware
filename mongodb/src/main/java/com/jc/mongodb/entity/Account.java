package com.jc.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Account implements Serializable {
    private String id;
    private String name;
    private Float money;

    public Account() {
    }

    public Account(String id, String name, Float money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
