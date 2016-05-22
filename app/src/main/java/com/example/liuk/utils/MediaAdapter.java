package com.example.liuk.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuk.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIUK on 2016/5/21.
 */
public class MediaAdapter extends BaseAdapter {
    private Context context;
    private List<MediaListCellData> list = new ArrayList<MediaListCellData>();

    public MediaAdapter(Context context)
    {
        this.context = context;
    }

    public void add(MediaListCellData data){
        list.add(data);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MediaListCellData getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            // Log.e("buju","布局&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            view = LayoutInflater.from(context).inflate(R.layout.media_list_cell,null);

        }

        MediaListCellData data = getItem(i);

        ImageView ivIcon = (ImageView)view.findViewById(R.id.ivIcon);
        TextView tvPath = (TextView)view.findViewById(R.id.tvPath);

        ivIcon.setImageResource(data.iconId);
        tvPath.setText(data.path);

        return view;
    }
}
