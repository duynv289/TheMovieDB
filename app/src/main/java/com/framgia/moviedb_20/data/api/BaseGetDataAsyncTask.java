package com.framgia.moviedb_20.data.api;


import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class BaseGetDataAsyncTask<T> extends AsyncTask<String, Void, List<T>> {
    private static final String REQUEST_METHOD = "GET";

    private Exception mException;
    private OnGetDataListener mListener;

    public BaseGetDataAsyncTask(OnGetDataListener listener) {
        mListener = listener;
    }

    protected abstract List<T> convertData(String strData) throws JSONException;

    @Override
    protected List<T> doInBackground(String... strings) {
        String url = strings[0];
        String strData = null;
        try {
            strData = getDataFromApi(url);
            return convertData(strData);
        } catch (IOException | JSONException e) {
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<T> result) {
        super.onPostExecute(result);
        if (mListener == null) {
            return;
        }
        if (mException == null) {
            mListener.onGetDataSuccess(result);
        } else {
            mListener.onGetDataFailure(mException);
        }
    }

    /**
     * Get data from api using http url connection
     *
     * @param urlPath : API path
     * @return : String data
     * @throws IOException : Connect exception
     */
    public String getDataFromApi(String urlPath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(REQUEST_METHOD);

        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        bufferedReader.close();
        connection.disconnect();
        return stringBuilder.toString();
    }

    /**
     * Get data callback
     *
     * @param <T>
     */
    public interface OnGetDataListener<T> {
        void onGetDataSuccess(List<T> data);

        void onGetDataFailure(Exception e);
    }
}
