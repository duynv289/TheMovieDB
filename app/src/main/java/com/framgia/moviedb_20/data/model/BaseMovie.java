package com.framgia.moviedb_20.data.model;

import org.json.JSONObject;

public class BaseMovie {
    private String mPosterPath;
    private String mBackdropPath;
    private int mId;
    private String mTitle;
    private String mOverview;
    private String mReleaseDate;
    private int mRunTime;
    private double mVoteAverage;

    public BaseMovie(JSONObject jsonObject) {
        mId = jsonObject.optInt(JsonKey.ID);
        mBackdropPath = jsonObject.optString(JsonKey.BACKDROP_PATH);
        mPosterPath = jsonObject.optString(JsonKey.POSTER_PATH);
        mTitle = jsonObject.optString(JsonKey.TITLE);
        mOverview = jsonObject.optString(JsonKey.OVERVIEW);
        mReleaseDate = jsonObject.optString(JsonKey.RELEASE_DATE);
        mRunTime = jsonObject.optInt(JsonKey.RUN_TIME);
        mVoteAverage = jsonObject.optDouble(JsonKey.VOTE_AVERAGE);
    }

    public BaseMovie(int id, String title, String posterPath) {
        mPosterPath = posterPath;
        mId = id;
        mTitle = title;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public int getRunTime() {
        return mRunTime;
    }

    public void setRunTime(int runTime) {
        mRunTime = runTime;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    @Override
    public String toString() {
        return "BaseMovie{" +
                "mPosterPath='" + mPosterPath + '\'' +
                ", mBackdropPath='" + mBackdropPath + '\'' +
                ", mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mOverview='" + mOverview + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mRunTime=" + mRunTime +
                ", mVoteAverage=" + mVoteAverage +
                '}';
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String POSTER_PATH = "poster_path";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String RUN_TIME = "runtime";
        public static final String VOTE_AVERAGE = "vote_average";
    }
}
