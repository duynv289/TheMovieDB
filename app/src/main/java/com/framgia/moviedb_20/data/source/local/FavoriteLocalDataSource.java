package com.framgia.moviedb_20.data.source.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.source.FavoriteDataSource;
import com.framgia.moviedb_20.data.source.MovieDataSource;
import com.framgia.moviedb_20.data.source.local.config.DBMovie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteLocalDataSource implements FavoriteDataSource.LocalDataSource {
    private static final String TABLE_NAME = "Movie";
    private static final String COLUMN_MOVIE_ID = "MovieID";
    private static final String COLUMN_MOVIE_TITLE = "Title";
    private static final String COLUMN_POSTER_PATH = "PosterPath";
    private DBMovie mDBMovie;

    public FavoriteLocalDataSource(DBMovie DBMovie) {
        mDBMovie = DBMovie;
    }

    @Override
    public void addMovie(BaseMovie baseMovie) {
        SQLiteDatabase db = mDBMovie.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_MOVIE_ID + "," + COLUMN_MOVIE_TITLE + "," + COLUMN_POSTER_PATH + ") VALUES (" +
                baseMovie.getId() + ",'" + baseMovie.getTitle() + "','" + baseMovie.getPosterPath() + "');";
        db.execSQL(sqlInsert);
        db.close();
    }

    @Override
    public void removeMovie(int id) {
        String deleteQuery = "DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_MOVIE_ID+"="+id;
        SQLiteDatabase db = mDBMovie.getWritableDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }

    @Override
    public boolean checkMovieInFavorite(BaseMovie baseMovie) {
        boolean STATE = false;
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = mDBMovie.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            do {
                int movieId = cursor.getInt(1);
                if (baseMovie.getId() == movieId) {
                    STATE = true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return STATE;
    }

    @Override
    public List<BaseMovie> getAllMovieFromFavorite() {
        List<BaseMovie> baseMovies = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = mDBMovie.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            do {
                int movieId = cursor.getInt(1);
                String title = cursor.getString(2);
                String posterPath = cursor.getString(3);
                BaseMovie baseMovie = new BaseMovie(movieId,title,posterPath);
                baseMovies.add(baseMovie);
            }while (cursor.moveToNext());
        }
        return baseMovies;
    }
}
