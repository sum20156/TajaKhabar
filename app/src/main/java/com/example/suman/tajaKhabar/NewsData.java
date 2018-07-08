package com.example.suman.tajaKhabar;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsData {

    public String[] headline, newsurl, imgurl;

    public static NewsData fromJson(JSONObject object) {
        try {
            NewsData newsData = new NewsData();
            newsData.headline= new String[Integer.parseInt(object.getString("totalResults"))];
            newsData.newsurl= new String[Integer.parseInt(object.getString("totalResults"))];
            newsData.imgurl= new String[Integer.parseInt(object.getString("totalResults"))];
            for (int i = 0; i < Integer.parseInt(object.getString("totalResults")); i++) {
                newsData.headline[i] = object.getJSONArray("articles").getJSONObject(i).getString("title");
                newsData.newsurl[i] = object.getJSONArray("articles").getJSONObject(i).getString("url");
                newsData.imgurl[i] = object.getJSONArray("articles").getJSONObject(i).getString("urlToImage");
            }
            return newsData;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }
    }

    public String[] getHeadline() {
        return headline;
    }

    public String[] getNewsurl() {
        return newsurl;
    }

    public String[] getImgurl() {
        return imgurl;
    }
}
