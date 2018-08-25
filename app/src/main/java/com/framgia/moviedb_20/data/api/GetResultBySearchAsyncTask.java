package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.SearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetResultBySearchAsyncTask extends BaseGetDataAsyncTask<SearchResult> {
    public GetResultBySearchAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<SearchResult> convertData(String strData) throws JSONException {
        List<SearchResult> results = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        JSONArray jsonArray = jsonObject.optJSONArray(SearchResult.JsonKey.RESULT);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject searchObject = jsonArray.optJSONObject(i);
            SearchResult searchResult = new SearchResult(searchObject);
            results.add(searchResult);
        }
        return results;
    }
}
