package com.framgia.moviedb_20.data.source;

public interface CastDataSource {
    interface LocalDataSource {

    }

    interface RemoteDataSource {
        void getCastDetail(int id);

        void getCastCredit(int id);
    }
}
