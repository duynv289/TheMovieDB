package com.framgia.moviedb_20.data.api;

import com.framgia.moviedb_20.data.model.CastCredit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetCastCreditAsyncTask extends BaseGetDataAsyncTask<CastCredit> {

    public GetCastCreditAsyncTask(OnGetDataListener listener) {
        super(listener);
    }

    @Override
    protected List<CastCredit> convertData(String strData) throws JSONException {
        List<CastCredit> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(strData);
        CastCredit castCredit = new CastCredit(jsonObject);
        result.add(castCredit);
        return result;
    }
}
