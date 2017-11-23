package com.example.chala.inclass05_chalasani;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by chala on 2/13/2017.
 */

public class NewsUtil {
    static public class NewspullParser{
        static ArrayList<News> parseNews(InputStream in) throws XmlPullParserException,IOException{
            String c="";
            XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            News news=null;
            ArrayList<News> newsList=new ArrayList<News>();
            int event=parser.getEventType();
            String w=" ";
            while(event!=XmlPullParser.END_DOCUMENT){
                switch(event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("ttl")){
                            c = parser.nextText();
                        }
                        if (parser.getName().equals("item")) {
                            news = new News();
                        }
                        if(!(c.equals(""))){
                            if (parser.getName().equals("title")) {
                                news.setTitle(parser.nextText());
                            } else if (parser.getName().equals("description")) {
                                news.setDescription(parser.nextText());
                            } else if (parser.getName().equals("pubDate")) {
                                news.setPub(parser.nextText());
                            } else if (parser.getName().equals("media:content")) {
                                if (parser.getAttributeValue(null, "height").equals(parser.getAttributeValue(null, "width"))) {
                                    news.setSqr(parser.getAttributeValue(null, "url"));

                                }
                                if (!(parser.getAttributeValue(null, "height").equals(parser.getAttributeValue(null, "width")))) {
                                    news.setImg(parser.getAttributeValue(null, "url"));
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            newsList.add(news);
                            news = null;
                        }

                        break;
                    default:
                        break;
                }
                event=parser.next();
            }
            return newsList;
        }
    }
}
