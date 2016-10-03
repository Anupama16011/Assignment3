package com.example.anupama.dbassignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.io.BufferedReader;

public class Profile extends AppCompatActivity {

    TextView user;
    EditText fname;
    EditText lname;
    EditText password;
    databasehelper db;
    Button update;
    Button delete;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db=new databasehelper(this);
        String value= getIntent().getStringExtra("username");
        Cursor rs=db.search(value);
        fname=(EditText)findViewById(R.id.editfirstname);
        lname=(EditText)findViewById(R.id.editlastname);
        password=(EditText)findViewById(R.id.editpassword);
        user=(TextView)findViewById(R.id.editusername);
        user.setText(rs.getString(2));
        fname.setText(rs.getString(0));
        lname.setText(rs.getString(1));
        password.setText(rs.getString(3));
        update=(Button)findViewById(R.id.update_data);
        delete=(Button)findViewById(R.id.bdel_accnt);
        logout=(Button)findViewById(R.id.b_logout);
        update_b();
        delete_b();
        StringBuffer buffer=new StringBuffer();
        logout_f();
    }
    public void update_b()
    {
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               if(db.update_data(user.getText().toString(),fname.getText().toString(),lname.getText().toString(),password.getText().toString()))
               {
                   display();
               }
                else
                {
                    Toast.makeText(getApplicationContext(),"Username or Password is Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void display()
    {
            Cursor c=db.search(user.getText().toString());
            StringBuffer buffer=new StringBuffer();
            buffer.append("Username      :"+c.getString(2)+"\n");
            buffer.append("Last Name :"+c.getString(0)+"\n");
            buffer.append("First Name  :"+c.getString(1)+"\n");
            buffer.append("Password    :"+c.getString(3)+"\n");
            buffer.append("\n\n");

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Updated Details");
        builder.setMessage(buffer.toString());
        builder.show();
    }
    public void logout_f()
    {
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent kl=new Intent(getApplicationContext(),Login.class);
                startActivity(kl);
                finish();
            }
        });
    }
    public void delete_b()
    {
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(db.delete_data(user.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"data deleted successfully",Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    preferences.edit().putBoolean("value", true).commit();
                    Intent kl=new Intent(getApplicationContext(), HomeClass.class);
                    startActivity(kl);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Can't be deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
