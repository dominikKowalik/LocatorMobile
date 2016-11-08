package com.kowalik.dominik.locatormobile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainScreenActivity extends AppCompatActivity {

    TextView grettingTextView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        grettingTextView = (TextView) findViewById(R.id.grettingTextView);
        context = this;
        setGrettingText();
    }


   public void setGrettingText(){
        grettingTextView.setText("Witaj," + Helpers.getStringFromPreference("username",this));
   }

}
