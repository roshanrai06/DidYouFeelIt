package com.example.roshanrai.didyoufeelit;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SaveAsyncTaskFragment.SaveAsyncTaskFragmentCallBack {
    public static String SAVEASYNC_TASK_FRAGMENT_TAG = "save_asynce_ask_fragment";
    SaveAsyncTaskFragment mSaveAsyncTaskFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        mSaveAsyncTaskFragment = (SaveAsyncTaskFragment) fm.findFragmentByTag(SAVEASYNC_TASK_FRAGMENT_TAG);
        if (mSaveAsyncTaskFragment == null) {
            mSaveAsyncTaskFragment = new SaveAsyncTaskFragment();
            fm.beginTransaction().add(mSaveAsyncTaskFragment, SAVEASYNC_TASK_FRAGMENT_TAG).commit();
        }


    }

    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi(Event earthquake) {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(earthquake.title);

        TextView tsunamiTextView = (TextView) findViewById(R.id.number_of_people);
        tsunamiTextView.setText(getString(R.string.num_people_felt_it, earthquake.numOfPeople));

        TextView magnitudeTextView = (TextView) findViewById(R.id.perceived_magnitude);
        magnitudeTextView.setText(earthquake.perceivedStrength);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(Event s) {
        // Update the information displayed to the user.
        updateUi(s);
    }

    @Override
    public void onCanceled() {

    }

    @Override
    public void onProgressUpdate(int percent) {

    }
}
