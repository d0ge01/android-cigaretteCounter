package it.d0ge01.cigarettecounter;

import java.util.Calendar;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class PrefsMan {
	Activity act;
	
	private final static String TEXT_DATA_KEY = "textData";
    private final static String INT_DATA_KEY = "dayData";
    
    private Calendar calendar;
    private Resources res;
    private static int day;
    private static int year;
    
    private int n = 0;
    
    LinkedList container = new LinkedList();
    
	public PrefsMan(Activity act) {
		this.act = act;
		
		res = this.act.getResources();
        
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_YEAR);
        year= calendar.get(Calendar.YEAR);
        updatePreferencesData();
	}
	
	public boolean checkDay() {
		if ( day == readDay()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updatePreferencesData(){
        SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        String textData = prefs.getString(TEXT_DATA_KEY, "0");
        
        n = (int) Integer.parseInt(textData);
	}
	
	public void savePreferencesData(int n) {
		this.n = n;
        SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TEXT_DATA_KEY, String.valueOf(n));
        editor.commit();
        editor.putInt(INT_DATA_KEY, day);
        editor.commit();
        updatePreferencesData();
    }
	
	private int readDay() {
		  SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
	      int ret = prefs.getInt(INT_DATA_KEY, 0);
	      return ret;
	}
	
	public int getN() {
		return this.n;
	}
}
