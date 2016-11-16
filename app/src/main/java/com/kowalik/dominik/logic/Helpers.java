package com.kowalik.dominik.logic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kowalik.dominik.model.User;

import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by dominik on 2016-10-27.
 */

public class Helpers<T>{
    public static void showText(String text, Context context){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public void saveList(String key, Context context, List<T> data){
        SharedPreferences appSharedPrefs =  PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    public List<T> getList(String key, Type type,Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = appSharedPrefs.getString(key, "");
        List<T> data = gson.fromJson(json, type);
        return data;
    }

    public static String getStringFromPreference(String key, Context context){
        SharedPreferences settings = context.getSharedPreferences("com.kowalik.dominik", Context.MODE_PRIVATE);
        return settings.getString(key,"");
    }

    public static boolean isGpsOn(Context context){
        LocationManager service = (LocationManager) context.getSystemService(LOCATION_SERVICE);
     return service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static void suggestToTurnOnGpsIfOff(Context context) {
        if (!isGpsOn(context)) {
            Helpers.showText("Włącz GPS", context);
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
    }
}
