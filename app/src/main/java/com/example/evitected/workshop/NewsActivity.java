package com.example.evitected.workshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evitected.workshop.datamodel.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity {


    private ListView newsList;
    //static String[] TopicNews = {"Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News"};
    //ArrayList<String> TopicNews;
/*
    static String[] NewsID;
    static String[] TopicNews;
    //static String[] DateNews = {"5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559", "5 Nov 2559"};
    //ArrayList<String> DateNews;
    static String[] DateNews;
    int[] imgID = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    //static String[] imgURL;
    static String[] DescNews;
*/

    String resultLogin = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsList = (ListView) findViewById(R.id.newsList);
        new loadData().execute();
    }

    public class loadData extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NewsActivity.this);
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
                    .url("http://kimhun55.com/pollservices/feednews.php")
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
            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultOb = rootObj.getJSONObject("result");
                    if(resultOb.getInt("result") == 1){
                        JSONArray jArray = rootObj.getJSONArray("news_list");
                        if(jArray != null && jArray.length() > 0){
                            int newsSize = jArray.length();
                            final List<News> nNewsList = new ArrayList<>();
                            for(int i = 0 ; i < newsSize; i++){
                                News news = new News();
                                JSONObject newsJsObj = jArray.getJSONObject(i);
                                news.setNewsId(newsJsObj.getString("news_id"));
                                news.setTitle(newsJsObj.getString("title"));
                                news.setCreateDate(newsJsObj.getString("create_date"));
                                news.setShortDesc(newsJsObj.getString("short_description"));
                                news.setImageUrl(newsJsObj.getString("image_url"));
                                nNewsList.add(news);
                            }
                            newsList.setAdapter(new CustomAdapter(getApplicationContext(),nNewsList));
                            progressDialog.dismiss();

                            newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent i = new Intent(NewsActivity.this, Detail_NewsActivity.class);
                                    i.putExtra("news_id", nNewsList.get(position).getNewsId());
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }
                    }else{

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    public class loadData extends AsyncTask<String, String, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(NewsActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/feednews.php")
                    .build();
            Response response = null;
            try{
                response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    String JSonData = response.body().string();
                    JSONObject JsObj = new JSONObject(JSonData);
                    JSONArray dataArr = JsObj.getJSONArray("news_list");
                    System.out.print(dataArr.length());
                    int length = dataArr.length();
                    NewsID = new String[length];
                    TopicNews = new String[length];
                    DateNews = new String[length];
                    DescNews = new String[length];
                    Log.d("Check", String.valueOf(TopicNews.length));
                    for(int i = 0 ; i < dataArr.length(); i++){
                        JSONObject DataObj = dataArr.getJSONObject(i);
                        NewsID[i] = DataObj.getString("news_id");
                        TopicNews[i] = DataObj.getString("title");
                        DateNews[i] = DataObj.getString("create_date");
                        DescNews[i] = DataObj.getString("short_description");
                     }
                    customAdap();
                    setEvent();
                }
            }catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }*/
}