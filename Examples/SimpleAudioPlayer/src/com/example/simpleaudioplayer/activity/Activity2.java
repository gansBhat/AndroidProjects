package com.example.simpleaudioplayer.activity;

import com.example.simpleaudioplayer.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Activity2 extends Activity implements OnClickListener {

	private static final String TAG = "Activity2";
	private static final int OPEN_FILE_CODE = 2280;
	private BottomBarController mBottomBarController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		mBottomBarController = new BottomBarController(getApplicationContext(),
				findViewById(R.id.musicControls));

		Button btn = (Button) findViewById(R.id.button1);
		Button  selectSongBtn = (Button) findViewById(R.id.selectSongBtn);
		
		btn.setOnClickListener(this);
		selectSongBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			Log.i(TAG, "starting 3rd activity");
			final Intent thirdActivity = new Intent(getApplicationContext(),
					Activity3.class);
			startActivity(thirdActivity);
			break;
		case R.id.selectSongBtn:
			Log.i(TAG, "Song select intent fired");
		 	final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		    intent.setType("*/*");
		    startActivityForResult(intent, OPEN_FILE_CODE);
		    break;
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case OPEN_FILE_CODE:
				//file opened
				String selectedFile = data.getDataString();
				Log.i(TAG, "Received song file:"+selectedFile);
				mBottomBarController.setSongFile(selectedFile);
				break;
			default:
				Log.i(TAG, "Unknown result");
		}
	}
}
