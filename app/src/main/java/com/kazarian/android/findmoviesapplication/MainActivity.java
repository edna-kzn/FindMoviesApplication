package com.kazarian.android.findmoviesapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import Omdbapi_Movie_info_model.OmdbapiMovieInfoModel;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    ArrayList<String> fnames, fjenres, fyears, fimages;
    ArrayList<Double> fimdbids;

    //Title (in OmdbapiMovieInfoModel) = FName
    //Year (in OmdbapiMovieInfoModel) = FYear
    //ImdbID (in OmdbapiMovieInfoModel) = FImdbID
    //Type (in OmdbapiMovieInfoModel) = FGenre
    //Poster (in OmdbapiMovieInfoModel) = FImage

    String myUrl = "http://www.omdbapi.com/?s=current&apikey=5e5354bb";
    String changablefase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgSearch = findViewById(R.id.imgSearch);
        EditText edtSearch = findViewById(R.id.edtSearch);


        //-------connecting to site omdb api-------start---------------
        //<----------- url omdb api bayad bashad
        AsyncHttpClient client = new AsyncHttpClient();

        //------making the url string for connecting to omdb site ------
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUrl = "http://www.omdbapi.com/?s=";
                String changablefase = edtSearch.getText().toString();   //using this variable to make myUrl complete with adding edtSearch

                if (changablefase != "") {
                    myUrl = myUrl.toString() + changablefase.toString() + "&apikey=5e5354bb";

                    //------receiving json code------------------------------------
                    client.get(myUrl, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            //-----mapping json code to Gson class----------------
                            Gson gson = new Gson();
                            OmdbapiMovieInfoModel model = gson.fromJson(response.toString(), OmdbapiMovieInfoModel.class);
                            //-----extracting data from Gson class----------------

                            fnames = new ArrayList<>();
                            fyears = new ArrayList<>();
                            //fimdbids = new ArrayList<>();
                            fjenres = new ArrayList<>();
                            fimages = new ArrayList<>();

                            for (int i = 0; i< model.getSearch().size(); i++){
                                fnames.add(model.getSearch().get(i).getTitle());
                                fyears.add(model.getSearch().get(i).getYear());
                                //fimdbids.add(model.getSearch().get(i).getImdbID());
                                fjenres.add(model.getSearch().get(i).getType());
                                fimages.add(model.getSearch().get(i).getPoster());
                            }

                            System.out.println(response.toString());

                            //-----preparing to show data in recycler View-----------
                            RecyclerView myRecycler = findViewById(R.id.recycler);

                            LinearLayoutManager myManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                            myRecycler.setLayoutManager(myManager);

                            //i shude make an adapter for recycler view here
                            FilmRecyclerAdapter adapter = new FilmRecyclerAdapter(fnames, fyears, fjenres, fimages, this);
                            myRecycler.setAdapter(adapter);

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });

                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter Keyword!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        //------receiving json code------------------------------------
        client.get(myUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //-----mapping json code to Gson class----------------
                Gson gson = new Gson();
                OmdbapiMovieInfoModel model = gson.fromJson(response.toString(), OmdbapiMovieInfoModel.class);
                //-----extracting data from Gson class----------------

                fnames = new ArrayList<>();
                fyears = new ArrayList<>();
                //fimdbids = new ArrayList<>();
                fjenres = new ArrayList<>();
                fimages = new ArrayList<>();

                for (int i = 0; i< model.getSearch().size(); i++){
                    fnames.add(model.getSearch().get(i).getTitle());
                    fyears.add(model.getSearch().get(i).getYear());
                    //fimdbids.add(model.getSearch().get(i).getImdbID());
                    fjenres.add(model.getSearch().get(i).getType());
                    fimages.add(model.getSearch().get(i).getPoster());
                }

                System.out.println(response.toString());

                //-----preparing to show data in recycler View-----------
                RecyclerView myRecycler = findViewById(R.id.recycler);

                LinearLayoutManager myManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                myRecycler.setLayoutManager(myManager);

                //i shude make an adapter for recycler view here
                FilmRecyclerAdapter adapter = new FilmRecyclerAdapter(fnames, fyears, fjenres, fimages, this);
                myRecycler.setAdapter(adapter);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }


        });



    }

    @Override
    public Void onItemClick(int position) {
        return null;
    }
}