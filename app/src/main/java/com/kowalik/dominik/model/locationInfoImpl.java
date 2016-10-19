package com.kowalik.dominik.model;

/**
 * Created by dominik on 2016-10-19.
 */

/**
 * Basic implementation of LocationInfo that helps exchange data with rest api
 */

public class LocationInfoImpl implements LocationInfo {
    public String latitude;

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
