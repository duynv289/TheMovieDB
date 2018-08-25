package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.Genre;
import com.framgia.moviedb_20.data.model.MovieDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetGenresAsyncTask extends BaseGetDataAsyncTask<Genre> {
    public GetGenresAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<Genre> convertData(String strData) throws JSONException {
        List<Genre> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        JSONArray jsonArray = jsonObject.optJSONArray(MovieDetail.JsonKey.GENRE);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject genreObject = jsonArray.optJSONObject(i);
            Genre genre = new Genre(genreObject);
            result.add(genre);
        }
        return result;
    }
}
