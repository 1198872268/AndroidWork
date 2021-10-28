package com.swufe.mywork;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchOnline extends AppCompatActivity implements Runnable{

    EditText search_online;
    TextView online_content;
    Button btn_add_new;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    String url;
    String TAG = "search_online";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_online);

//        Log.i(TAG, "onCreate: "+xx);


    }

    public void online(View v){
        search_online = findViewById(R.id.search_online);
        String word = search_online.getText().toString();
        url = "http://www.iciba.com/word?w="+word;
        Log.i(TAG, "run: "+url);
        Thread td = new Thread(this);
        td.start();
    }


    //将获取到的单词加入生词本
    public void online_add_word(View v){
        search_online = findViewById(R.id.search_online);
        String word = search_online.getText().toString();
        online_content = findViewById(R.id.online_content);
        String content = online_content.getText().toString();
        myDBHelper = new MyDBHelper(SearchOnline.this);
        db = myDBHelper.getWritableDatabase();
        String know = "1";
        db.execSQL("INSERT INTO word(word,content,know) values(?,?,?)",
                new String[]{ word, content, know});
        Log.i(TAG, "onClick: "+"success");
        Toast.makeText(SearchOnline.this, "已添加到生词本~", Toast.LENGTH_SHORT).show();
    }

    //run方法获取网络服务，在网上爬取单词含义
    @Override
    public void run() {

//        retlist = new ArrayList<HashMap<String,String>>();
//
        Document doc = null;
        try {
                search_online = findViewById(R.id.search_online);
                String word = search_online.getText().toString();
                doc = Jsoup.connect(url).get();
//
                Log.i("xx", "run: title :"+doc.title());
                Elements tables = doc.getElementsByClass("Mean_part__1Xi6p");
            Log.i(TAG, "run: tables"+tables);
            online_content = findViewById(R.id.online_content);
            if(tables.size()!=0){
                Element table1 = tables.get(0);
                Elements trs = table1.getElementsByTag("span");
                Log.i("run", "run: tds"+trs.get(0).text());
////获取table内的tr
                String content = "";
                for(Element tr:trs){
                    String xx = tr.text();
                    content = content + xx;
                }

                online_content.setText(content);
            Log.i("xx", " word"+word+"---->"+"content:"+content  );
            }else {
                online_content.setText("请检查拼写");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}