package com.example.administrator.fixframwork;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class Person {
    private String name;
    private String password;

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Person{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
