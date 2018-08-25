package com.framgia.moviedb_20.screen.genre;

import com.framgia.moviedb_20.data.repository.MovieRepository;

public class GenrePresenter implements GenreContract.Presenter {

    private GenreContract.View mView;
    private MovieRepository mMovieRepository;

    public GenrePresenter(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(GenreContract.View view) {
        mView = view;
    }

    @Override
    public void getListMovieByGenre(int id) {
        mMovieRepository.getListMovieByGenre(id);
    }
}
