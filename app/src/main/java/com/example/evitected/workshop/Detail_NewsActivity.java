package com.example.evitected.workshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.evitected.workshop.datamodel.News;
import com.example.evitected.workshop.datamodel.ResponseNews;
import com.example.evitected.workshop.datamodel.Result;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Detail_NewsActivity extends AppCompatActivity {

    private int news_id;
    private TextView DtvTopicNews, DtvDetailNews;
    NewsActivity newsData = new NewsActivity();
    private ImageView imgDetail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__news);
        bindWidget();
        Intent intent = getIntent();
        String id = intent.getStringExtra("news_id");
        new loadDataNews(id).execute();
    }

    private void bindWidget() {
        DtvTopicNews = (TextView) findViewById(R.id.DtvTopicNews);
        DtvDetailNews = (TextView) findViewById(R.id.DtvDetailNews);
        imgDetail = (ImageView) findViewById(R.id.imgDetail);
    }
    public class loadDataNews extends AsyncTask<Void, Void, String>{
        private String id;

        public loadDataNews(String id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Detail_NewsActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/getNewsDetail.php?news_id="+id)
                    .get()
                    .build();
            try{
                response = client.newCall(request).execute();
                if (response.isSuccessful()){
                    return response.body().string();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ResponseNews responseNews = new Gson().fromJson(s, ResponseNews.class);
            if(responseNews != null){
                Result result = responseNews.getResult();
                if(result.getResult() == 1){
                    News news = responseNews.getNews();
                    DtvTopicNews.setText(news.getTitle());
                    DtvDetailNews.setText(news.getDetail());
                    Glide.with(Detail_NewsActivity.this).load(news.getImageUrl()).into(imgDetail);
                    progressDialog.dismiss();
                }
            }
            /*
            try{
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultOb = rootObj.getJSONObject("result");
                    if(resultOb.getInt("result") == 1){
                        JSONObject newsRoot = rootObj.getJSONObject("news");
                        if(newsRoot.getString("news_id").equals(id)){
                            News news = new News();
                            news.setTitle(newsRoot.getString("title"));
                            news.setDetailNews(newsRoot.getString("detail"));
                            DtvTopicNews.setText(news.getTitle());
                            DtvDetailNews.setText(news.getDetailNews());
                        }
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }*/
        }
    }
}
