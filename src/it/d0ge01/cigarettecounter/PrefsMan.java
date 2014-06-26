package it.d0ge01.cigarettecounter;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class PrefsMan {
	Activity act;
	
	private final static String TEXT_DATA_KEY = "textData";
    private final static String INT_DATA_KEY = "dayData";
    
    private Calendar calendar;
    private static int day;
    private static int year;
    
    private int n = 0;
    
    LinkedList<Object> container = new LinkedList<Object>();
    
	public PrefsMan(Activity act) {
		this.act = act;
		
		this.act.getResources();
        
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_YEAR);
        year= calendar.get(Calendar.YEAR);
        updatePreferencesData();
	}
	
	public boolean checkDay() {
		if ( (day + year) == this.readLastDay()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updatePreferencesData(){
        SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        String textData = prefs.getString(TEXT_DATA_KEY, "0");
        try {
        	n = (int) Integer.parseInt(textData);
        } catch ( Exception e ) {
        	n = 0;
        }
	}
	
	public void savePreferencesData(int n) {
		this.n = n;
        SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TEXT_DATA_KEY, this.genString());
        editor.commit();
        editor.putInt(INT_DATA_KEY, day+year);
        editor.commit();
        updatePreferencesData();
    }
	
	private String genString() {
		List<String> buff = getAllDay();
		String ret = "";
		if ( checkDay() ) {
			for ( int i = 0 ; i < (buff.size() > 1 ? buff.size() - 1 : 0); i++ )
				ret += (String) buff.get(i);
			ret += "|"+(this.day+this.year)+"-"+this.n;
		} else {
			for ( int i = 0; i < buff.size() ; i++ )
				ret += (String) buff.get(i);
			
			ret += "|"+(this.day+this.year)+"-"+this.n;
		}
		return ret;
	}
	
	private int readLastDay() {
		  SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
	      int ret = prefs.getInt(INT_DATA_KEY, 0);
	      return ret;
	}
	
	public List<String> getAllDay() {
		
		return new LinkedList();
	}
	
	public int getN() {
		return this.n;
	}
}
