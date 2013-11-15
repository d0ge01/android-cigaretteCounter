package it.d0ge01.cigarettecounter;

import java.util.Calendar;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.CalendarView;

public class Report extends Activity{
	
	CalendarView cv1;
	Calendar calendar;
	Resources res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);    
		
		// cv1 = (CalendarView) findViewById(R.id.calendarView1);
		res = getResources();
		calendar = Calendar.getInstance();
		
		// cv1.setDate(calendar.getTimeInMillis());
	}
}
