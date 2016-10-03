package com.example.anupama.dbassignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {
    Button mregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mregister =(Button)findViewById(R.id.register_button);
        reg();
    }
    public void reg()
    {
        mregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("hi","Clicked Submit Button");
                Intent kl = new Intent(getApplicationContext(),Register_page.class);
                startActivity(kl);
                finish();
            }
        });
    }
}
