package com.example.evitected.workshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Evitected on 5/11/2559.
 */
public class CustomAdapter extends BaseAdapter {

    Context mContext;
    String[] topicNews;
    String[] dateNews;
    int[] imgID;

    public CustomAdapter(Context context, String[] topicNews, String[] dateNews, int[] imgID){
        this.mContext = context;
        this.topicNews = topicNews;
        this.dateNews = dateNews;
        this.imgID = imgID;

    }
    @Override
    public int getCount() {
        return topicNews.length;
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
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder myHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_news__detail__list, null);
            myHolder = new ViewHolder();
            myHolder.topicNews = (TextView) convertView.findViewById(R.id.tvTopicNews);
            myHolder.dateNews = (TextView) convertView.findViewById(R.id.tvTopicDate);
            myHolder.imgID = (ImageView) convertView.findViewById(R.id.imgTopic);
            convertView.setTag(myHolder);
        } else{
            myHolder = (ViewHolder) convertView.getTag();
        }
        myHolder.topicNews.setText(topicNews[position]);
        myHolder.dateNews.setText(dateNews[position]);
        myHolder.imgID.setImageResource(imgID[position]);
        return convertView;
    }
    public class ViewHolder{
        TextView topicNews;
        TextView dateNews;
        ImageView imgID;
    }
}
