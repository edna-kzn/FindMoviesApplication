package com.kazarian.android.findmoviesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import Omdbapi_Movie_info_model.OmdbapiMovieInfoModel;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------connecting to site omdb api-------start---------------

        String myUrl = "http://www.omdbapi.com/?t=gun&apikey=5e5354bb";   //<----------- url omdb api bayad bashad
        AsyncHttpClient client = new AsyncHttpClient();
        //------receiving json code------------------------------------
        client.get(myUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //-----mapping json code to Gson class----------------
                Gson gson = new Gson();
                OmdbapiMovieInfoModel model = gson.fromJson(response.toString(), OmdbapiMovieInfoModel.class);
                //-----extracting data from Gson class----------------
                String fname = model.getTitle();
                String fjenre = model.getGenre();
                String fyear = model.getYear();
                String fimage = model.getPoster();

                System.out.println(response.toString());

                //-----preparing to show data in recycler View-----------
                RecyclerView myRecycler = findViewById(R.id.recycler);

                LinearLayoutManager myManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                myRecycler.setLayoutManager(myManager);

                //i shude make an adapter for recycler view here
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });




    }
}