package com.framgia.moviedb_20.data.api;


import com.framgia.moviedb_20.data.model.CastDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetCastDetailAsyncTask extends BaseGetDataAsyncTask<CastDetail> {

    public GetCastDetailAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<CastDetail> convertData(String strData) throws JSONException {
        List<CastDetail> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        CastDetail castDetail = new CastDetail(jsonObject);
        result.add(castDetail);
        return result;
    }
}
