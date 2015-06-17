package com.example.learn_asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ProgressDialog progressDialog;
	private int percent = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setMax(100);
		new DownloadTask().execute();
	}
	
	private class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
		
		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				while (true) {
					int downloadPercent = doDownload();
					publishProgress(downloadPercent);
					if(downloadPercent >= 100) {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			progressDialog.setMessage("目前下載進度:" + values[0] + "%");
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			progressDialog.dismiss();
			if(result) {
				Toast.makeText(MainActivity.this, "下載成功", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private int doDownload(){

//		progressDialog.setProgress(percent);
//		percent ++;
		return percent;
		
	}
}
