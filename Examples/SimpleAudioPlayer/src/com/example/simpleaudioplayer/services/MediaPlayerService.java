package com.example.simpleaudioplayer.services;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MediaPlayerService extends Service {

	public static final String PLAY_MUSIC = "PLAY_MUSIC";
	public static final String STOP_MUSIC = "STOP_MUSIC";
	private static final String TAG = "MediaPlayerService";
	private MediaPlayer mPlayer;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mPlayer = new MediaPlayer();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		if(intent!=null && intent.hasExtra(PLAY_MUSIC)) {
			//start music play
			Log.i(TAG, "Playing music");
			playMusic();
		} else if(intent!=null && intent.hasExtra(STOP_MUSIC)){
			//stop music
			Log.i(TAG, "Stopping music");
			stopMusic();
		}
		return START_NOT_STICKY;
	}
	
	
	private void stopMusic() {
		if (mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = new MediaPlayer();
        }
	}

	public void playMusic() {
	    try {

	        if (mPlayer.isPlaying()) {
	            mPlayer.stop();
	            mPlayer.release();
	            mPlayer = new MediaPlayer();
	        }
	        AssetFileDescriptor descriptor = getAssets().openFd("music_track.mp3");
	        mPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
	        descriptor.close();

	        mPlayer.prepare();
	        mPlayer.setVolume(1f, 1f);
	        mPlayer.setLooping(true);
	        mPlayer.start();
	    } catch (Exception e) {
	    	
	    }
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mPlayer.stop();
		mPlayer.release();
	}
	
}
