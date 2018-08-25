package com.framgia.moviedb_20.data.repository;

import com.framgia.moviedb_20.data.source.CastDataSource;
import com.framgia.moviedb_20.data.source.remote.CastRemoteDataSource;

public class CastRepository implements CastDataSource.RemoteDataSource,CastDataSource.LocalDataSource {
    private CastRemoteDataSource mCastRemoteDataSource;

    public CastRepository(CastRemoteDataSource castRemoteDataSource) {
        mCastRemoteDataSource = castRemoteDataSource;
    }

    @Override
    public void getCastDetail(int id) {
        mCastRemoteDataSource.getCastDetail(id);
    }

    @Override
    public void getCastCredit(int id) {
        mCastRemoteDataSource.getCastCredit(id);
    }
}
