package com.example.anupama.dbassignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

public class databasehelper extends SQLiteOpenHelper {
    private static final String sTAG="QuizFinal";
    public static final String database_name = "Final.db" ;
    public static final String table_name = "Registration1_table" ;
    public static final String id="ID";
    public static final String first_name = "First_name" ;
    public static final String last_name = "Last_name" ;
    public static final String username = "Username" ;
    public static final String password = "Password" ;
    public databasehelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(sTAG,"hiii");
              db.execSQL("create table "+ table_name +"("+first_name+" TEXT ,"+last_name+" TEXT,"+username+" TEXT PRIMARY KEY ,"+password+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
             db.execSQL("DROP TABLE IF EXISTS"+table_name);
             onCreate(db);
    }

    public boolean onInsert(String f,String l,String u,String p)
    {
        Log.d(sTAG,"Inside add data");
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(first_name,f);
        cv.put(last_name,l);
        cv.put(username,u);
        cv.put(password,p);
        long rs=db.insert(table_name,null,cv);
        if(rs==-1)
            return false;
        else
            return true;
    }
    public Cursor view_data()
    {
        Log.d(sTAG,"Inside view data of dbhelper");
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+table_name,null);
        return res;
    }
    public boolean update_data(String u_name,String l_name,String f_name,String p)
    {
        Log.d(sTAG,"Inside update data");
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("Anupama","sorry1");
        ContentValues cv=new ContentValues();
        cv.put(first_name,f_name);
        cv.put(last_name,l_name);
        Log.d("Anupama","sorry2");
        cv.put(password,p);
        db.update(table_name,cv,"Username ="+username,null);
        Log.d("Anupama","sorry");
        return true;
    }
    public boolean delete_data(String r)
    {
        Log.d(sTAG,"Inside delete data");
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("Delete from "+ table_name +" where Username='"+r+"'");
                //db.delete(table_name,"Username ="+r,null);
        return true;
    }
    public boolean search_string(String u,String p)
    {
        int flag=0;
        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from "+table_name+"";
        Cursor res=db.rawQuery(query,null);
        while(res.moveToNext())
        {
            flag=0;
            if(u.equals(res.getString(2)) && p.equals(res.getString(3)))
            {
                flag=1;
                break;
            }
        }
        if(flag==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Cursor search(String username)
    {

        SQLiteDatabase db=this.getReadableDatabase();
        String query="Select * from "+table_name+"";
        Cursor res=db.rawQuery(query,null);
        while(res.moveToNext())
        {
            Log.d("hi","inside database");
            if(username.equals(res.getString(2)))
            {
                return res;

            }
        }
        return null;

    }
}
