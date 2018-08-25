package com.framgia.moviedb_20.data.model;

import org.json.JSONObject;

public class SearchResult {
    private String mMediaType;
    private String mName;
    private String mTitle;
    private String mPosterPath;
    private String mProfilePath;
    private int mId;

    public SearchResult(JSONObject jsonObject) {
        mId = jsonObject.optInt(JsonKey.ID);
        mName = jsonObject.optString(JsonKey.NAME);
        mProfilePath = jsonObject.optString(JsonKey.PROFILE_PATH);
        mTitle = jsonObject.optString(JsonKey.TITLE);
        mMediaType = jsonObject.optString(JsonKey.MEDIA_TYPE);
        mPosterPath = jsonObject.optString(JsonKey.POSTER_PATH);
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String MEDIA_TYPE = "media_type";
        public static final String PROFILE_PATH = "profile_path";
        public static final String POSTER_PATH = "poster_path";
        public static final String TITLE = "title";
    }
}
