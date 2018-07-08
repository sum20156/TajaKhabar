package com.example.suman.tajaKhabar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends BaseAdapter {
        Context mContext;
        List<RowData> mRowData;

    public MyAdapter(Context context, List<RowData> rowdatas) {
            this.mContext=context;
            this.mRowData=rowdatas;
    }

    @Override
    public int getCount() {
        return mRowData.size();
    }

    @Override
    public Object getItem(int position) {
        return mRowData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class MyViewHolder{
        private ImageView newsimg;
        private TextView newsheadline;
        private TextView newsurl;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        RowData rowData=mRowData.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_data, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.newsheadline = convertView.findViewById(R.id.textViewTrending);
            myViewHolder.newsimg = convertView.findViewById(R.id.imageViewTrending);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();

        }
        myViewHolder.newsheadline.setText(rowData.getHeadline());
        Picasso.get().load(rowData.getImgurl()).into(myViewHolder.newsimg);
        return convertView;
    }
}
