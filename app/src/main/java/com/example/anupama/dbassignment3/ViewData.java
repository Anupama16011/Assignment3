package com.example.anupama.dbassignment3;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ViewData extends AppCompatActivity {
    private static final String sTAG = "QuizFinal";
    TextView fnamedisp;
    TextView lnamedisp;
    TextView marksdisp;
    TextView rolldisp;
    databasehelper db;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView fname = (TextView) findViewById(R.id.fnamedisplay);
        TextView lastname = (TextView) findViewById(R.id.lnamedisplay);
        TextView marks = (TextView) findViewById(R.id.marksdisplay);
        TextView roll = (TextView) findViewById(R.id.roll_display);
        Log.d(sTAG, "Inside oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        db = new databasehelper(this);
         view_data();

    }

    public void view_data() {
        Log.d(sTAG, "Inside onclick view data");
        Cursor rs = db.view_data();
        Log.d(sTAG, "Returned Back");
        if (rs.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "data not found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "data found", Toast.LENGTH_SHORT).show();
        }
        StringBuffer buffer = new StringBuffer();
        while (rs.moveToNext())
                {
                    buffer.append("id:" + rs.getString(1));
                    buffer.append("id:" + rs.getString(2));
                    buffer.append("id:" + rs.getString(3));
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT).show();
                   // buffer.setLength(0);
                }
        show_message("hi",buffer.toString());
    }

    public void show_message(String title,String Message)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
