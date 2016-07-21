package me.hiroaki.bottomsheettest;

import android.graphics.Point;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private float preY;
	private int winW, winH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View decor = this.getWindow().getDecorView();
		decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

		Display display = getWindowManager().getDefaultDisplay();
		Point real = new Point(0, 0);
		display.getRealSize(real);
		winW = real.x;
		winH = real.y;
		Log.i("MainActivity : ", "画面幅 = " + winW);
		Log.i("MainActivity : ", "画面高さ = " + winH);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			preY = event.getY();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (preY > event.getY() && preY > winH - 100) {
				NestedScrollView bottomSheet = (NestedScrollView) findViewById(R.id.bottomSheet);
				BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
				behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
			}
		}
		return false;
	}

}
