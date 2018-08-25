package com.framgia.moviedb_20.data.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CastCredit {
    private List<BaseMovie> mBaseMovies;
    private List<Integer> mGenreIds;

    public CastCredit() {
    }

    public CastCredit(JSONObject jsonObject) throws JSONException {
        JSONArray resultsArray = jsonObject.optJSONArray(BaseJsonKey.CAST);
        mBaseMovies = new ArrayList<>();
        mGenreIds =  new ArrayList<>();
        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject castObject = resultsArray.optJSONObject(i);
            BaseMovie baseMovie = new BaseMovie(castObject);
            mBaseMovies.add(baseMovie);
            int genreId = castObject.optInt(JsonKey.GENRE_IDS);
            mGenreIds.add(genreId);
        }
    }

    public List<BaseMovie> getBaseMovies() {
        return mBaseMovies;
    }

    public void setBaseMovies(List<BaseMovie> baseMovies) {
        mBaseMovies = baseMovies;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public static class JsonKey extends BaseJsonKey{
        private static final String GENRE_IDS = "genre_ids";
    }
}
