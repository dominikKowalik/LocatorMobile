package com.kowalik.dominik.logic;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

import com.google.firebase.database.Logger;
import com.kowalik.dominik.model.LocationInfo;

import java.util.List;
import java.util.Objects;


import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by dominik on 2016-11-11.
 */

public class LocationInfoProvider implements LocationListener {

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Context context;

    private LocationInfo locationInfo;

    LocationManager mLocationManager;

    Location location;
    Location bestLocation = null;


    public LocationInfoProvider(Context context) {
        this.context = context;
        location = getLocation();
        locationInfo = new LocationInfo();
    }


    public LocationInfoProvider(){
        locationInfo = new LocationInfo();
    }


    public Location getLocation() {
        mLocationManager = (LocationManager)context.getApplicationContext().getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5 * 1000, 10, this);

                List<String> providers = mLocationManager.getProviders(true);

                for (String provider : providers) {
                    Location l = mLocationManager.getLastKnownLocation(provider);
                    if (l == null) {
                        continue;
                    }
                    if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                        // Found best last known location: %s", l);
                        bestLocation = l;
                    }
                }

        return bestLocation;
    }


    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public LocationInfo getLocationInfo(){
        location = getLocation();

        if(!Objects.equals(location,null)) {
            locationInfo.setLatitude(location.getLatitude());
            locationInfo.setLongitude(location.getLongitude());
        }
        return locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }
}
