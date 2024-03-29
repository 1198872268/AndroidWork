package com.swufe.mywork;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class WordList {
    //获取生词列表

    ArrayList<HashMap<String,String>> retlist;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;
    String TAG = "wordlist";
    public ArrayList<HashMap<String,String>> obtainword(Context context){
        myDBHelper = new MyDBHelper(context);
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        retlist = new ArrayList<HashMap<String,String>>();
        Cursor cursor = db.rawQuery("SELECT * FROM word WHERE know = ?",
                new String[]{"1"});
        if(cursor.getCount()==0){
            HashMap<String,String> add = new HashMap<String,String>();
            add.put("word","null");
            add.put("content","您还没有单词，快去添加吧");
            retlist.add(add);
        }
        while (cursor.moveToNext()) {
            String word = cursor.getString(0);
            String content =cursor.getString(1);
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("word",word);
            map.put("content",content);
            retlist.add(map);
//            Log.i(TAG, "myword: "+word+":"+content);

        }
//        Message msg = handler.obtainMessage(2);
//        msg.obj = retlist;
//        handler.sendMessage(msg);
        return retlist;
    }
}
