package com.example.rahulkumar.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by rahulkumar on 02-08-2017.
 */


public class CustomAdapter extends BaseAdapter{
    private Context context;
    private String[] items;
    LayoutInflater inflater;


    public CustomAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.eachcell, null);
        }
        TextView t = (TextView) convertView.findViewById(R.id.grid_Value);
        t.setText(items[position]);
        return convertView;
    }
}
