package com.kowalik.dominik.model;

/**
 * Created by dominik on 2016-10-28.
 */

public class FriendsName {

    private long id;
    String name;

    public FriendsName(String name) {
        this.name = name;
    }
    public FriendsName() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FriendsName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}