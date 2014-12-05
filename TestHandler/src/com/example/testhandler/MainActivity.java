package com.example.testhandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startThreadOne();
//		startThreadTwo();
	}
	
	int m = 100;
	int n = 10;
	private void startThreadTwo() {
		Message msg = new Message();
		msg.what = 1;
		Bundle bundle = new Bundle();
		for (int i = 0; i < 10; i++) {
			
			bundle.putInt("num", m++);
			msg.setData(bundle);
			if(!handler.hasMessages(1)){
				handler.sendMessage(msg);
			}
		}
		
	}

	private void startThreadOne() {
		Message msg = new Message();
		msg.what = 2;
		Bundle bundle = new Bundle();
		for (int i = 0; i < 10; i++) {
			
			bundle.putInt("num", n++);
			msg.setData(bundle);
			if(!handler.hasMessages(2)){
				handler.sendMessage(msg);
			}
		}
		
	}

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			Bundle bundle = msg.getData();
			switch (msg.what) {
			case 1:
				Log.i("dg", ">>>m= "+bundle.getInt("num"));
				break;
			case 2:
				Log.i("dg", ">>>n= "+bundle.getInt("num"));
				break;

			default:
				break;
			}
		};
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
