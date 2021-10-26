package com.swufe.mywork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "mydb.db";
    public static final String TB_NAME = "word";

    public MyDBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TB_NAME+"( word TEXT,content TEXT,know TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

    }

}
