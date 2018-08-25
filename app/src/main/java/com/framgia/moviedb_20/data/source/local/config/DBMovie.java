package com.framgia.moviedb_20.data.source.local.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMovie extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ListFavorite";
    private static final String TABLE_NAME = "Movie";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_MOVIE_ID = "MovieID";
    private static final String COLUMN_MOVIE_NAME = "Title";
    private static final String COLUMN_POSTER_PATH = "PosterPath";
    private static final int VERSION = 1;

    public DBMovie(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_MOVIE_ID + " INTEGER," +
                COLUMN_MOVIE_NAME + " TEXT," +
                COLUMN_POSTER_PATH + " TEXT)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
