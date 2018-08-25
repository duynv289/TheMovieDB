package com.framgia.moviedb_20.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{
    private int mPage;
    private List<BaseMovie> mBaseMovie;
    private List<Integer> mGenreIds;

    public Category(JSONObject jsonObject) throws JSONException {
        mPage = jsonObject.optInt(JsonKey.PAGE);
        JSONArray results = jsonObject.optJSONArray(BaseJsonKey.RESULT);
        mBaseMovie = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject movieObject = results.optJSONObject(i);
            mBaseMovie.add(new BaseMovie(movieObject));
            JSONArray genreIds = movieObject.optJSONArray(JsonKey.GENRE_ID);
            mGenreIds = new ArrayList<>();
            for (int j = 0; j < genreIds.length(); j++) {
                mGenreIds.add(genreIds.getInt(j));
            }
        }
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<BaseMovie> getBaseMovie() {
        return mBaseMovie;
    }

    public void setBaseMovie(List<BaseMovie> baseMovie) {
        mBaseMovie = baseMovie;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public static class JsonKey {
        private static final String PAGE = "page";
        private static final String GENRE_ID = "genre_ids";
    }
}
