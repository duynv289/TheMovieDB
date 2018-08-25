package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.BaseJsonKey;
import com.framgia.moviedb_20.data.model.BaseMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetMovieByGenreAsyncTask extends BaseGetDataAsyncTask<BaseMovie> {
    public GetMovieByGenreAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<BaseMovie> convertData(String strData) throws JSONException {
        List<BaseMovie> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        JSONArray jsonArray = jsonObject.optJSONArray(JsonKey.ITEMS);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject movieObject = jsonArray.optJSONObject(i);
            BaseMovie baseMovie = new BaseMovie(movieObject);
            if (baseMovie == null) {
            }
            result.add(baseMovie);
        }
        return result;
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String ITEMS = "items";
    }
}
