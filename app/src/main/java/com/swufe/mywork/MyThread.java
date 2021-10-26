package com.swufe.mywork;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class MyThread implements Runnable{
    Handler handler;
    public void MyThread(Handler hander){
        this.handler = hander;
    }
    @Override
    public void run() {

        ArrayList<HashMap<String,String>> retlist = new ArrayList<HashMap<String,String>>();

        Document doc = null;
        try {
            for(int i = 0;i<10;i++){
                if (i==0){
                    doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/"+"index.html").get();
                }else{
                    doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/"+"index_"+i+".html").get();
                }
                Log.i("xx", "run: title :"+doc.title());
                Elements tables = doc.getElementsByTag("tbody");
                Element table1 = tables.get(1);
                Elements trs = table1.getElementsByTag("tr");
//                Elements tds = trs.first().getElementsByTag("td");
//                Log.i("run", "run: tds"+trs);
                trs.remove(0);
//获取table内的tr
                for(Element tr:trs){
                    Elements tds = tr.getElementsByTag("td");
                    String cname = tds.get(0).text();
                    String cval = tds.get(5).text();
                    HashMap<String,String> map = new HashMap<String,String>();
                    map.put("ItemTitle",cname);
                    map.put("ItemDetail",cval);
                    retlist.add(map);
//                    retlist.add(cname+"--->"+cval);
                    Log.i("xx", "run: cname"+cname+"---->"+"cval:"+cval  );
                }
            }

            Message msg = handler.obtainMessage(2);
            msg.obj = retlist;
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
