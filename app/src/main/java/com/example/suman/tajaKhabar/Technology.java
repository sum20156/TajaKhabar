package com.example.suman.tajaKhabar;

import android.content.Context;
import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;



public class Technology extends Fragment {

    String[] headlinegTech,urlTech,imgTech;
    List<RowData> mRowDatasTech =new ArrayList<RowData>();
    RowData singleRowDataTech;
    ListView technologylist;
    Intent techWebIntent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        technologylist=view.findViewById(R.id.technology_list);
        techWebIntent=new Intent(getActivity(),web_view.class);
        RequestParams params = new RequestParams();
        params.put("country",Trending.place);
        params.put("category","technology");
        params.put("apiKey",Trending.apikey);
        donetworking(params);
        return view;
    }

    public void donetworking(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Trending.newsapiurl,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                NewsData technlogyNewsdata = NewsData.fromJson(response);
                headlinegTech=technlogyNewsdata.getHeadline().clone();
                urlTech=technlogyNewsdata.getNewsurl();
                imgTech=technlogyNewsdata.getImgurl();

                for (int i =0;i<headlinegTech.length;i++){
                    singleRowDataTech= new RowData(headlinegTech[i],urlTech[i],imgTech[i]);
                    mRowDatasTech.add(singleRowDataTech);
                }

                MyAdapter techAdapter = new MyAdapter(getContext(),mRowDatasTech);
                techAdapter.notifyDataSetChanged();
                technologylist.setAdapter(techAdapter);
                technologylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        techWebIntent.putExtra("newsurl",urlTech[position]);
                        startActivity(techWebIntent);
                    }
                });
                Toast.makeText(getContext(),"Technology Request Sucess",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Toast.makeText(getContext(),"Request Failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
