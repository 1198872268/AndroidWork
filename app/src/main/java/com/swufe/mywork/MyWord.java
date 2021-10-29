package com.swufe.mywork;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MyWord extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    MyAdapter myadapter;
    public static ListView listView;
    public static ArrayList<HashMap<String,String>> retlist;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;
    String TAG = "myword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);


        listView = findViewById(R.id.word_list);
        retlist = (ArrayList<HashMap<String,String>>) new WordList().obtainword(MyWord.this);
        Log.i("list", "wordlist: "+retlist);
        myadapter = new MyAdapter(MyWord.this,R.layout.list_item,retlist);

        listView.setAdapter(myadapter);
        listView.setOnItemLongClickListener(MyWord.this);
        listView.setOnItemClickListener(MyWord.this);
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
                Log.i(TAG, "onClick: 长按position："+position);
//                retlist = (ArrayList<HashMap<String,String>>) new WordList().obtainword(MyWord.this);
                Log.i(TAG, "onClick: retlist"+retlist);




//                Object itemAtPosition = listView.getItemAtPosition(position);
                HashMap<String,String> map = retlist.get(position-1);
                String word = map.get("word");
                String content = map.get("content");
                db.delete("word", "word = ?", new String[]{word});
                Log.i(TAG, "onItemClick:title "+word);
//                Log.i(TAG, "onItemClick: detail"+content);

                retlist.remove(position);
                myadapter.notifyDataSetChanged();

//                Log.i(TAG, "onItemLongClick: size="+retlist.size());
            }

        }).setNegativeButton("否",null);
        bulider.create().show();
//
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        listView = findViewById(R.id.word_list);
        Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String,String>)itemAtPosition;
        String word = map.get("word");
        String content = map.get("content");
        Log.i(TAG, "onItemClick:title "+word);
        Log.i(TAG, "onItemClick: detail"+content);


//发送消息
        Intent intent = new Intent(this, WordDetail.class);
        intent.putExtra("detail_word",word);
        intent.putExtra("detail_content",content);
        startActivity(intent);
    }

    public void to_page_rember(View v){
        //转到记单词界面
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void to_page_wordlist(View v){
        //转到展示全部单词界面
        Intent intent = new Intent(this,MyWord.class);
        startActivity(intent);
    }
    public void to_page_search(View v){
        //转到搜索单词界面
        Intent intent = new Intent(this,SearchWord.class);
        startActivity(intent);
    }
    public void to_page_add(View v){
        //转到搜索单词界面
        Intent intent = new Intent(this,AddWord.class);
        startActivity(intent);
    }
}