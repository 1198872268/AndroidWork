package com.swufe.mywork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WordDetail extends AppCompatActivity {
    TextView show_word,show_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        show_word = findViewById(R.id.detail_word);
        show_content = findViewById(R.id.detail_content);

        Intent intent = getIntent();
        String word = intent.getStringExtra("detail_word");
        String content = intent.getStringExtra("detail_content");

        show_word.setText(word);
        show_content.setText(content);
    }
}