package com.example.socketio_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {
    List<String> dataa;
    Context context;
    private LayoutInflater li;
    public ChatAdapter(List<String> dataa, Context context) {
        this.dataa = dataa;
        this.context = context;
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataa.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null) {
            convertView = li.inflate(R.layout.item_listchat, null);
            vh = new ViewHolder();
            vh.idTextChat = (TextView) convertView.findViewById(R.id.idTextChat);
    }
        return convertView;
}
    private static class ViewHolder {
        public TextView idTextChat;
    }}

