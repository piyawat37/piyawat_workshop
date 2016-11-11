package com.example.evitected.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Detail_NewsActivity extends AppCompatActivity {

    private int news_id;
    private TextView DtvTopicNews, DtvDetailNews;
    NewsActivity newsData = new NewsActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__news);
        bindWidget();
        Intent i = getIntent();
        news_id = i.getIntExtra("news_id",0);

        queryData();
    }

    private void queryData() {
        if(news_id != -1 && news_id <= newsData.TopicNews.length ){
            DtvTopicNews.setText(newsData.TopicNews[news_id]);
            DtvDetailNews.setText(newsData.DescNews[news_id]);
        }
    }

    private void bindWidget() {
        DtvTopicNews = (TextView) findViewById(R.id.DtvTopicNews);
        DtvDetailNews = (TextView) findViewById(R.id.DtvDetailNews);
    }
}
