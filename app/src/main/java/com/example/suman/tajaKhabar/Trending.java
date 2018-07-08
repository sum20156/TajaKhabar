package com.example.suman.tajaKhabar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;



public class Trending extends Fragment {



    ListView mListView;
    public static   String apikey="66997fa2e6214c499190f8e0224df636";
    public static String newsapiurl ="https://newsapi.org/v2/top-headlines";
    public static String place ="in";
    List<RowData> rowdatas;
    String[] headline,url,img;
    Intent webIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trending, container, false);

        mListView = (ListView)rootView.findViewById(R.id.trending_list);
        rowdatas=new ArrayList<RowData>();
        RequestParams params = new RequestParams();
        params.put("country",place);
        params.put("apiKey",apikey);
        doNetworking(params);

        return rootView;
    }

    public void doNetworking(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(newsapiurl,params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                webIntent = new Intent(getActivity(),web_view.class);
                    NewsData newsData=NewsData.fromJson(response);
                    headline= newsData.getHeadline().clone();
                    url=newsData.getNewsurl().clone();
                    img=newsData.getImgurl().clone();

                for (int i =0;i<headline.length;i++){
                    RowData rowData= new RowData(headline[i],url[i],img[i]);
                    rowdatas.add(rowData);
                }
                MyAdapter myAdapter=new MyAdapter(getContext(),rowdatas);
                myAdapter.notifyDataSetChanged();
                mListView.setAdapter(myAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        webIntent.putExtra("newsurl",url[position]);
                        startActivity(webIntent);
                    }
                });
                Toast.makeText(getContext(),headline[6],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getContext(),"Request Failed",Toast.LENGTH_LONG).show();
            }
        });
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
