package com.example.simpleaudioplayer.services;

import java.io.IOException;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.example.simpleaudioplayer.R;
import com.example.simpleaudioplayer.activity.Activity1;

public class MediaPlayerService extends Service implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

	public static final String PLAY_MUSIC = "PLAY_MUSIC";
	public static final String STOP_MUSIC = "STOP_MUSIC";
	public static final String SELECTED_SONG = "SONG_NAME";

	private static final String TAG = "MediaPlayerService";
	private static final int NOTIFICATION_ID = 8862;

	private MediaPlayer mMediaPlayer;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if (intent != null && intent.hasExtra(PLAY_MUSIC)) {
			// start music play
			String songName = intent.getStringExtra(SELECTED_SONG);
			Log.i(TAG, "Playing music , song received is:" + songName);
			runAsForegroundService(songName);
			mMediaPlayer =new MediaPlayer();
			mMediaPlayer.setOnPreparedListener(this);
			try {
				mMediaPlayer.setDataSource(getApplicationContext(), Uri.parse(songName));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			mMediaPlayer.prepareAsync(); // prepare async to not block main
		} else if (intent != null && intent.hasExtra(STOP_MUSIC)) {
			Log.i(TAG, "Stopping music");
			stopForeground(true);
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		}
		return START_NOT_STICKY;
	}

	private void runAsForegroundService(String songName) {
		PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),
				0, new Intent(getApplicationContext(), Activity1.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		Notification notification = new Notification();
		notification.tickerText = songName;
		notification.icon = R.drawable.ic_launcher;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.setLatestEventInfo(getApplicationContext(),
				"MusicPlayerSample", "Playing: " + songName, pi);
		startForeground(NOTIFICATION_ID, notification);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMediaPlayer.stop();
		mMediaPlayer.release();
	}

	@Override
	public void onPrepared(MediaPlayer player) {
		Log.i(TAG, "Music prepared, starting to play the song");
		player.start();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// ... react appropriately ...
		mMediaPlayer.stop();
		mMediaPlayer.release();
		mMediaPlayer = null;
		// The MediaPlayer has moved to the Error state, must be reset!
		return true;
	}

}
