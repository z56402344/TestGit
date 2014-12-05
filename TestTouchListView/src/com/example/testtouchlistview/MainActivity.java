package com.example.testtouchlistview;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity  {

	private ListView lv_word;
	private RelativeLayout rl_word;
	private LinearLayout ll_info;
	private TextView tv_text;
	private ArrayAdapter<String> mAdapter;
	private FrameLayout fl_fragment;
	
	private View view;

	private LinkedList<String> mWords;
	private String[] mWord = { "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mains);
		initView();
		initDate();
		setListener();
	}

	private void initView() {
		lv_word = (ListView) findViewById(R.id.lv_word);
		rl_word = (RelativeLayout) findViewById(R.id.rl_word);
		tv_text = (TextView) findViewById(R.id.tv_text);
		fl_fragment =(FrameLayout) findViewById(R.id.fl_fragment);
		ll_info = (LinearLayout) findViewById(R.id.ll_info);
		
		view = View.inflate(this, R.layout.item_word_info, null);
		view.setVisibility(View.INVISIBLE);
		rl_word.addView(view);
	}

	private void initDate() {
		mWords = new LinkedList<String>();

		for (int i = 0; i < mWord.length; i++) {
			mWords.add(mWord[i]);
		}
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mWords);
		lv_word.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		int count = lv_word.getLastVisiblePosition()-lv_word.getFirstVisiblePosition()+1;
		lv_word.setSelection(mWords.size()-3);
		tv_text.setText(mWords.get(mWords.size()-1));
		mWords.remove(mWords.get(mWords.size()-1));
		mAdapter.notifyDataSetChanged();
	}

	private void setListener() {
		 lv_word.setFocusable(false);
		 lv_word.setEnabled(false);
		// lv_word.setOnTouchListener(this);
	}

	int startX = 0;
	int startY = 0;
	int dX = 0;
	int dY = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) event.getRawX();
			startY = (int) event.getRawY();
			Log.i("dg", "startX>>" + startX + "  startY>>" + startY);
			break;
		case MotionEvent.ACTION_MOVE:

			dX = ((int) event.getRawX()) - startX;
			dY = ((int) event.getRawY()) - startY;
			Log.i("dg", "dX>>" + dX + "  dY>>" + dY);
			break;
		case MotionEvent.ACTION_UP:
			if (dY > 0) {
				// TODO 下滑事件
				slideDown();

			} else {
				// TODO 上滑事件
//				slideUp();
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void slideUp() {
		Log.i("dg",
				"上滑事件1+lv_word.getFirstVisiblePosition()>>>"
						+ lv_word.getFirstVisiblePosition());
		if (mWords.size() > lv_word.getLastVisiblePosition()) {
			lv_word.setSelection(lv_word.getFirstVisiblePosition() + 1);
			Log.i("dg", "上滑事件2");
		}

	}

	private int top = 0;
	private int bootm = 0;
	private int i = 1;

	private void slideDown() {
		Log.i("dg",
				"下滑事件1+lv_word.getLastVisiblePosition()>>>"
						+ lv_word.getLastVisiblePosition());
		if (0 < lv_word.getFirstVisiblePosition()) {
//			TranslateAnimation animation = new TranslateAnimation(0, 0, lv_word.getHeight(), lv_word.getHeight()+15);
			TranslateAnimation animation = new TranslateAnimation(0, 0, -lv_word.getHeight(), 0);
			view.setVisibility(View.VISIBLE);
			animation.setDuration(500);
//			view.setAlpha(0.5f);
			ll_info.setAnimation(animation);
			view.setVisibility(View.INVISIBLE);
			AlphaAnimation alpha = new AlphaAnimation(0, 1);
			alpha.setDuration(500);
			ll_info.setAnimation(alpha);
			
			tv_text.setText(mWords.get(lv_word.getLastVisiblePosition()));
			mWords.remove(lv_word.getLastVisiblePosition());
			mAdapter.notifyDataSetChanged();
			Log.i("dg", "下滑事件2");
			
		} else if (0 == lv_word.getFirstVisiblePosition()) {
			tv_text.setText(mWords.get(lv_word.getLastVisiblePosition()));
			mWords.remove(lv_word.getLastVisiblePosition());
			mAdapter.notifyDataSetChanged();
			if(mWords.size()==1){
				mWords.addFirst("");
				mAdapter.notifyDataSetChanged();
			}
		}
	}

//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			startX = (int) event.getRawX();
//			startY = (int) event.getRawY();
//			Log.i("dg", "startX>>" + startX + "  startY>>" + startY);
//			break;
//		case MotionEvent.ACTION_MOVE:
//
//			dX = ((int) event.getRawX()) - startX;
//			dY = ((int) event.getRawY()) - startY;
//			Log.i("dg", "dX>>" + dX + "  dY>>" + dY);
//			break;
//		case MotionEvent.ACTION_UP:
//			if (dY > 0) {
//				// TODO 下滑事件
////				slideDown();
//
//			} else {
//				// TODO 上滑事件
////				slideUp();
//			}
//			break;
//
//		default:
//			break;
//		}
//		return false;
//	}

}
