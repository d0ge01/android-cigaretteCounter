package it.d0ge01.cigarettecounter;

import java.util.Calendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyWidgetProvider extends AppWidgetProvider {
	// private static final String SYNC_CLICKED = "addCigarette";
	
	
	// DISABLED FOR NOW; INSTEAD STARTING MAIN ACTIVITY
	private final static String DAY_KEY = "textData";
    private final static String N_KEY = "dayData";
    private final static String N_ELEMENT = "vectorData";

	
	private SharedPreferences prefs;
	
	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews;
        ComponentName watchWidget;
        
        Intent configIntent = new Intent(context, CigaretteCounterActivity.class);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        watchWidget = new ComponentName(context, MyWidgetProvider.class);
        
        remoteViews.setOnClickPendingIntent(R.id.buttonPlus, configPendingIntent);
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }
 
    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);
 
    }
}
