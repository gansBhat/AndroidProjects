package com.example.simpleaudioplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.simpleaudioplayer.R;

public class Activity1 extends Activity implements OnClickListener {

	private static final String TAG = "Activity1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity1);
		Button btn = (Button)findViewById(R.id.selectClientBtn);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.selectClientBtn:
			Log.i(TAG, "starting 2nd activity");
			final Intent secondActivity = new Intent(getApplicationContext(), Activity2.class);
			startActivity(secondActivity);
			break;
		}
	}
}
