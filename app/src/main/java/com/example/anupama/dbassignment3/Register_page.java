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

public class Register_page extends AppCompatActivity {
    Button Submit;
    EditText first_name;
    EditText last_name;
    EditText age;
    EditText gender;
    EditText about_urself;
    databasehelper db;
    Button mlogin;
    StringBuilder sb = null;
    StringBuffer buffer=new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new databasehelper(this);
        setContentView(R.layout.activity_register_page);
        first_name = (EditText) findViewById(R.id.editfirstname);
        last_name = (EditText) findViewById(R.id.editlastname);
        age = (EditText) findViewById(R.id.editAge);
        gender = (EditText) findViewById(R.id.editGender);
        about_urself = (EditText) findViewById(R.id.editabouturself);
        Submit = (Button) findViewById(R.id.submit_button);
        mlogin = (Button) findViewById(R.id.blogin);
        mlogin.setEnabled(false);
        try {
            reg();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void reg() {
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstnameEditText = (EditText) findViewById(R.id.editfirstname);
                String fname = firstnameEditText.getText().toString();
                EditText lastname = (EditText) findViewById(R.id.editlastname);
                String lname=lastname.getText().toString();
                EditText username = (EditText) findViewById(R.id.editAge);
                String uname=username.getText().toString();
                EditText password = (EditText) findViewById(R.id.editGender);
                String password1=password.getText().toString();
                EditText confpass = (EditText) findViewById(R.id.editabouturself);
                String confpass1=confpass.getText().toString();
                if (fname.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (lname.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (uname.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a User Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (password1.matches("")) {
                        Toast.makeText(getApplicationContext(), "You did not enter a Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                else if (confpass1.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!password1.equals(confpass1))
                {
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password did not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                    Log.d("hi", "Clicked Submit Button");
                    internal_storage();//create internal storage file
                    cr_ex_st_pr_fl();//create external storage private file
                    write_external_storage();//create external storage public file
                    ext();//create external storage public file
                    boolean result = db.onInsert(first_name.getText().toString(), last_name.getText().toString(), age.getText().toString(), gender.getText().toString());
                    if (result == true) {
                        SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                        preferences.edit().putBoolean("value", false).commit();
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        mlogin.setEnabled(true);
                    } else {
                        Toast.makeText(getApplicationContext(), "Data not entered", Toast.LENGTH_SHORT).show();
                    }
                    display();


            }
        });
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kl = new Intent(getApplicationContext(), Login.class);
                startActivity(kl);
                finish();
            }
        });
    }

    public void internal_storage() {
        Log.d("hi", "saved");
        FileOutputStream outputStream;
        StringBuffer buffer = new StringBuffer();
        buffer.append("First Name :" + first_name.getText() + "\n");
        buffer.append("Last Name  :" + last_name.getText() + "\n");
        buffer.append("Username   :" + age.getText() + "\n");
        buffer.append("Password   :" + gender.getText());
        buffer.append("\n\n");
        try {
            outputStream = openFileOutput("user.txt", Context.MODE_PRIVATE);
            outputStream.write(buffer.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        show_message();
    }
    public void show_message() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("user.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Your Details");
        builder.setMessage(sb);
        builder.show();
    }

    public void write_external_storage() {              //create External Stirage Public file
        try {
            String state = Environment.getExternalStorageState();
            File rtr = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/anu.txt");
            if (!rtr.exists()) {
                rtr.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(rtr);
            fout.write(sb.toString().getBytes());
            fout.close();
            buffer.append("Public External File Created");
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Data not stored successfully !!", Toast.LENGTH_LONG).show();
        }
    }
    void ext()                    //create External Storage Public file
    {
        File fileExternalStorage = new File(Environment.getExternalStorageDirectory() + "/externalFile.txt" );
        try
        {
            if(!fileExternalStorage.exists())
            {
                fileExternalStorage.createNewFile();
            }
            Log.v("ExternalFileName" , String.valueOf(fileExternalStorage)) ;
            OutputStream os = new FileOutputStream(fileExternalStorage);
            os.write("hii.... Welcome to external storage".getBytes());
            os.close();
            buffer.append("Public External File Created \n");
        }
        catch (FileNotFoundException e)
        {
            Log.w("File Not Found", String.valueOf(e));
            Toast.makeText(getApplicationContext(), "Data not stored successfully !!", Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Log.w("IO Exception", String.valueOf(e));
            Toast.makeText(getApplicationContext(), "Data not stored successfully !!", Toast.LENGTH_LONG).show();
        }
    }
    void cr_ex_st_pr_fl() {             //create External Storage Private file
        File traceFile = new File(((Context)this).getExternalFilesDir(null), "TraceFile.txt");
        if (!traceFile.exists())
        {
            try {
                traceFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(traceFile, true));
                writer.write(sb.toString());
                writer.close();
                buffer.append("Private external file created");
             }
             catch(Exception e)
             {
                 e.printStackTrace();
                 Toast.makeText(getApplicationContext(), "Data not stored successfully !!", Toast.LENGTH_LONG).show();
             }
        }
    }
    public void display()
    {
        if(buffer!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Storage Details");
            builder.setMessage(buffer);
            builder.show();
            Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    public void check()
    {
        int flag=0;
        if(first_name.getText().equals(""))
        {
            flag=1;
            Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_SHORT);
            first_name.requestFocus();
        }
        else if(last_name.getText().equals(""))
        {
            flag=1;
            Toast.makeText(getApplicationContext(),"Enter Last Name",Toast.LENGTH_SHORT);
        }
        else if(age.getText().equals(""))
        {
            flag=1;
            Toast.makeText(getApplicationContext(),"Enter Username",Toast.LENGTH_SHORT);
        }
        else if(gender.getText().equals(""))
        {
            flag=1;
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT);
        }
        else if(about_urself.getText().equals(""))
        {
            flag=1;
            Toast.makeText(getApplicationContext(),"Enter Confirm Password",Toast.LENGTH_SHORT);
        }
        else if(!gender.getText().equals(about_urself.getText()))
        {
            flag=1;
            Toast.makeText(getApplicationContext(),"Password Mismatch",Toast.LENGTH_SHORT);
        }
    }
}
