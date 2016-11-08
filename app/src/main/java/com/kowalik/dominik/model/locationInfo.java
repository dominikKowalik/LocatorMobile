package com.kowalik.dominik.model;

/**
 * Created by dominik on 2016-10-19.
 */

import android.location.Location;

/**
 * Basic implementation of LocationInfo that helps exchange data with rest api
 */

public class LocationInfo {
    double latitude;
    double longitude;

    public LocationInfo(Location location){
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public LocationInfo(){}

    @Override
    public String toString() {
        return "LocationInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
