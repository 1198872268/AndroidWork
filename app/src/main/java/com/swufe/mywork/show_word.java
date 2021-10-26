package com.swufe.mywork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class show_word extends AppCompatActivity {
    TextView show_word,show_content;
    Button next;
    private  String TAG = "show_word";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_word);

        show_word = findViewById(R.id.next_word);
        show_content = findViewById(R.id.next_content);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        String content = intent.getStringExtra("content");
        Log.i(TAG, "word "+word);
        Log.i(TAG, "content "+content);
        show_word.setText(word);
        show_content.setText(content);

    }
    public void next(View V){
        sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int i = sp.getInt("position",0);
        i++;
        editor.putInt("position",i);
        editor.commit();
        Log.i(TAG, "position: "+i);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}