package com.example.customthinprogressbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		
		new CustomTask(progressBar).execute((Void[])null);
	}

	private static class CustomTask extends AsyncTask<Void, Integer, Void> {

		private static final String TAG = "CustomTask";
		private ProgressBar mProgressBar;
		
		CustomTask(ProgressBar progressBar) {
			mProgressBar = progressBar;
		}
		
		
		@Override
		protected Void doInBackground(Void... params) {
			int progress = 0;
			int increment = 1;
			while(progress<=100) {
				progress+=increment;
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					Log.e(TAG, "Thread interrupted");
				}
				publishProgress(progress);
			}
			//for 100%
			publishProgress(progress);
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			if(values!=null && values.length>0) {
				int progress = values[0];
				if(mProgressBar!=null) {
					mProgressBar.setProgress(progress);
				}
			}
		}
	}
	

}
