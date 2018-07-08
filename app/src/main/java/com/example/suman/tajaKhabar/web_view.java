package com.example.suman.tajaKhabar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class web_view extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }
    @Override
    protected void onResume(){
        final ProgressBar progressBar= findViewById(R.id.progressBar);
        progressBar.setMax(100);
        super.onResume();
        Intent myIntent = getIntent();
        String url = myIntent.getStringExtra("newsurl");
        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        progressBar.setProgress(0);
    }



    }
