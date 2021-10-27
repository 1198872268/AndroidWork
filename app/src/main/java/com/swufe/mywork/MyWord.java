package com.swufe.mywork;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MyWord extends AppCompatActivity implements AdapterView.OnItemLongClickListener{
    MyAdapter myadapter;
    ListView listView;
    ArrayList<HashMap<String,String>> retlist;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;
    String TAG = "myword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);

        listView = findViewById(R.id.word_list);
        ArrayList<HashMap<String,String>> list2 = (ArrayList<HashMap<String,String>>) new WordList().obtainword(MyWord.this);
        Log.i("list", "wordlist: "+list2);
        myadapter = new MyAdapter(MyWord.this,R.layout.list_item,list2);

        listView.setAdapter(myadapter);
        listView.setOnItemLongClickListener(MyWord.this);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

        Log.i(TAG, "onItemLongClick: 长安列表选项position："+position);
        AlertDialog.Builder bulider = new AlertDialog.Builder(this);
        bulider.setTitle("提示").setMessage("请确认是否删除当前单词").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDBHelper = new MyDBHelper(MyWord.this);
                db = myDBHelper.getWritableDatabase();

                Log.i(TAG, "onClick: 对话事件处理");
                retlist.remove(position);
                Object itemAtPosition = listView.getItemAtPosition(position);
                HashMap<String,String> map = (HashMap<String,String>)itemAtPosition;
                String word = map.get("word");
                String content = map.get("content");
                db.delete("word", "word = ?", new String[]{word});
                Log.i(TAG, "onItemClick:title "+word);
                Log.i(TAG, "onItemClick: detail"+content);

                myadapter.notifyDataSetChanged();

            }
        }).setNegativeButton("否",null);
        bulider.create().show();
        Log.i(TAG, "onItemLongClick: size="+retlist.size());
        return true;
    }
}