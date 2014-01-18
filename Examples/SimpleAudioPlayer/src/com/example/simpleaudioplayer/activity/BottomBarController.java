package com.example.simpleaudioplayer.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.simpleaudioplayer.R;
import com.example.simpleaudioplayer.services.MediaPlayerService;

public class BottomBarController implements OnClickListener {
	
	private static final String TAG = "BottomBarController";
	
	private Context mContext;

	public BottomBarController(Context context, View musicControls) {
		mContext = context;
		Log.i(TAG, "Setting up bottom bar controller");
		ImageButton musicPlayBtn = (ImageButton)musicControls.findViewById(R.id.musicPlayBtn);
		ImageButton musicStopBtn = (ImageButton)musicControls.findViewById(R.id.musicStopBtn);
		musicPlayBtn.setOnClickListener(this);
		musicStopBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.musicPlayBtn:
				Log.i(TAG, "Play pressed");
				final Intent playIntent = new Intent(mContext, MediaPlayerService.class);
				playIntent.putExtra(MediaPlayerService.PLAY_MUSIC, true);
				mContext.startService(playIntent);
				
			break;
		case R.id.musicStopBtn:
				Log.i(TAG, "Stop pressed");
				final Intent stopIntent = new Intent(mContext, MediaPlayerService.class);
				stopIntent.putExtra(MediaPlayerService.STOP_MUSIC, true);
				mContext.startService(stopIntent);
			break;
		}
	}
	
	

}
