package com.framgia.moviedb_20.screen.detail.movie;

import com.framgia.moviedb_20.data.model.BaseMovie;
import com.framgia.moviedb_20.data.model.Genre;
import com.framgia.moviedb_20.data.model.MovieCast;
import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.model.Producer;
import com.framgia.moviedb_20.screen.BasePresenter;

import java.util.List;

public interface MovieDetailContract {
    interface View {

        void showBackdrop(String backdropPath);

        void showFilmName(String filmName);

        void showReleaseDate(String releaseDate);

        void showRunTime(int runTime);

        void showVoteAverage(String voteAverage);

        void showOverview(String overview);

        void showGenres(String genre);

        void showCasts(String cast);

        void showProducers(String producers);

        void showVideo(String movieKey);

        void showButton();
    }

    interface Presenter extends BasePresenter<View> {

        void handlePlayClick(int movieId, boolean iPlay);

        void loadGenres(List<Genre> genreList);

        void loadCastName(List<MovieCast> movieCasts);

        void loadMovies(int id);

        void loadProducers(List<Producer> producerList);

        void addToFavorite(BaseMovie baseMovie);

        void removeFromFavorite(int id);

        boolean checkMovieInFavorite (BaseMovie baseMovie);
    }
}
