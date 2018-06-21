package org.androidtown.testdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyon1001 on 2018-06-21.
 */

class MyListAdapter extends BaseAdapter{
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<RPostItem> postItems;
    int layout;

    public MyListAdapter(Context context, int alayout, ArrayList<RPostItem> postItems) {
        this.maincon = context;
        Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        this.postItems = postItems;
        this.layout = alayout;
    }

    @Override
    public int getCount() {
        return postItems.size();
    }

    @Override
    public Object getItem(int i) {
        return postItems.get(i).rname;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int pos= position;
        if(view ==null)
            view = Inflater.inflate(layout, viewGroup, false);

        TextView desc = (TextView)view.findViewById(R.id.item_title);
        desc.setText(postItems.get(position).rname);

        TextView addr = (TextView)view.findViewById(R.id.item_address);
        addr.setText(postItems.get(position).radd);

        TextView owner = (TextView)view.findViewById(R.id.item_writer);
        owner.setText(postItems.get(position).rowner);

        return view;
    }
}
