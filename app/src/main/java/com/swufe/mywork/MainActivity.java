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


}