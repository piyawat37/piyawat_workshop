package com.example.evitected.workshop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Evitected on 9/11/2559.
 */

public class Download_data implements Runnable {
    public download_complete caller;
    public interface download_complete{
        public void get_data(String data);
    }

    public Download_data(download_complete caller) {
        this.caller = caller;
    }
    private String link;
    public void download_data_from_link(String link){
        this.link = link;
        Thread t = new Thread(this);
        t.start();
    }
    public final Handler handler = new Handler(){
        public void handlerMessage(Message msg){
            String response = msg.getData().getString("message");
            caller.get_data(response);
        }
    };
    public void threadMsg(String msg){
        if(!msg.equals(null) && !msg.equals("")){
            Message msgObj = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putString("message", msg);
            msgObj.setData(b);
            handler.sendMessage(msgObj);
        }
    }
    public static String download(String url){
        URL website;
        StringBuilder response = null;
        try{
            website = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestProperty("charset","utf-8");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader((
                            connection.getInputStream()
                    ))
            );
            response = new StringBuilder();
            String inputLine;
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response.toString();
    }
    @Override
    public void run() {
        threadMsg(download(this.link));
    }
}