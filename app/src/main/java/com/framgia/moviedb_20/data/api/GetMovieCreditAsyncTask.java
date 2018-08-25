package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.BaseJsonKey;
import com.framgia.moviedb_20.data.model.MovieCast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetMovieCreditAsyncTask extends BaseGetDataAsyncTask<MovieCast> {

    public GetMovieCreditAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<MovieCast> convertData(String strData) throws JSONException {
        List<MovieCast> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        JSONArray jsonArray = jsonObject.optJSONArray(BaseJsonKey.CAST);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject movieCast = jsonArray.optJSONObject(i);
            MovieCast cast = new MovieCast(movieCast);
            result.add(cast);
        }
        return result;
    }
}