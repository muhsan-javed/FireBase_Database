package com.muhsantech.firebasetesting;

public class user {

    public String name;
    public String age;


    public user(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
