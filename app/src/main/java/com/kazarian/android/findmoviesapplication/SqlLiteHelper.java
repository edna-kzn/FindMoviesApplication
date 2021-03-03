package com.kazarian.android.findmoviesapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Omdbapi_Movie_info_moldel_2_singleresult.OmdbapiMovieInfoModel2;
import Omdbapi_Movie_info_moldel_2_singleresult.OmdbapiMovieInfoModel3;

public class SqlLiteHelper extends SQLiteOpenHelper {

    String TABLE_NAME = "Movies";  //this is database name

    public SqlLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MOVIES_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
                "_id integer primary key autoincrement, " +
                "fOmdbId text, " +
                "fTitle text, " +
                "fGenre text, " +
                "fYear text, " +
                "fDirector text, " +
                "fCountry text, " +
                "fImage text " +")";

        db.execSQL(CREATE_MOVIES_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public void insertIntoMoviesTable(String fOmdbId,
                                      String fTitle,
                                      String fGenre,
                                      String fYear,
                                      String fDirector,
                                      String fCountry,
                                      String fImage){
        String INSERT_INTO_MOVIES_TABLE_QUERY = "INSERT INTO " + TABLE_NAME +
                "(fOmdbId, fTitle, fGenre, fYear, fDirector, fCountry, fImage) VALUES ("
                    + "'" + fOmdbId+ "'" +", "
                    + "'" + fTitle + "'" +", "
                    + "'" + fGenre + "'"+", "
                    + "'" + fYear + "'"+", "
                    + "'" + fDirector + "'"+", "
                    + "'" + fCountry +"'" + ", "
                    + "'" + fImage +"'"
                +")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_INTO_MOVIES_TABLE_QUERY);
        db.close();

    }

    public List<OmdbapiMovieInfoModel3> getAllSavedRecords(){
        String SELECT_ALL_QUERY = "SELECT " +
                                        "_id integer, " +
                                        "fOmdbId, " +
                                        "fTitle, " +
                                        "fGenre, " +
                                        "fYear, " +
                                        "fDirector, " +
                                        "fCountry, " +
                                        "fImage "+
                                        "FROM " + TABLE_NAME;


        //OmdbapiMovieInfoModel2 singleline = new OmdbapiMovieInfoModel2();
        ArrayList<OmdbapiMovieInfoModel3> SavedRecords = new ArrayList<OmdbapiMovieInfoModel3>();

        //ArrayList<String> SavedOmdbId = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL_QUERY, null);
       // Integer teded = cursor.getCount();
       // Integer i = 0;
        while (cursor.moveToNext()){

            //Integer id = cursor.getInt(0);
            String omdbId = cursor.getString(1);
            String title = cursor.getString(2);
            String genre = cursor.getString(3);
            String year = cursor.getString(4);
            String director = cursor.getString(5);
            String country = cursor.getString(6);
            String image_link = cursor.getString(7);

            //--------------------------------------------

            SavedRecords.add(new OmdbapiMovieInfoModel3(title, year, genre, director, country, image_link, omdbId));

         //   i++;

        }
        db.close();
        return SavedRecords;
    }
}
