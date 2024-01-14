package com.example.sqlitelikedislike;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;


import java.io.File;

public class DatabaseHelper extends SQLiteOpenHelper {


    //private static final String DB_PATH="/data/user/0/com.example.sqlitelikedislike/files/db/";
    private static final String DB_PATH="/data/data/com.example.sqlitelikedislike/databases/";
    private static final String DB_NAME="sqlitelikedislike.db";
    private static final int DB_VERSION=1;
    private static final String TB_USER="images";

    private SQLiteDatabase myDb;
    private Context context;




//    public DatabaseHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int dbversion){
//        super(context, dbname, factory, dbversion);
//        this.context=context;
//    }
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
        if(checkDataBase()==true){
            myDb = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            myDb = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            //myDb = SQLiteDatabase.openOrCreateDatabase(DB_PATH+DB_NAME, null);
        }
        else {
            String createQuery = "CREATE TABLE " + TB_USER +
                    "(id INTEGER NOT NULL UNIQUE , " +
                    "likes INTEGER, " +
                    "dislikes	INTEGER, " +
                    "date_loaded	INTEGER," +
                    "image	BLOB NOT NULL, " +
                    "PRIMARY KEY(id));";

            //myDb.execSQL(createQuery);
            //myDb = SQLiteDatabase.create(createQuery);
//            myDb=SQLiteDatabase.create(null);
//            myDb.execSQL(createQuery);
//            myDb = SQliteDatabase.
        }



    }
    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    @Override
    public synchronized void close(){
        if(myDb!=null){
            myDb.close();

        }
        super.close();
    }

    public boolean checkDataBase(){
        SQLiteDatabase tempDb = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            //Toast.makeText(context, " way to the db is good ", Toast.LENGTH_SHORT).show();

        }
        catch(SQLiteException e) {
            System.out.println("tle99 - check, error while opening a db" + e.getMessage());
            //Toast.makeText(context, " bad ", Toast.LENGTH_SHORT).show();
        }
        if (tempDb != null)
            tempDb.close();
        return tempDb != null ? true : false;
    }

    public Cursor getAllData(){
        Cursor res = myDb.rawQuery("SELECT * FROM " + TB_USER, null);
        return res;
    }

}
