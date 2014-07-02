package it.d0ge01.cigarettecounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Report extends Activity{
	
	private static TextView tv;
	PrefsMan man;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);    
		tv = (TextView) findViewById(R.id.textView1);
		
		man = new PrefsMan(this);
		
		tv.setText("Ciao mondo");
	}
}
