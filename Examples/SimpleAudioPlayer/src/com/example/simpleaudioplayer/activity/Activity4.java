package com.example.simpleaudioplayer.activity;

import com.example.simpleaudioplayer.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Activity4 extends Activity implements OnClickListener {

	private static final String TAG = "Activity4";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity4);
		new BottomBarController(getApplicationContext(), findViewById(R.id.musicControls));
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Log.i(TAG, "starting 5th activity");
			final Intent fifthActivity = new Intent(getApplicationContext(),
					Activity5.class);
			startActivity(fifthActivity);
			break;
		}
	}


	
}
