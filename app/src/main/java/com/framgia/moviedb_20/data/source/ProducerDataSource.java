package com.framgia.moviedb_20.data.source;

public interface ProducerDataSource {
    interface LocalDataSource {

    }

    interface RemoteDataSource {
        void getProducer(int id);

        void getMovieDetail(String id);
    }
}
