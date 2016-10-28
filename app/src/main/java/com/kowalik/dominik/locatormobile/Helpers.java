package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dominik on 2016-10-27.
 */

public class Helpers{
    public static void showText(String text, Context context){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
