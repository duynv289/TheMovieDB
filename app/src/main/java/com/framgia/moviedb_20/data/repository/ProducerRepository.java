package com.framgia.moviedb_20.data.repository;

import com.framgia.moviedb_20.data.source.ProducerDataSource;

public class ProducerRepository implements ProducerDataSource.RemoteDataSource, ProducerDataSource.LocalDataSource {

    private ProducerDataSource.RemoteDataSource mRemoteDataSource;

    public ProducerRepository(ProducerDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getProducer(int id) {
        mRemoteDataSource.getProducer(id);
    }

    @Override
    public void getMovieDetail(String id) {
        mRemoteDataSource.getMovieDetail(id);
    }
}
