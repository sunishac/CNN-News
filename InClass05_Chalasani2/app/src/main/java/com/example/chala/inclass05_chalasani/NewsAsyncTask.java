package com.example.chala.inclass05_chalasani;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chala on 2/13/2017.
 */

public class NewsAsyncTask extends AsyncTask<String,Void,ArrayList<News>> {
    IData activity;

    public NewsAsyncTask(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<News> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();
                Log.d("demo", in.toString());
                return NewsUtil.NewspullParser.parseNews(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    protected void onPostExecute(ArrayList<News> newses) {
        super.onPostExecute(newses);
            if(newses!=null){
                activity.list(newses);
            }
    }

    public interface IData{
        public void list(ArrayList<News> news);
    }
}
