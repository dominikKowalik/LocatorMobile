package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by dominik on 2016-10-27.
 */

public class Helpers{
    public static void showText(String text, Context context){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void suggestToTurnOnGpsIfOff(Context context) {
        LocationManager service = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            Helpers.showText("Włącz GPS", context);
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
    }
}
