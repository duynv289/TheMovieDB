package com.framgia.moviedb_20.data.source.remote;

import com.framgia.moviedb_20.BuildConfig;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.api.GetCastCreditAsyncTask;
import com.framgia.moviedb_20.data.api.GetCastDetailAsyncTask;
import com.framgia.moviedb_20.data.source.CastDataSource;
import com.framgia.moviedb_20.utils.Constants;

public class CastRemoteDataSource implements CastDataSource.RemoteDataSource {
    private BaseGetDataAsyncTask.OnGetDataListener mListener;

    public CastRemoteDataSource(BaseGetDataAsyncTask.OnGetDataListener listener) {
        mListener = listener;
    }

    @Override
    public void getCastDetail(int id) {
        StringBuilder urlDetail = new StringBuilder(Constants.BLANK_STRING);
        urlDetail.append(Constants.DOMAIN);
        urlDetail.append(Constants.PERSON);
        urlDetail.append(id);
        urlDetail.append(Constants.QUESTION_MARK);
        urlDetail.append(Constants.PARAM_KEY_API_KEY);
        urlDetail.append(BuildConfig.API_KEY);
        urlDetail.append(Constants.AND);
        urlDetail.append(Constants.LANGUAGE);
        urlDetail.append(Constants.VALUE_LANGUAGE);
        new GetCastDetailAsyncTask(mListener).execute(urlDetail.toString());
    }

    @Override
    public void getCastCredit(int id) {
        StringBuilder urlCast = new StringBuilder(Constants.BLANK_STRING);
        urlCast.append(Constants.DOMAIN);
        urlCast.append(Constants.PERSON);
        urlCast.append(id);
        urlCast.append(Constants.MOVIE_CREDITS);
        urlCast.append(Constants.QUESTION_MARK);
        urlCast.append(Constants.PARAM_KEY_API_KEY);
        urlCast.append(BuildConfig.API_KEY);
        urlCast.append(Constants.AND);
        urlCast.append(Constants.LANGUAGE);
        urlCast.append(Constants.VALUE_LANGUAGE);
        new GetCastCreditAsyncTask(mListener).execute(urlCast.toString());
    }
}
