package com.framgia.moviedb_20.screen.detail.movie;

import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.Genre;
import com.framgia.moviedb_20.data.model.MovieCast;
import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.model.Producer;
import com.framgia.moviedb_20.data.repository.FavoriteRepository;
import com.framgia.moviedb_20.data.repository.MovieRepository;
import com.framgia.moviedb_20.utils.Constants;

import java.util.List;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private static final String STRING_COMMA = ", ";
    private MovieDetailContract.View mView;
    private MovieRepository mMovieRepository;
    private FavoriteRepository mFavoriteRepository;

    public MovieDetailPresenter(MovieRepository movieRepository, FavoriteRepository favoriteRepository) {
        mMovieRepository = movieRepository;
        mFavoriteRepository = favoriteRepository;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(MovieDetailContract.View view) {
        mView = view;
    }


    @Override
    public void handlePlayClick(int movieId, boolean iPlay) {
        mMovieRepository.getVideo(movieId);
    }

    @Override
    public void loadGenres(List<Genre> genreList) {
        StringBuilder strName = new StringBuilder(Constants.BLANK_STRING);
        strName.append(Constants.STRING_COLON);
        for (int i = 0; i < genreList.size(); i++) {
            strName.append(genreList.get(i).getName());
            if (i == genreList.size() - 1) break;
            strName.append(STRING_COMMA);
        }
        mView.showGenres(strName.toString());
    }

    @Override
    public void loadCastName(List<MovieCast> movieCasts) {
        StringBuilder strName = new StringBuilder(Constants.BLANK_STRING);
        strName.append(Constants.STRING_COLON);
        for (int i = 0; i < movieCasts.size(); i++) {
            strName.append(movieCasts.get(i).getCastName());
            if (i == movieCasts.size() - 1) break;
            strName.append(STRING_COMMA);
        }
        mView.showCasts(strName.toString());
    }

    @Override
    public void loadMovies(int id) {
        mMovieRepository.getMovieDetail(id);
        mMovieRepository.getMovieCast(id);
    }

    @Override
    public void loadProducers(List<Producer> producerList) {
        StringBuilder strName = new StringBuilder(Constants.BLANK_STRING);
        strName.append(Constants.STRING_COLON);
        for (int i = 0; i < producerList.size(); i++) {
            strName.append(producerList.get(i).getName());
            if (i == producerList.size() - 1) break;
            strName.append(STRING_COMMA);
        }
        mView.showProducers(strName.toString());
    }

    @Override
    public void addToFavorite(BaseMovie baseMovie) {
        mFavoriteRepository.addMovie(baseMovie);
    }

    @Override
    public void removeFromFavorite(int id) {
        mFavoriteRepository.removeMovie(id);
    }

    @Override
    public boolean checkMovieInFavorite(BaseMovie baseMovie) {
        return mFavoriteRepository.checkMovieInFavorite(baseMovie);
    }
}
