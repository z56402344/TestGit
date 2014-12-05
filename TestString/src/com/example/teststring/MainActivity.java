package com.example.teststring;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_str;
	private ArrayList<String> list;
	private String regexp = "^\\{[a-zA-Z0-9]+\\)[a-zA-Z]+\\}&";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_str = (TextView) findViewById(R.id.tv_str);
		
		String str = "[Image:{3e94328b79d129d54d2c0842c6d939d6.jpg}]dfsafdsa[Image:{3e94328b79d129d54d2c0842c6d939d6.jpg}]} dsfsda f dsaf  最最2 [Image:{3e94328b79d129d54d2c0842c6d939d6.jpg}]最fdsa最3 [Image:{3e94328b79d129d54d2c0842c6d939d6.jpg}]";
		String[] split = str.split("\\[Image\\:");
		
		list = new ArrayList<String>();
		for (int i = 0; i < split.length; i++) {
			getImage(split, list, i);
		}
		
	}
	
	private void getImage(String[] split, ArrayList<String> list, int i) {
		if(split[i].startsWith("{") && split[i].endsWith("}") && split[i].matches(regexp)){
			list.add(split[i]);
			Log.i("dg", split[i]);
		}else if(split[i].contains("{") && split[i].contains("}]")){
			String[] split2 = split[i].split("]");
			for (int j = 0; j < split2.length; j++) {
				getImage(split2, list, j);
			}
			
		}else{
			list.add(split[i]);
			Log.i("dg", split[i]);
		}
		
	}


}
