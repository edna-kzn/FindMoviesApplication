package com.kazarian.android.findmoviesapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
