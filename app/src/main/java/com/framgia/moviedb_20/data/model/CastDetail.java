package com.framgia.moviedb_20.data.model;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CastDetail {
    private String mBirthday;
    private int mId;
    private String mName;
    private String mBiography;
    private String mPlaceOfBirth;
    private String mProfilePath;

    public CastDetail() {
    }

    public CastDetail(JSONObject jsonObject) throws JSONException {
        mId = jsonObject.optInt(JsonKey.ID);
        mName = jsonObject.optString(JsonKey.NAME);
        mBirthday = jsonObject.optString(JsonKey.BIRTHDAY);
        mBiography = jsonObject.optString(JsonKey.BIOGRAPHY);
        mPlaceOfBirth = jsonObject.optString(JsonKey.PLACE_OF_BIRTH);
        mProfilePath = jsonObject.optString(JsonKey.PROFILE_PATH);
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
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

    public String getBiography() {
        return mBiography;
    }

    public void setBiography(String biography) {
        mBiography = biography;
    }

    public String getPlaceOfBirth() {
        return mPlaceOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        mPlaceOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    @Override
    public String toString() {
        return "CastDetail{" +
                ", mBirthday='" + mBirthday + '\'' +
                ", mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mBiography='" + mBiography + '\'' +
                ", mPlaceOfBirth='" + mPlaceOfBirth + '\'' +
                ", mProfilePath='" + mProfilePath + '\'' +
                '}';
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String BIRTHDAY = "birthday";
        public static final String BIOGRAPHY = "biography";
        public static final String PLACE_OF_BIRTH = "place_of_birth";
        public static final String PROFILE_PATH = "profile_path";
    }
}
