package com.example.suman.tajaKhabar;

public class RowData {

    public String headline,newsurl,imgurl;

    public RowData(String headline, String newsurl, String imgurl) {
        this.headline = headline;
        this.newsurl = newsurl;
        this.imgurl = imgurl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getNewsurl() {
        return newsurl;
    }

    public String getImgurl() {
        return imgurl;
    }
}
