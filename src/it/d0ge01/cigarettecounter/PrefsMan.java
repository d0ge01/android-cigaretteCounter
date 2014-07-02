package it.d0ge01.cigarettecounter;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class PrefsMan {
	Activity act;
	
	private final static String DAY_KEY = "textData";
    private final static String N_KEY = "dayData";
    private final static String N_ELEMENT = "vectorData";
    
    private Calendar calendar;
    private static int day;
    private static int year;
    
    private int n = 0;
    private int nVector = 0;
    
    public PrefsMan(Activity act) {
		this.act = act;
		
		this.act.getResources();
        
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_YEAR);
        year= calendar.get(Calendar.YEAR);
        updatePreferencesData();
	}
	
    public PrefsMan(SharedPreferences sprefs) {
        
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_YEAR);
        year= calendar.get(Calendar.YEAR);
        widgetPreferencesData(sprefs);
	}
	
	
	
	private int now() {
		return day+year;
	}
	
	public boolean checkDay() {
		if ( now() == (this.readLastDay())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void widgetPreferencesData(SharedPreferences prefs) {
		int nElementi = prefs.getInt(N_ELEMENT, 0);
        nVector = nElementi;
        try {
        	if ( checkDay() )
        		n = prefs.getInt(N_KEY + nElementi, 0);
        	else
        		n = 0;
        } catch ( Exception e ) {
        	n = 0;
        }
		n += 1;
        SharedPreferences.Editor editor = prefs.edit();
        if ( checkDay() ) {
	        editor.putInt(DAY_KEY + nVector, day+year);
	        editor.putInt(N_KEY + nVector, n);
	        editor.commit();
        } else {
        	editor.putInt(DAY_KEY + ( nVector + 1 ), now());
        	editor.putInt(N_KEY+ ( nVector + 1), n);
        	editor.putInt(N_ELEMENT, nVector + 1);
        	editor.commit();
        }
		
	}
	
	public void updatePreferencesData(){
        SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        int nElementi = prefs.getInt(N_ELEMENT, 0);
        nVector = nElementi;
        try {
        	if ( checkDay() )
        		n = prefs.getInt(N_KEY + nElementi, 0);
        	else
        		n = 0;
        } catch ( Exception e ) {
        	n = 0;
        }
	}
	
	public void savePreferencesData(int n) {
		this.n = n;
        SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        if ( checkDay() ) {
	        editor.putInt(DAY_KEY + nVector, day+year);
	        editor.putInt(N_KEY + nVector, n);
	        editor.commit();
        } else {
        	editor.putInt(DAY_KEY + ( nVector + 1 ), now());
        	editor.putInt(N_KEY+ ( nVector + 1), n);
        	editor.putInt(N_ELEMENT, nVector + 1);
        	editor.commit();
        }
        updatePreferencesData();
    }
	
	private int readLastDay() {
		SharedPreferences prefs = this.act.getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
		int nElementi = prefs.getInt(N_ELEMENT, 0);
		
		return prefs.getInt(DAY_KEY+nElementi, now());
	}
	
	public String getAllDay() {
		return "ciao|mondo";
	}
	
	public int getN() {
		return this.n;
	}
}
