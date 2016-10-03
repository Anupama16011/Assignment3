package com.example.anupama.dbassignment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;

public class HomeClass extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = null;
        SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        if (preferences.getBoolean("value", true)) {   //the app is being launched for first time
            intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        }
        else
        {            //app is not launched for the first time
            Log.d("Comments", "First time");
            intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
            Log.d("ko","nooo");
        }
    }
}
