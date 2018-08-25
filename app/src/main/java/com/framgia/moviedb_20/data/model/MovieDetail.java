package com.framgia.moviedb_20.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieDetail extends BaseMovie {

    private BaseMovie mBaseMovie;
    private List<Genre> mGenres;
    private List<Producer> mProducers;

    public MovieDetail(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        mGenres = new ArrayList<>();
        JSONArray genres = jsonObject.optJSONArray(JsonKey.GENRE);
        for (int i = 0; i < genres.length(); i++) {
            JSONObject genreObject = genres.optJSONObject(i);
            mGenres.add(new Genre(genreObject));
        }
        JSONArray producers = jsonObject.optJSONArray(JsonKey.PRODUCER);
        mProducers = new ArrayList<>();
        for (int j = 0; j < producers.length(); j++) {
            JSONObject producerObject = producers.optJSONObject(j);
            mProducers.add(new Producer(producerObject));
        }
    }

    public MovieDetail(int id, String title, String posterPath) {
        super(id, title, posterPath);
    }

    public BaseMovie getBaseMovie() {
        return mBaseMovie;
    }

    public void setBaseMovie(BaseMovie baseMovie) {
        mBaseMovie = baseMovie;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public List<Producer> getProducers() {
        return mProducers;
    }

    public void setProducers(List<Producer> producers) {
        mProducers = producers;
    }

    @Override
    public String toString() {
        return "MovieDetail{" + super.toString() +
                ", mGenres=" + mGenres +
                ", mProducers=" + mProducers +
                '}';
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String GENRE = "genres";
        public static final String PRODUCER = "production_companies";
    }
}
