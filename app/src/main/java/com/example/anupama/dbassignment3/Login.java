package com.example.anupama.dbassignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import android.content.Context;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button mlogin;
    databasehelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mlogin=(Button)findViewById(R.id.login_button);
        username=(EditText)findViewById(R.id.edit_username);
        password=(EditText)findViewById(R.id.edit_password);
        db=new databasehelper(this);
        login();
    }
    public void login()
    {
        mlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                EditText user=(EditText)findViewById(R.id.edit_username);
                String user1=user.getText().toString();
                EditText pass=(EditText)findViewById(R.id.edit_password);
                String pass1=pass.getText().toString();
                if (user1.matches("")) {
                        Toast.makeText(getApplicationContext(), "You did not enter a User Name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                if (pass1.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean b=db.search_string(username.getText().toString(),password.getText().toString());
                if(b==true) {
                    Intent kl = new Intent(getApplicationContext(), Profile.class);
                    kl.putExtra("username",username.getText().toString());
                    startActivity(kl);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Username or Password is Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
