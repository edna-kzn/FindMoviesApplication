package com.kazarian.android.findmoviesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import Omdbapi_Movie_info_moldel_2_singleresult.OmdbapiMovieInfoModel2;
import cz.msebera.android.httpclient.Header;

public class RecyclerItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_item_detail);



        TextView dname = findViewById(R.id.txtFNameindetail);
        TextView dgenre = findViewById(R.id.txtFGenreindetail);
        TextView dyear = findViewById(R.id.txtFYearindetail);
        TextView ddirector = findViewById(R.id.txtFDirectorindetail);
        TextView dcountry = findViewById(R.id.txtFCountryindetail);
        ImageView dimage = findViewById(R.id.imgFImageindetail);
        TextView dImage_link = findViewById(R.id.txtFImageLink);  //this textview is invisible it just keeps the image link data (web address)
        //String Image_link = "";  //keeps the image webaddress


        Button btnsave = findViewById(R.id.btnSave);


        //-------connecting to site omdb api-------start---------------
        //----------- getting the selected movie id from recycler item ------------------
        String dfid;   //omdbID
        Intent intent = getIntent();
        dfid = intent.getStringExtra("ifId");
        String Url_search_by_id;

        //----------- preparing the url for omdb api ------------------
        if (dfid != ""){
            Url_search_by_id = "http://www.omdbapi.com/?i=" + dfid +"&apikey=5e5354bb";
        }
        else return;

        AsyncHttpClient client = new AsyncHttpClient();

        //------receiving json code for url for the first time (search by id)------------------------------------
        client.get(Url_search_by_id, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());

                Gson gson = new Gson();
                //-----mapping json code to Gson class----------------
                OmdbapiMovieInfoModel2 model2 = gson.fromJson(response.toString(), OmdbapiMovieInfoModel2.class);

                //----- por kardan textboxhaye rooye form detail------
                dname.setText(model2.getTitle());
                dgenre.setText(model2.getGenre());
                dyear.setText(model2.getYear());
                ddirector.setText(model2.getDirector());
                dcountry.setText(model2.getCountry());
                Picasso.get().load(model2.getPoster()).into(dimage);
                dImage_link.setText(model2.getPoster().toString());


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(RecyclerItemDetailActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        });

        //------------------- save button clicked---------------------
        SqlLiteHelper helper = new SqlLiteHelper(RecyclerItemDetailActivity.this, "Movies", null, 1);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----save item to table------call insert into metod in Sqllitehelper class ----
                String iOmdbId = dfid;
                String iTitle = dname.getText().toString();
                String iGenre = dgenre.getText().toString();
                String iYear = dyear.getText().toString();
                String iDirector = ddirector.getText().toString();
                String iCountry = dcountry.getText().toString();
                String iimage = dImage_link.getText().toString();

                helper.insertIntoMoviesTable(iOmdbId, iTitle, iGenre, iYear, iDirector, iCountry, iimage);

                Toast.makeText(RecyclerItemDetailActivity.this, "Movie is Saved!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}