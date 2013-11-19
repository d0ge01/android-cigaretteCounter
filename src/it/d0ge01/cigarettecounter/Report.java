package it.d0ge01.cigarettecounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class Report extends Activity{
	
	Spinner sp;
	TextView tv;
	PrefsMan prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);    
		
		sp = (Spinner) findViewById(R.id.spinner);
		tv = (TextView) findViewById(R.id.spinnerText);
	}
}
