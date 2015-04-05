package com.thomasabassi.primenotprime;

import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FullscreenActivity extends ChartboostActivity {
	
	final static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
		31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
		101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167};
	int curr_number = 169;
	final static String appId = "$put_your";
	final static String appSignature = "$put_your";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		Chartboost.startWithAppId(this, appId, appSignature);
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);

		findViewById(R.id.prime).setOnClickListener(
				mPrimeListener);
		
		findViewById(R.id.notprime).setOnClickListener(
				mNotPrimeListener);
	}

	View.OnClickListener mPrimeListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(in(primes, curr_number)) {
				curr_number--;
				if(curr_number == 1) {
		        	TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
		        	contentView.setText(R.string.end);
		    		View controlsView = findViewById(R.id.fullscreen_content_controls);
		    		controlsView.setVisibility(View.GONE);
				} else {
		        	TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
		        	contentView.setText(Integer.toString(curr_number));
				}
			} else {
				new AlertDialog.Builder(FullscreenActivity.this)
			    .setTitle("Wrong Wrong Wrong")
			    .setMessage("Watch an ad to continue...")
			    .setPositiveButton(R.string.watch, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	Chartboost.showRewardedVideo(CBLocation.LOCATION_HOME_SCREEN);
			        	Chartboost.cacheRewardedVideo(CBLocation.LOCATION_HOME_SCREEN);
			        }
			     })
			    .setNegativeButton(R.string.restart, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	curr_number = 169;
			        	TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
			        	contentView.setText(Integer.toString(curr_number));
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			    .setCancelable(false)
			    .show();
			}
		}
	};

	View.OnClickListener mNotPrimeListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(in(primes, curr_number)) {
				new AlertDialog.Builder(FullscreenActivity.this)
			    .setTitle("Wrong Wrong Wrong")
			    .setMessage("Watch an ad to continue...")
			    .setPositiveButton(R.string.watch, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	Chartboost.showRewardedVideo(CBLocation.LOCATION_HOME_SCREEN);
			        	Chartboost.cacheRewardedVideo(CBLocation.LOCATION_HOME_SCREEN);
			        }
			     })
			    .setNegativeButton(R.string.restart, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	curr_number = 169;
			        	TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
			        	contentView.setText(Integer.toString(curr_number));
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			    .setCancelable(false)
			    .show();
			} else {
				curr_number--;
				if(curr_number == 1) {
		        	TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
		        	contentView.setText(R.string.end);
		    		View controlsView = findViewById(R.id.fullscreen_content_controls);
		    		controlsView.setVisibility(View.GONE);
				} else {
		        	TextView contentView = (TextView) findViewById(R.id.fullscreen_content);
		        	contentView.setText(Integer.toString(curr_number));
				}
			}
		}
	};
	
	public boolean in(int[] primes, int n) {
		for(int i = 0; i < primes.length; i++) {
			if(n == primes[i]) {
				return true;
			}
		}
		return false;
	}
}
