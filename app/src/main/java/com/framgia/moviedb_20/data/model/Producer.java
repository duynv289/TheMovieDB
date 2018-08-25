package com.framgia.moviedb_20.data.model;

import org.json.JSONObject;

public class Producer {
    private String mHeadquarter;
    private String mHomepage;
    private int mId;
    private String mLogoPath;
    private String mName;
    private String mOriginCountry;

    public Producer() {
    }

    public Producer(JSONObject jsonObject) {
        mId = jsonObject.optInt(JsonKey.ID);
        mName = jsonObject.optString(JsonKey.NAME);
        mHeadquarter = jsonObject.optString(JsonKey.HEADQUARTERS);
        mHomepage = jsonObject.optString(JsonKey.HOMEPAGE);
        mLogoPath = jsonObject.optString(JsonKey.LOGO_PATH);
        mOriginCountry = jsonObject.optString(JsonKey.ORIGIN_COUNTRY);
    }


    public String getHeadquarter() {
        return mHeadquarter;
    }

    public void setHeadquarter(String headquarter) {
        mHeadquarter = headquarter;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLogoPath() {
        return mLogoPath;
    }

    public void setLogoPath(String logoPath) {
        mLogoPath = logoPath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOriginCountry() {
        return mOriginCountry;
    }

    public void setOriginCountry(String originCountry) {
        mOriginCountry = originCountry;
    }


    @Override
    public String toString() {
        return "Producer{" +
                "mHeadquarter='" + mHeadquarter + '\'' +
                ", mHomepage='" + mHomepage + '\'' +
                ", mId=" + mId +
                ", mLogoPath='" + mLogoPath + '\'' +
                ", mName='" + mName + '\'' +
                ", mOriginCountry='" + mOriginCountry + '\'' +
                '}';
    }

    public static class JsonKey extends BaseJsonKey {
        public static final String HEADQUARTERS = "headquarters";
        public static final String HOMEPAGE = "homepage";
        public static final String LOGO_PATH = "logo_path";
        public static final String ORIGIN_COUNTRY = "origin_country";

    }
}
