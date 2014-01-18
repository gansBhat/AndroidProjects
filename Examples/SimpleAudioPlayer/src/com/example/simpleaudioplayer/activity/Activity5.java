package com.example.simpleaudioplayer.activity;

import com.example.simpleaudioplayer.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Activity5 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity5);
		new BottomBarController(getApplicationContext(), findViewById(R.id.musicControls));
	}


	

}
