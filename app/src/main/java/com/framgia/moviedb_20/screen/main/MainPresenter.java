package com.framgia.moviedb_20.screen.main;

import com.framgia.moviedb_20.data.repository.MovieRepository;
import com.framgia.moviedb_20.data.repository.SearchRepository;

public class MainPresenter implements MainContract.Presenter {

    private MovieRepository mMovieRepository;
    private SearchRepository mSearchRepository;
    private MainContract.View mView;


    public MainPresenter(MovieRepository movieRepository, SearchRepository searchRepository) {
        mMovieRepository = movieRepository;
        mSearchRepository = searchRepository;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void loadGenres() {
        mMovieRepository.getGenres();
    }

}