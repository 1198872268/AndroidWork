package com.swufe.mywork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HashMap<String,String>> data) {
        super(context, resource, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,false);

        }
        Map<String,String> map=  (Map<String,String>)getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.item_word);
//        TextView detail = (TextView) itemView.findViewById(R.id.item_content);

        title.setText(map.get("word"));
//        detail.setText(map.get("content"));

        return  itemView;
//        return super.getView(position, convertView, parent);
    }
}