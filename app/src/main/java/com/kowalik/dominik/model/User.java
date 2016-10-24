package com.kowalik.dominik.model;

import java.util.List;

/**
 * Created by dominik on 2016-10-22.
 */

public class User implements UserInterface {
    private String emailAdress;
    private String name;
    private String lastName;

    public User(String emailAdress, String lastName, String name,LocationInfo locationInfo, List<User> friends ) {
        this.emailAdress = emailAdress;
        this.locationInfo = locationInfo;
        this.friends = friends;
        this.lastName = lastName;
        this.name = name;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    private List<User> friends;
   // private boolean exists;

//    public boolean isExists() {
//        return exists;
//    }
//
//    public void setExists(boolean exists) {
//        this.exists = exists;
//    }

    private LocationInfo locationInfo;

    private long id;

    public  User(){
    }


    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfoInterface locationInfo) {

    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" +
                "emailAdress='" + emailAdress + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friends=" + friends +
                ", locationInfo=" + locationInfo +
                ", id=" + id +
                '}';
    }
}
