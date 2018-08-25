package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.BaseJsonKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetMovieIdFromProducerAsyncTask extends BaseGetDataAsyncTask<String> {
    public GetMovieIdFromProducerAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<String> convertData(String strData) throws JSONException {
        List<String> listId = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        JSONArray jsonArray = jsonObject.optJSONArray(JsonKey.LOGOS);
        for (int i = 0 ;i<jsonArray.length();i++){
            JSONObject movie = jsonArray.optJSONObject(i);
            String movieID = movie.optString(JsonKey.ID);
            listId.add(movieID);
        }
        return listId;
    }
    public static class JsonKey extends BaseJsonKey{
        public static final String LOGOS = "logos";
    }
}
