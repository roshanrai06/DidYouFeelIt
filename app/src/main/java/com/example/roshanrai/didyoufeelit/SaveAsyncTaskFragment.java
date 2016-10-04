package com.example.roshanrai.didyoufeelit;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * Created by roshan.rai on 04-10-2016.
 */

public class SaveAsyncTaskFragment extends Fragment {
    SaveAsyncTaskFragmentCallBack mSaveAsyncTaskFragmentCallBack;
    FragmentAsyncTask mFragmentAsyncTask;
    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    interface SaveAsyncTaskFragmentCallBack {
        void onPreExecute();

        void onPostExecute(Event s);

        void onCanceled();

        void onProgressUpdate(int percent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSaveAsyncTaskFragmentCallBack = (SaveAsyncTaskFragmentCallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSaveAsyncTaskFragmentCallBack = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mFragmentAsyncTask = new FragmentAsyncTask();
        mFragmentAsyncTask.execute();
    }

    private class FragmentAsyncTask extends AsyncTask<String, Integer, Event> {

        @Override
        protected Event doInBackground(String... params) {
            // Perform the HTTP request for earthquake data and process the response.
            Event earthquake = Utils.fetchEarthquakeData(USGS_REQUEST_URL);
            return earthquake;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if (mSaveAsyncTaskFragmentCallBack != null) {
                mSaveAsyncTaskFragmentCallBack.onCanceled();

            }
        }

        @Override
        protected void onPostExecute(Event s) {
            super.onPostExecute(s);
            if (mSaveAsyncTaskFragmentCallBack != null) {
                mSaveAsyncTaskFragmentCallBack.onPostExecute(s);
            }
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mSaveAsyncTaskFragmentCallBack != null) {
                mSaveAsyncTaskFragmentCallBack.onPreExecute();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mSaveAsyncTaskFragmentCallBack != null) {
                mSaveAsyncTaskFragmentCallBack.onProgressUpdate(values[0]);
            }
        }
    }
}
