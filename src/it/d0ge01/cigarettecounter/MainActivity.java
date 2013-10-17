package it.d0ge01.cigarettecounter;

// 0.2a

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import it.d0ge01.cigarettecounter.*;

public class MainActivity extends Activity  {

	public int n = 0;
	
	private final static String TEXT_DATA_KEY = "textData";
	private final static String INT_DATA_KEY = "dayData";

	
	private Calendar calendar;
	private Resources res;
	private static int day;
	
	private Button button1;
	private Button button2;
	
	private MainActivity activity = this;
	private TextView tv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		
		tv = (TextView) findViewById(R.id.textView1);
		
		res = getResources();
		
		calendar = Calendar.getInstance();
		day = calendar.get(Calendar.DAY_OF_YEAR);
		
		if ( readDay() != day ) {
			n = 0;
			savePreferencesData();
		}
		
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ( ( n >= 0 ) && ( n < 1000 ) ) {
					n += 1;
				} else {
					n = 0;
				}
				Toast.makeText(getApplicationContext(), R.string.frase05 , Toast.LENGTH_SHORT).show();
				savePreferencesData();
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ( n >= 1 )
					n -= 1;
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
	
	private void createDialogReset() {
		Dialogo ob = new Dialogo();
		ob.setOb(activity);
		ob.show(getFragmentManager(), UI_MODE_SERVICE);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch ( item.getItemId() ) {
		case R.id.action_settings:
			createDialogReset();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	private void updatePreferencesData(){
        SharedPreferences prefs = getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        String textData = prefs.getString(TEXT_DATA_KEY, "0");
        
        n = (int) Integer.parseInt(textData);
        if ( n == 0 )
        {
        	tv.setText(R.string.frase03);
        }
        else {
        	tv.setText((res.getString(R.string.frase02).replace("N", Integer.toString(n))));
        }
	}
	
	public void savePreferencesData() {
        SharedPreferences prefs = getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TEXT_DATA_KEY, String.valueOf(n));
        editor.commit();
        editor.putInt(INT_DATA_KEY, day);
        editor.commit();
        updatePreferencesData();
	}
	
	private int readDay() {
		SharedPreferences prefs = getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        int ret = prefs.getInt(INT_DATA_KEY, 0);
        return ret;
	}
	
	public void setZero() {
		n = 0;
		savePreferencesData();
	}
}
