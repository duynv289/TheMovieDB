package com.framgia.moviedb_20.data.model;

import org.json.JSONObject;

public class MovieCast {
    private int mCastId;
    private String mCastName;
    private String mCastProfilePath;

    public MovieCast(JSONObject jsonObject) {
        mCastId = jsonObject.optInt(BaseJsonKey.ID);
        mCastName = jsonObject.optString(BaseJsonKey.NAME);
        mCastProfilePath = jsonObject.optString(JsonKey.PROFILE_PATH);
    }

    public int getCastId() {
        return mCastId;
    }

    public void setCastId(int castId) {
        mCastId = castId;
    }

    public String getCastName() {
        return mCastName;
    }

    public void setCastName(String castName) {
        mCastName = castName;
    }

    public String getCastProfilePath() {
        return mCastProfilePath;
    }

    public void setCastProfilePath(String castProfilePath) {
        mCastProfilePath = castProfilePath;
    }

    public class JsonKey extends BaseJsonKey {
        private static final String PROFILE_PATH = "profile_path";
    }
}
