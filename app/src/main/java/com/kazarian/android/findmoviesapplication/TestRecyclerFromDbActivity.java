package com.kazarian.android.findmoviesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Omdbapi_Movie_info_moldel_2_singleresult.OmdbapiMovieInfoModel2;
import Omdbapi_Movie_info_moldel_2_singleresult.OmdbapiMovieInfoModel3;

public class TestRecyclerFromDbActivity extends AppCompatActivity {

    ArrayList<String> fnames, fjenres, fyears, fimages;
    ArrayList<String> fimdbids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_from_db);



        SqlLiteHelper helper = new SqlLiteHelper(TestRecyclerFromDbActivity.this, "Movies", null, 1);


                String testshowcontent1;
                String testshowcontent2;
                String testshowcontent3;
                Integer testtedad;

                List<OmdbapiMovieInfoModel3> savedrecords = helper.getAllSavedRecords();


                fnames = new ArrayList<>();
                fyears = new ArrayList<>();
                fimdbids = new ArrayList<>();
                fjenres = new ArrayList<>();
                fimages = new ArrayList<>();

                for (int i = 0; i< savedrecords.size(); i++){
                    fnames.add(savedrecords.get(i).getTitle());
                    testshowcontent1 = savedrecords.get(i).getTitle();
                    fyears.add(savedrecords.get(i).getYear());
                    testshowcontent2 = savedrecords.get(i).getYear();
                    fimdbids.add(savedrecords.get(i).getImdbID());
                    testshowcontent3 = savedrecords.get(i).getImdbID();
                    fjenres.add(savedrecords.get(i).getGenre());
                    fimages.add(savedrecords.get(i).getPoster());
                    testshowcontent3 = savedrecords.get(i).getPoster();
                }

                //System.out.println(savedrecords);
                testtedad = fnames.size();

        RecyclerView TestrecyclerView = findViewById(R.id.recyclerTest);

        LinearLayoutManager manager2 = new LinearLayoutManager(TestRecyclerFromDbActivity.this, RecyclerView.VERTICAL, false);
        TestrecyclerView.setLayoutManager(manager2);

        //set adaptor
        TestRecyclerAdapter adapter = new TestRecyclerAdapter(fnames, fyears, fjenres, fimages, fimdbids);
        TestrecyclerView.setAdapter(adapter);


    }
}