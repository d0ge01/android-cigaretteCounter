package it.d0ge01.cigarettecounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Settings extends Activity {
	private MainActivity activity;
	private Button bt1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_main);
		
	}
	
	public void setMain(MainActivity actv) {
		this.activity = actv;
	}
}
