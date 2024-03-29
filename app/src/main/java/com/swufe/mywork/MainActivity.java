package com.swufe.mywork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView show_word;
    Button btu_know,btu_tips;
    ArrayList<HashMap<String,String>> retlist;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;
    String TAG = "MainAcitvity";
    ArrayList<HashMap<String,String>> wordlist;


    SharedPreferences sp;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordlist = new WordList().obtainword(MainActivity.this);
        show_word = findViewById(R.id.word);
        btu_know = findViewById(R.id.btn_konw);
        btu_tips = findViewById(R.id.btn_tips);
        refresh();
        sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        i = sp.getInt("position",0);
        if(i>=wordlist.size()){
            Toast.makeText(MainActivity.this, "今天的内容以学习完毕", Toast.LENGTH_SHORT).show();
            i=wordlist.size()-1;
        }
        Log.i(TAG, "onCreate: position:"+i);
        HashMap<String, String> word = wordlist.get(i);
        String myword = word.get("word");
        show_word.setText(myword);
        TextView show = findViewById(R.id.page_remeber);
        show.setTextColor(this.getResources().getColor(R.color.blue));
    }

    public void know(View v){
        btu_know = findViewById(R.id.btn_konw);
        show_word = findViewById(R.id.word);
        btu_tips = findViewById(R.id.btn_tips);

        ArrayList<HashMap<String,String>> wordlist = new WordList().obtainword(MainActivity.this);
        HashMap<String, String> word = wordlist.get(i);
        String show_word = word.get("word");
        String show_content = word.get("content");
        Log.i(TAG, "intent"+show_word);
        Intent intent = new Intent(MainActivity.this,show_word.class);
        intent.putExtra("word",show_word);
        intent.putExtra("content",show_content);
        intent.putExtra("position",i);
        startActivity(intent);
        finish();
    }


    //每日更新单词位置
    public void refresh(){
        sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //将当前时间放进sharedperferences
        String old_time = sp.getString("time","0000-00-00");
        Log.i(TAG, "run: old_time:"+old_time);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String now_time = format.format(System.currentTimeMillis());
        Log.i(TAG, "run: now_time: "+now_time);
        editor.putString("time",now_time);
        //如果当前时间与sharedperfences中时间不一样说明未更新过，需要更新
        if(!old_time.equals(now_time)){
            editor.putInt("position",1);
            editor.commit();
            Log.i(TAG, "refresh: "+now_time+"--"+old_time);
        }
        }

    public void to_page_rember(View v){
        //转到记单词界面

        TextView tv = findViewById(R.id.page_remeber);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void to_page_wordlist(View v){
        //转到展示全部单词界面
//        finish();
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