package com.framgia.moviedb_20.screen.detail.producer;

import com.framgia.moviedb_20.data.model.Producer;
import com.framgia.moviedb_20.data.repository.ProducerRepository;
import com.framgia.moviedb_20.utils.Constants;

import java.util.List;

public class ProducerDetailPresenter implements ProducerDetailContract.Presenter {

    private ProducerDetailContract.View mView;
    private ProducerRepository mProducerRepository;

    public ProducerDetailPresenter(ProducerRepository producerRepository) {
        mProducerRepository = producerRepository;
    }

    @Override
    public void getProducer(int id) {
        mProducerRepository.getProducer(id);
    }

    @Override
    public void fetchData(List<Producer> data) {
        Producer producer = data.get(0);
        mView.showProducerLogo(producer.getLogoPath());
        mView.showName(Constants.STRING_COLON + producer.getName());
        mView.showHeadquarters(Constants.STRING_COLON + producer.getHeadquarter());
        mView.showHomPage(Constants.STRING_COLON + producer.getHomepage());
        mView.showOriginCountry(Constants.STRING_COLON + producer.getOriginCountry());
    }

    @Override
    public void getMovieDetail(String id) {
        mProducerRepository.getMovieDetail(id);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(ProducerDetailContract.View view) {
        mView = view;
    }
}
