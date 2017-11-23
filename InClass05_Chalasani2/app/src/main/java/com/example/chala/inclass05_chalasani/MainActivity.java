package com.example.chala.inclass05_chalasani;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsAsyncTask.IData,ImgAsyncTask.IDat {
    Button b1,b2,b3,b4,b5;
    TextView t1,t2,t3;
    ProgressDialog pD;
    ImageView iv;
    ArrayList<News> newsList=new ArrayList<News>();
    int z=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("CNN News");
        getSupportActionBar().setIcon(R.drawable.cnn);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pD= new ProgressDialog(MainActivity.this);
        pD.setMessage("Loading ");
        pD.setCancelable(false);
        b1=(Button) findViewById(R.id.first);
        b2=(Button) findViewById(R.id.prev);
        b3=(Button) findViewById(R.id.next);
        b4=(Button) findViewById(R.id.last);
        b5=(Button) findViewById(R.id.finish);
        iv=(ImageView) findViewById(R.id.imageView);
        t1=(TextView) findViewById(R.id.title);
        t2=(TextView) findViewById(R.id.pub);
        t3=(TextView) findViewById(R.id.des);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);

        findViewById(R.id.getNews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pD.show();
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
                b4.setEnabled(true);
                b5.setEnabled(true);
                new NewsAsyncTask(MainActivity.this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(z>0) {
                    z = 0;
                    new ImgAsyncTask(MainActivity.this).execute(newsList.get(z).getSqr());
                    t1.setText(newsList.get(z).getTitle());
                    t2.setText("Published on: " + newsList.get(z).getPub());
                    t3.setText("Description: " + "\n" + newsList.get(z).getDescription());
                }
                else{
                    Toast.makeText(MainActivity.this,"This is the first news",Toast.LENGTH_LONG).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(z>0){
                    z--;
                    new ImgAsyncTask(MainActivity.this).execute(newsList.get(z).getSqr());
                    t1.setText(newsList.get(z).getTitle());
                    t2.setText("Published on: " + newsList.get(z).getPub());
                    t3.setText("Description: " + "\n" + newsList.get(z).getDescription());
                }else{
                    Toast.makeText(MainActivity.this,"This is the first news",Toast.LENGTH_LONG).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(z<newsList.size()-1){
                    z++;
                    new ImgAsyncTask(MainActivity.this).execute(newsList.get(z).getSqr());
                    t1.setText(newsList.get(z).getTitle());
                    t2.setText("Published on: " + newsList.get(z).getPub());
                    t3.setText("Description: " + "\n" + newsList.get(z).getDescription());

                }else{
                    Toast.makeText(MainActivity.this,"This is the last news",Toast.LENGTH_LONG).show();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(z<newsList.size()-1){
                    z=newsList.size()-1;
                    new ImgAsyncTask(MainActivity.this).execute(newsList.get(z).getSqr());
                    t1.setText(newsList.get(z).getTitle());
                    t2.setText("Published on: " + newsList.get(z).getPub());
                    t3.setText("Description: " + "\n" + newsList.get(z).getDescription());
                }
                else{
                    Toast.makeText(MainActivity.this,"This is the last news",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void list(ArrayList<News> news) {
        newsList=news;
        Log.d("demo","list is "+newsList);
        pD.dismiss();
        new ImgAsyncTask(MainActivity.this).execute(newsList.get(0).getSqr());
        t1.setText(newsList.get(0).getTitle());
        t2.setText("Published on: "+newsList.get(0).getPub());
        t3.setText("Description: "+"\n"+ newsList.get(0).getDescription());
    }

    @Override
    public void setImage(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }
}
