package com.swufe.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class show_word extends AppCompatActivity {
    TextView show_word,show_content;
    Button next;
    private  String TAG = "show_word";

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
}