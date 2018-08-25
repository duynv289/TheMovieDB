package com.framgia.moviedb_20.data.source;

public interface SearchDataSource {
    interface LocalDataSource {

    }

    interface RemoteDataSource {
        void getResult(String query);
    }
}
