package com.framgia.moviedb_20.data.repository;

import com.framgia.moviedb_20.data.model.MovieDetail;
import com.framgia.moviedb_20.data.source.MovieDataSource;

import java.util.List;

public class MovieRepository implements MovieDataSource.RemoteDataSource, MovieDataSource.LocalDataSource {

    private MovieDataSource.RemoteDataSource mMovieRemoteDataSource;

    public MovieRepository(MovieDataSource.RemoteDataSource movieRemoteDataSource) {
        mMovieRemoteDataSource = movieRemoteDataSource;
    }

    @Override
    public void getMovies(String category) {
        mMovieRemoteDataSource.getMovies(category);
    }

    @Override
    public void getMovieDetail(int id) {
        mMovieRemoteDataSource.getMovieDetail(id);
    }

    @Override
    public void getMovieCast(int id) {
        mMovieRemoteDataSource.getMovieCast(id);
    }

    @Override
    public void getVideo(int id) {
        mMovieRemoteDataSource.getVideo(id);
    }

    @Override
    public void getListMovieByGenre(int id) {
        mMovieRemoteDataSource.getListMovieByGenre(id);
    }

    @Override
    public void getGenres() {
        mMovieRemoteDataSource.getGenres();
    }

}
