package com.example.suman.tajaKhabar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Sports.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Sports#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sports extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Sports() {
        // Required empty public constructor
    }
    String[] headlinegSports,urlSports,imgSports;
    List<RowData> mRowDatasSports =new ArrayList<RowData>();
    RowData singleRowDataSports;
    ListView sportslist;
    Intent sportsWebIntent;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sports.
     */
    // TODO: Rename and change types and number of parameters
    public static Sports newInstance(String param1, String param2) {
        Sports fragment = new Sports();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sports, container, false);

        sportslist=view.findViewById(R.id.sports_list);
        sportsWebIntent= new Intent(getActivity(),web_view.class);
        RequestParams params = new RequestParams();
        params.put("country",Trending.place);
        params.put("category","sports");
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
                headlinegSports=technlogyNewsdata.getHeadline().clone();
                urlSports=technlogyNewsdata.getNewsurl();
                imgSports=technlogyNewsdata.getImgurl();

                for (int i =0;i<headlinegSports.length;i++){
                    singleRowDataSports= new RowData(headlinegSports[i],urlSports[i],imgSports[i]);
                    mRowDatasSports.add(singleRowDataSports);
                }

                MyAdapter sportsAdapter = new MyAdapter(getContext(),mRowDatasSports);
                sportsAdapter.notifyDataSetChanged();
                sportslist.setAdapter(sportsAdapter);
                sportslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        sportsWebIntent.putExtra("newsurl",urlSports[position]);
                        startActivity(sportsWebIntent);
                    }
                });
                Toast.makeText(getContext(),"Sports Request Sucess",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Toast.makeText(getContext(),"Request Failed",Toast.LENGTH_LONG).show();
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
