package com.framgia.moviedb_20.screen.detail.producer;

import com.framgia.moviedb_20.data.model.Producer;
import com.framgia.moviedb_20.screen.BasePresenter;

import java.util.List;

public interface ProducerDetailContract {
    interface View {

        void showProducerLogo(String logoPath);

        void showName(String name);

        void showHeadquarters(String headquarters);

        void showHomPage(String homePage);

        void showOriginCountry(String originCountry);

    }

    interface Presenter extends BasePresenter<View> {
        void getProducer(int id);

        void fetchData(List<Producer> data);

        void getMovieDetail(String id);

    }
}
