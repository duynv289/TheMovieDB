package com.framgia.moviedb_20.data.source.remote;

import com.framgia.moviedb_20.BuildConfig;
import com.framgia.moviedb_20.data.api.BaseGetDataAsyncTask;
import com.framgia.moviedb_20.data.api.GetCategoryAsyncTask;
import com.framgia.moviedb_20.data.api.GetGenresAsyncTask;
import com.framgia.moviedb_20.data.api.GetMovieByGenreAsyncTask;
import com.framgia.moviedb_20.data.api.GetMovieCreditAsyncTask;
import com.framgia.moviedb_20.data.api.GetMovieDetailAsyncTask;
import com.framgia.moviedb_20.data.api.GetVideoAsyncTask;
import com.framgia.moviedb_20.data.source.MovieDataSource;
import com.framgia.moviedb_20.utils.Constants;

public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {

    private BaseGetDataAsyncTask.OnGetDataListener mListener;

    public MovieRemoteDataSource(BaseGetDataAsyncTask.OnGetDataListener listener) {
        mListener = listener;
    }

    public void getMovies(String category) {
        StringBuilder url = new StringBuilder(Constants.BLANK_STRING);
        url.append(Constants.DOMAIN);
        url.append(Constants.MOVIE_DETAIL);
        url.append(category);
        url.append(Constants.QUESTION_MARK);
        url.append(Constants.PARAM_KEY_API_KEY);
        url.append(BuildConfig.API_KEY);
        url.append(Constants.AND);
        url.append(Constants.LANGUAGE);
        url.append(Constants.VALUE_LANGUAGE);
        url.append(Constants.AND);
        url.append(Constants.PAGE);
        url.append(Constants.PAGE_NUMBER);
        new GetCategoryAsyncTask(mListener).execute(url.toString());
    }

    @Override
    public void getMovieDetail(int id) {
        StringBuilder urlDetail = new StringBuilder(Constants.BLANK_STRING);
        urlDetail.append(Constants.DOMAIN);
        urlDetail.append(Constants.MOVIE_DETAIL);
        urlDetail.append(id);
        urlDetail.append(Constants.QUESTION_MARK);
        urlDetail.append(Constants.PARAM_KEY_API_KEY);
        urlDetail.append(BuildConfig.API_KEY);
        urlDetail.append(Constants.AND);
        urlDetail.append(Constants.LANGUAGE);
        urlDetail.append(Constants.VALUE_LANGUAGE);
        new GetMovieDetailAsyncTask(mListener).execute(urlDetail.toString());
    }

    @Override
    public void getMovieCast(int id) {
        StringBuilder urlCast = new StringBuilder(Constants.BLANK_STRING);
        urlCast.append(Constants.DOMAIN);
        urlCast.append(Constants.MOVIE_DETAIL);
        urlCast.append(id);
        urlCast.append(Constants.CREDITS);
        urlCast.append(Constants.QUESTION_MARK);
        urlCast.append(Constants.PARAM_KEY_API_KEY);
        urlCast.append(BuildConfig.API_KEY);
        new GetMovieCreditAsyncTask(mListener).execute(urlCast.toString());
    }

    @Override
    public void getVideo(int id) {
        StringBuilder urlVideo = new StringBuilder(Constants.BLANK_STRING);
        urlVideo.append(Constants.DOMAIN);
        urlVideo.append(Constants.MOVIE_DETAIL);
        urlVideo.append(id);
        urlVideo.append(Constants.VIDEOS);
        urlVideo.append(Constants.QUESTION_MARK);
        urlVideo.append(Constants.PARAM_KEY_API_KEY);
        urlVideo.append(BuildConfig.API_KEY);
        urlVideo.append(Constants.AND);
        urlVideo.append(Constants.LANGUAGE);
        urlVideo.append(Constants.VALUE_LANGUAGE);
        new GetVideoAsyncTask(mListener).execute(urlVideo.toString());
    }

    @Override
    public void getListMovieByGenre(int id) {
        StringBuilder urlList = new StringBuilder(Constants.BLANK_STRING);
        urlList.append(Constants.DOMAIN);
        urlList.append(Constants.LIST);
        urlList.append(id);
        urlList.append(Constants.QUESTION_MARK);
        urlList.append(Constants.PARAM_KEY_API_KEY);
        urlList.append(BuildConfig.API_KEY);
        urlList.append(Constants.AND);
        urlList.append(Constants.LANGUAGE);
        urlList.append(Constants.VALUE_LANGUAGE);
        new GetMovieByGenreAsyncTask(mListener).execute(urlList.toString());
    }

    @Override
    public void getGenres() {
        StringBuilder urlGenres = new StringBuilder(Constants.BLANK_STRING);
        urlGenres.append(Constants.DOMAIN);
        urlGenres.append(Constants.GENRES_LIST_PATH);
        urlGenres.append(Constants.QUESTION_MARK);
        urlGenres.append(Constants.PARAM_KEY_API_KEY);
        urlGenres.append(BuildConfig.API_KEY);
        urlGenres.append(Constants.AND);
        urlGenres.append(Constants.LANGUAGE);
        urlGenres.append(Constants.VALUE_LANGUAGE);
        new GetGenresAsyncTask(mListener).execute(urlGenres.toString());
    }
}
