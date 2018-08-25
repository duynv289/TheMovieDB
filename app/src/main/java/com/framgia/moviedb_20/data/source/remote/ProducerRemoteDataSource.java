package com.framgia.moviedb_20.data.source.remote;

import com.framgia.moviedb_20.BuildConfig;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.api.GetMovieDetailFromProducerAsyncTask;
import com.framgia.moviedb_20.data.api.GetMovieIdFromProducerAsyncTask;
import com.framgia.moviedb_20.data.api.GetProducerAsyncTask;
import com.framgia.moviedb_20.data.source.ProducerDataSource;
import com.framgia.moviedb_20.utils.Constants;

public class ProducerRemoteDataSource implements ProducerDataSource.RemoteDataSource {

    private BaseGetDataAsyncTask.OnGetDataListener mListener;

    public ProducerRemoteDataSource(BaseGetDataAsyncTask.OnGetDataListener listener) {
        mListener = listener;
    }

    @Override
    public void getProducer(int id) {
        StringBuilder urlProducerDetail = new StringBuilder(Constants.BLANK_STRING);
        urlProducerDetail.append(Constants.DOMAIN);
        urlProducerDetail.append(Constants.COMPANY);
        urlProducerDetail.append(id);
        urlProducerDetail.append(Constants.QUESTION_MARK);
        urlProducerDetail.append(Constants.PARAM_KEY_API_KEY);
        urlProducerDetail.append(BuildConfig.API_KEY);
        new GetProducerAsyncTask(mListener).execute(urlProducerDetail.toString());
    }

    @Override
    public void getMovieDetail(String id) {
        StringBuilder urlMovieDetail = new StringBuilder(Constants.BLANK_STRING);
        urlMovieDetail.append(Constants.DOMAIN);
        urlMovieDetail.append(Constants.DISCOVER);
        urlMovieDetail.append(Constants.MOVIE);
        urlMovieDetail.append(Constants.QUESTION_MARK);
        urlMovieDetail.append(Constants.PARAM_KEY_API_KEY);
        urlMovieDetail.append(BuildConfig.API_KEY);
        urlMovieDetail.append(Constants.AND);
        urlMovieDetail.append(Constants.WITH_COMPANIES);
        urlMovieDetail.append(id);
        new GetMovieDetailFromProducerAsyncTask(mListener).execute(urlMovieDetail.toString());
    }
}
