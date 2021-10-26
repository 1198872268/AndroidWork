package com.swufe.mywork;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWord extends AppCompatActivity {
    EditText add_word,add_content;
    Button btn_add_word;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    String TAG = "Addword" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

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
}