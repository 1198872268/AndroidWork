package com.swufe.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchWord extends AppCompatActivity {
    EditText search_input_word;
    TextView show_word,show_content;
    Button submit;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;

    String TAG = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);
    }

    public void search(View v){
        myDBHelper = new MyDBHelper(SearchWord.this);
        db = myDBHelper.getReadableDatabase();
        search_input_word = findViewById(R.id.search_input_word);
        show_word = findViewById(R.id.search_show_word);
        show_content = findViewById(R.id.search_show_content);
        String input_word = search_input_word.getText().toString();

        Cursor cursor = db.rawQuery("SELECT * FROM word WHERE word = ?",
                new String[]{input_word});
//存在数据才返回true
        if (cursor.moveToFirst()) {
            String word = cursor.getString(0);
            String content =cursor.getString(1);
            Log.i(TAG, "search: "+word+":"+content);
            show_word.setText(word);
            show_content.setText(content);
        }else{
            show_content.setText("未找到相应单词，请自行添加");
        }
    }
}