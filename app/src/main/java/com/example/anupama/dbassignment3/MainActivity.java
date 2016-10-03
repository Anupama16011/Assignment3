package com.example.anupama.dbassignment3;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.*;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity  {
    databasehelper db;
    EditText fname;
    EditText lname;
    EditText marks;
    EditText roll;
    Button madd;
    Button vdata;
    Button update_button;
    Button delete;
    private static final String sTAG="QuizFinal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new databasehelper(this);
        fname=(EditText)findViewById(R.id.editTextname);
        lname=(EditText)findViewById(R.id.editTextlname);
        marks=(EditText)findViewById(R.id.editTextmarks);
        madd=(Button)findViewById(R.id.button);
        vdata=(Button) findViewById(R.id.button2);
        update_button=(Button)findViewById(R.id.button3);
        roll=(EditText)findViewById(R.id.editroll) ;
        delete=(Button)findViewById(R.id.button4);
      //  add_data();
      //  view_data();
      //  update_data();
      //  delete();
    }
    public void delete()
    {
        Log.d(sTAG,"Inside delete");
        delete.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Log.d(sTAG,"Inside delete");
                        boolean result= db.delete_data(roll.getText().toString());
                        if(result==true)
                        {
                            Toast.makeText(getApplicationContext(),"data deleted",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"data not entered",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
    public void add_data()
    {
        Log.d(sTAG,"Inside adddata");
        madd.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Log.d(sTAG,"Inside onclick");
                     /*     boolean result= db.onInsert(fname.getText().toString(),lname.getText().toString(),marks.getText().toString());
                          if(result==true)
                          {
                              Toast.makeText(getApplicationContext(),"data entered",Toast.LENGTH_SHORT).show();
                          }
                        else
                          {
                              Toast.makeText(getApplicationContext(),"data not entered",Toast.LENGTH_SHORT).show();
                          }*/
                    }
                }
        );
    }
    public void view_data()
    {
       /* Log.d(sTAG,"Inside View Data");
      vdata.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(sTAG,"Clicked Hint Button");
                Intent kl = new Intent(getApplicationContext(),ViewData.class);
                Log.d(sTAG,"clicked");
                startActivity(kl);
            }
        });*/
      vdata.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Log.d(sTAG,"Inside onclick view data");
                        Cursor rs=db.view_data();
                        if(rs.getCount()==0)
                        {
                            Toast.makeText(getApplicationContext(),"data not found",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"data found",Toast.LENGTH_SHORT).show();
                        }
                        StringBuffer buffer=new StringBuffer();
                        while(rs.moveToNext())
                        {
                            buffer.append("Roll       :"+rs.getInt(0)+"\n");
                            buffer.append("First Name :"+rs.getString(1)+"\n");
                            buffer.append("Last Name  :"+rs.getString(2)+"\n");
                            buffer.append("Marks      :"+rs.getString(3)+"\n");
                            buffer.append("\n\n");
                        }
                       // Toast.makeText(getApplicationContext(),buffer.toString(),Toast.LENGTH_SHORT).show();
                       show_message("hi",buffer.toString());
                    }
                }
        );
    }
    /*public void update_data()
    {
        update_button.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Log.d(sTAG,"Inside update");
                        boolean b=db.update_data(fname.getText().toString(),lname.getText().toString(),marks.getText().toString(),roll.getText().toString());
                       // boolean b=db.update_data("amit","balotra","1234","2");
                        if(b==true)
                        {
                            Toast.makeText(getApplicationContext(),"Successfully done",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }*/
    public void show_message(String title,String Message)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
