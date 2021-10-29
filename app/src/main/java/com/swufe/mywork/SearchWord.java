package com.swufe.mywork;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchWord extends AppCompatActivity {
    EditText search_input_word;
    TextView show_word,show_content;
    Button submit,local_to_add,local_to_searchonline;
    SQLiteDatabase db;
    MyDBHelper myDBHelper;

    String TAG = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

        TextView show = findViewById(R.id.page_search);
        show.setTextColor(this.getResources().getColor(R.color.blue));
    }

    public void search(View v){
        myDBHelper = new MyDBHelper(SearchWord.this);
        db = myDBHelper.getReadableDatabase();
        search_input_word = findViewById(R.id.search_input_word);
        show_content = findViewById(R.id.search_show_content);
        String input_word = search_input_word.getText().toString();

        Cursor cursor = db.rawQuery("SELECT * FROM word WHERE word = ?",
                new String[]{input_word});
//存在数据才返回true
        if (cursor.moveToFirst()) {
            String word = cursor.getString(0);
            String content =cursor.getString(1);
            Log.i(TAG, "search: "+word+":"+content);
            show_content.setText(content);
        }else{
            local_to_add = findViewById(R.id.local_to_add);
            local_to_searchonline = findViewById(R.id.local_to_searchonline);
            local_to_add.setVisibility(View.VISIBLE);
            local_to_searchonline.setVisibility(View.VISIBLE);
            show_content.setText("未找到相应单词，请自行添加,或者在线搜索");
        }
    }

    //如果点击在线搜索，则跳转至在线搜索页面
    public void page_to_searchonline(View v){
        Intent intent = new Intent(this,SearchOnline.class);
        startActivity(intent);
    }

    //如果点击自行添加，则跳转至添加单词
    public void page_to_addword(View v){
        Intent intent = new Intent(this,AddWord.class);
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