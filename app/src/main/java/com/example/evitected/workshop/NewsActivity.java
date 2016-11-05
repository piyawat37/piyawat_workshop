package com.example.evitected.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class NewsActivity extends AppCompatActivity {

    private ListView newsList;
    static String[] TopicNews = {"Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News"};
    static String[] DateNews = {"5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559"};
    int[] imgID = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        customAdap();
    }

    private void customAdap() {
        newsList = (ListView) findViewById(R.id.newsList);
        newsList.setAdapter(new CustomAdapter(getApplicationContext(), TopicNews, DateNews, imgID));
    }
}
