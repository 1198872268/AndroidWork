package com.swufe.mywork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SQLite_insert extends AppCompatActivity implements View.OnClickListener {
    String TAG = "insert";
    private Context mContext;
    private Button btn_insert;
    private Button btn_query;
    private Button btn_update;
    private Button btn_delete;
    private SQLiteDatabase db;
    private MyDBHelper myDBHelper;
    private StringBuilder sb;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);
        mContext = SQLite_insert.this;
        myDBHelper = new MyDBHelper(mContext);

        bindViews();
    }

    private void bindViews() {
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_query.setOnClickListener(SQLite_insert.this);
        btn_insert.setOnClickListener(SQLite_insert.this);
        btn_update.setOnClickListener(SQLite_insert.this);
        btn_delete.setOnClickListener(SQLite_insert.this);
    }

    @Override
    public void onClick(View v) {
        myDBHelper = new MyDBHelper(SQLite_insert.this);
        db = myDBHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.btn_insert:
                db.execSQL("INSERT INTO word(word,content) values(?,?)",
                        new String[]{ "11", "11"});
                Log.i(TAG, "onClick: "+"success");
                Toast.makeText(mContext, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query:

                Cursor cursor = db.rawQuery("SELECT * FROM word WHERE word = ?",
                        new String[]{"11"});
                //存在数据才返回true
                if (cursor.moveToFirst()) {
                    String wordid = cursor.getString(1);
                    String name = cursor.getString(2);
                    String phone = cursor.getString(3);

                }
                cursor.close();
                Toast.makeText(mContext, sb.toString(), Toast.LENGTH_SHORT).show();
                break;
//            case R.id.btn_update:
//                ContentValues values2 = new ContentValues();
//                values2.put("name", "嘻嘻~");
//                //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
//                db.update("person", values2, "name = ?", new String[]{"呵呵~2"});
//                break;
//            case R.id.btn_delete:
//                //参数依次是表名，以及where条件与约束
//                db.delete("person", "personid = ?", new String[]{"3"});
//                break;
        }
    }
}
