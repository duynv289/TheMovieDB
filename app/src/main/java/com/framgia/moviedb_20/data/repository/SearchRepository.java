package com.framgia.moviedb_20.data.repository;

import com.framgia.moviedb_20.data.source.SearchDataSource;
import com.framgia.moviedb_20.data.source.remote.SearchRemoteDataSource;

public class SearchRepository implements SearchDataSource.RemoteDataSource,SearchDataSource.LocalDataSource {

    private SearchDataSource.RemoteDataSource mRemoteDataSource;

    public SearchRepository(SearchDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getResult(String query) {
        mRemoteDataSource.getResult(query);
    }
}
