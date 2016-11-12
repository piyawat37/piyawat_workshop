package com.example.evitected.workshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.evitected.workshop.datamodel.News;

import java.util.List;

/**
 * Created by Evitected on 5/11/2559.
 */
public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private List<News> data;

    public CustomAdapter(Context mContext, List<News> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        if(data == null){
            return 0;
        }else{
            return data.size();
        }
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
            myHolder.imgUrl = (ImageView) convertView.findViewById(R.id.imgTopic);
            convertView.setTag(myHolder);
        } else{
            myHolder = (ViewHolder) convertView.getTag();
        }
        News news = data.get(position);
        myHolder.topicNews.setText(news.getTitle());
        myHolder.dateNews.setText(news.getCreateDate());
        Glide.with(mContext).load(news.getImageUrl()).into(myHolder.imgUrl);
        return convertView;
    }
    public class ViewHolder{
        TextView topicNews;
        TextView dateNews;
        ImageView imgUrl;
    }
}
