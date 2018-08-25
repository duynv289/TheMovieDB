package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.Category;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetCategoryAsyncTask extends BaseGetDataAsyncTask<Category> {
    public GetCategoryAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<Category> convertData(String strData) throws JSONException {
        List<Category> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        Category category = new Category(jsonObject);
        result.add(category);
        return result;
    }
}
