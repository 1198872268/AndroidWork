package com.swufe.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Edits;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    TextView show_word;
    Button btu_know,btu_tips;
    ArrayList<HashMap<String,String>> retlist;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;
    String TAG = "MainAcitvity";
    ArrayList<HashMap<String,String>> wordlist;



    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordlist = new WordList().obtainword(MainActivity.this);
        show_word = findViewById(R.id.word);
        btu_know = findViewById(R.id.btn_konw);
        btu_tips = findViewById(R.id.btn_tips);
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
        i++;
        String show_word = word.get("word");
        String show_content = word.get("content");
        Log.i(TAG, "intent"+show_word);
        Intent intent = new Intent(MainActivity.this,show_word.class);
        intent.putExtra("word",show_word);
        intent.putExtra("content",show_content);
        startActivity(intent);

    }


}