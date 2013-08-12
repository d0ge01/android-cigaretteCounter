package it.d0ge01.cigarettecounter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int n = 0;
	
	private final static String MY_PREFERENCES = "MyPref";
	private final static String TEXT_DATA_KEY = "textData";
	
	private Button button1;
	private Button button2;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		tv = (TextView) findViewById(R.id.textView1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				n += 1;
				savePreferencesData();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				n = 0;
				savePreferencesData();
			}
		});
		updatePreferencesData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void updatePreferencesData(){
        SharedPreferences prefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        String textData = prefs.getString(TEXT_DATA_KEY, "0");
        
        n = (int) Integer.parseInt(textData);
        if ( n == 0 )
        {
        	tv.setText(R.string.frase03);
        }
        else {
        	tv.setText("oggi hai fumato " + Integer.toString(n) + " sigarette...");
        }
	}
	
	public void savePreferencesData() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TEXT_DATA_KEY, String.valueOf(n));
        editor.commit();
        updatePreferencesData();
}
}
