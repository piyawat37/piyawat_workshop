package com.example.evitected.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView etUsername, etPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();
        setEvent();
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
                */
                new loginActive(etUsername.getText().toString(),etPassword.getText().toString()).execute();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void bindWidget() {
        etUsername = (TextView) findViewById(R.id.etUsername);
        etPassword = (TextView) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnForRegister);
    }
    public class loginActive extends AsyncTask<Void, Void, String>{
        private String username, password;

        public loginActive(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response = null;
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();
            try{
                response = client.newCall(request).execute();
                if(response.isSuccessful()){
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
            /*{
              "result": {
                "result": 1,
                "result_desc": " success"
              },
              "user_info": {
                "user_id": "26",
                "image_url": null,
                "display_name": ""
              }
            }*/
            try{
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultOb = rootObj.getJSONObject("result");
                    if(resultOb.getInt("result") == 1){
                        Intent i = new Intent(MainActivity.this, NewsActivity.class);
                        i.putExtra("resultLogin",resultOb.getInt("result"));
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this, resultOb.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

    }
}
