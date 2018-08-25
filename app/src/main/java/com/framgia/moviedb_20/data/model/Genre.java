package com.framgia.moviedb_20.data.model;

import org.json.JSONObject;

public class Genre extends BaseJsonKey {
    private int mId;
    private String mName;

    public Genre() {
    }

    public Genre(JSONObject jsonObject) {
        mId = jsonObject.optInt(BaseJsonKey.ID);
        mName = jsonObject.optString(BaseJsonKey.NAME);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }

}
