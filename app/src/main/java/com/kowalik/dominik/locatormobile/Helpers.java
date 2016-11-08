package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;

import static android.R.attr.enabled;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by dominik on 2016-10-27.
 */

public class Helpers{
    public static void showText(String text, Context context){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
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
