package com.framgia.moviedb_20.screen.main.home;

import com.framgia.moviedb_20.data.repository.MovieRepository;
import com.framgia.moviedb_20.utils.Constants;

public class HomePresenter implements HomeContract.Presenter {

    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;
    private MovieRepository mMovieRepository;
    private HomeContract.View mView;
    private int mTag;

    public HomePresenter(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void setAdapter(int tag) {
        switch (tag) {
            case INDEX_ZERO:
                mView.showUpComing();
                loadTopRated(INDEX_ONE);
                break;
            case INDEX_ONE:
                mView.showTopRated();
                loadPopular(INDEX_TWO);
                break;
            case INDEX_TWO:
                mView.showPopular();
                loadNowPlaying(INDEX_THREE);
                break;
            case INDEX_THREE:
                mView.showNowPlaying();
                break;
        }
    }

    @Override
    public void loadUpComing(int tag) {
        mView.showProgress();
        mTag = tag;
        mMovieRepository.getMovies(Constants.UPCOMING);
    }

    @Override
    public void loadPopular(int tag) {
        mTag = tag;
        mMovieRepository.getMovies(Constants.POPULAR);
    }

    @Override
    public void loadNowPlaying(int tag) {
        mTag = tag;
        mMovieRepository.getMovies(Constants.NOW_PLAYING);
    }

    @Override
    public void loadTopRated(int tag) {
        mTag = tag;
        mMovieRepository.getMovies(Constants.TOP_RATED);
    }
    public int getTag() {
        return mTag;
    }
}
