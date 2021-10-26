package com.swufe.mywork;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchOnline extends AppCompatActivity implements Runnable{

    MyAdapter myadapter;
    ArrayList<HashMap<String,String>> retlist;
    Handler handler = new Handler();
    ListView listView;
    EditText search_online;
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
                Element table1 = tables.get(0);
                Elements trs = table1.getElementsByTag("span");
                Log.i("run", "run: tds"+trs.get(0).text());
////获取table内的tr
                String content = "";
                for(Element tr:trs){
                    String xx = tr.text();
                    content = content + xx;
                }

            Log.i("xx", " word"+word+"---->"+"content:"+content  );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}