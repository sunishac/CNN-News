package com.example.chala.inclass05_chalasani;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chala on 2/13/2017.
 */

public class ImgAsyncTask extends AsyncTask<String,Void,Bitmap> {
    IDat activity;

    public ImgAsyncTask(IDat activity) {
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try{
            URL url= new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null){
            activity.setImage(bitmap);
        }
    }
    public interface IDat{
        public void setImage(Bitmap bitmap);
    }
}
