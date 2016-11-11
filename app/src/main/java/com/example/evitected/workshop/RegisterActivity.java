package com.example.evitected.workshop;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etDisplayName, etUsername, etPassword, etPassConfirm;
    private Button btnConfirmRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindWidget();
        setEvent();
    }

    private void bindWidget() {
        etDisplayName = (EditText) findViewById(R.id.etDisplayName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassConfirm = (EditText) findViewById(R.id.etPassConfirm);
        btnConfirmRegister = (Button) findViewById(R.id.btnConfirmRegister);
    }
    private void setEvent(){
        btnConfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    new Register(etUsername.getText().toString(),
                            etPassword.getText().toString(),
                            etPassConfirm.getText().toString(),
                            etDisplayName.getText().toString()).execute();
                }else{
                    Toast.makeText(RegisterActivity.this, "Register Failure!, Please input blank space.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validateForm(){
        String displayName = etDisplayName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passConfirm = etPassConfirm.getText().toString().trim();
        if(displayName.isEmpty()){ return false; }
        if(username.isEmpty()){ return false; }
        if(password.isEmpty()){ return false; }
        if(passConfirm.isEmpty()){ return false; }
        if(!password.equals(passConfirm)){
            return false;
        }
        return true;
    }
    private class Register extends AsyncTask<Void, Void, String>{
        private String username, password, passConfirm, displayName;

        public Register(String username, String password, String passConfirm, String displayName) {
            this.username = username;
            this.password = password;
            this.passConfirm = passConfirm;
            this.displayName = displayName;
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
                        .add("password_con", passConfirm)
                        .add("display_name", displayName)
                        .build();
                request = new Request.Builder()
                        .url("http://kimhun55.com/pollservices/signup.php")
                        .post(requestBody)
                        .build();
            try{
                response = client.newCall(request).execute(); //Sync
                if (response.isSuccessful()) {
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
                "result": -7,
                "result_desc": " duplicate username"
              }
            }*/
            try{
                JSONObject rootObj = new JSONObject(s); // new Object JSON and return value S
                if(rootObj.has("result")){
                    JSONObject resultOb = rootObj.getJSONObject("result");
                    if(resultOb.getInt("result") == 1){
                        Toast.makeText(RegisterActivity.this, resultOb.getString("result_desc"), Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, resultOb.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }
}