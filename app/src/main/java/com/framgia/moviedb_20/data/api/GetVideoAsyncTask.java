package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.BaseJsonKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetVideoAsyncTask extends BaseGetDataAsyncTask<String> {
    public GetVideoAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<String> convertData(String strData) throws JSONException {
        List<String> movieKeys = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        JSONArray movieArrays = jsonObject.optJSONArray(JsonKey.RESULT);
        for (int i = 0; i < movieArrays.length(); i++) {
            JSONObject movie = movieArrays.optJSONObject(i);
            String movieKey = movie.optString(JsonKey.KEY);
            movieKeys.add(movieKey);
        }
        return movieKeys;
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String RESULT = "results";
        public static final String KEY = "key";
    }
}
