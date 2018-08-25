package com.framgia.moviedb_20.data.source.remote;

import com.framgia.moviedb_20.BuildConfig;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.api.GetResultBySearchAsyncTask;
import com.framgia.moviedb_20.data.source.SearchDataSource;
import com.framgia.moviedb_20.utils.Constants;

public class SearchRemoteDataSource implements SearchDataSource.RemoteDataSource {

    private BaseGetDataAsyncTask.OnGetDataListener mListener;

    public SearchRemoteDataSource(BaseGetDataAsyncTask.OnGetDataListener listener) {
        mListener = listener;
    }

    @Override
    public void getResult(String query) {
        StringBuilder url_search = new StringBuilder(Constants.BLANK_STRING);
        url_search.append(Constants.DOMAIN);
        url_search.append(Constants.SEARCH_MULTI);
        url_search.append(Constants.QUESTION_MARK);
        url_search.append(Constants.PARAM_KEY_API_KEY);
        url_search.append(BuildConfig.API_KEY);
        url_search.append(Constants.AND);
        url_search.append(Constants.LANGUAGE);
        url_search.append(Constants.VALUE_LANGUAGE);
        url_search.append(Constants.AND);
        url_search.append(Constants.PAGE);
        url_search.append(Constants.PAGE_NUMBER);
        url_search.append(Constants.AND);
        url_search.append(Constants.QUERY);
        url_search.append(query);
        new GetResultBySearchAsyncTask(mListener).execute(url_search.toString());
    }
}
