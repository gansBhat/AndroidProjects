package com.example.simpleaudioplayer.activity;

import com.example.simpleaudioplayer.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Activity3 extends Activity implements OnClickListener {

	private static final String TAG = "Activity3";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity3);
		new BottomBarController(getApplicationContext(), findViewById(R.id.musicControls));
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Log.i(TAG, "starting 4th activity");
			final Intent fourthActivity = new Intent(getApplicationContext(),
					Activity4.class);
			startActivity(fourthActivity);
			break;
		}
	}


	

}
