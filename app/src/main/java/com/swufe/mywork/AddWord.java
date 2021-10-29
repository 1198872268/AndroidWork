package com.swufe.mywork;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWord extends AppCompatActivity {
    EditText add_word,add_content;
    Button btn_add_word;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    String TAG = "Addword" ;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        show = findViewById(R.id.page_add);
        show.setTextColor(this.getResources().getColor(R.color.blue));
    }


    public void add_word(View btn){
        add_word = findViewById(R.id.add_word);
        add_content = findViewById(R.id.add_content);
        btn_add_word = findViewById(R.id.btn_add_word);

        String word = add_word.getText().toString();
        String content =add_content.getText().toString();

        myDBHelper = new MyDBHelper(AddWord.this);
        db = myDBHelper.getWritableDatabase();
        String know = "1";
        db.execSQL("INSERT INTO word(word,content,know) values(?,?,?)",
                new String[]{ word, content, know});
        Log.i(TAG, "onClick: "+"success");
        Toast.makeText(AddWord.this, "插入完毕~", Toast.LENGTH_SHORT).show();
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