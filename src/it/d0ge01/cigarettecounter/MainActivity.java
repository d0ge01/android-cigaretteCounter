package it.d0ge01.cigarettecounter;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener {

	private int n = 0;
	
	private final static String MY_PREFERENCES = "MyPref";
	private final static String TEXT_DATA_KEY = "textData";
	static final String KEY = TextToSpeech.Engine.KEY_PARAM_STREAM;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private TextView tv;
	private TextToSpeech tts;
	
	private Boolean speakable = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		
		tv = (TextView) findViewById(R.id.textView1);
		
		tts = new TextToSpeech(this, this);
		
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
		
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				speak("Oggi hai fumato " + n + " sigarette", 1.0);
			}
		});
		/*
		if ( !speakable ) {
			button3.setClickable(false);
		}*/
		
		updatePreferencesData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
        	tv.setText("oggi hai fumato " + Integer.toString(n) + " sigarette...");
        }
	}
	
	public void savePreferencesData() {
        SharedPreferences prefs = getSharedPreferences("PREF_NAME", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TEXT_DATA_KEY, String.valueOf(n));
        editor.commit();
        updatePreferencesData();
	}
	
	public void onInit(int status) {
		Context context = getApplicationContext();
		CharSequence text = "";
		int duration = Toast.LENGTH_SHORT;
		
		if(status == TextToSpeech.SUCCESS){
			int result = tts.setLanguage(Locale.ITALIAN);
			if(result == TextToSpeech.LANG_MISSING_DATA || 
					result == TextToSpeech.LANG_NOT_SUPPORTED){
					text = "Mancano i dati vocali";
				}else{
					text = "Inizializzazione avvenuta con successo..";
					speakable = true;
				}
			}else{
				text = "Text To speech non supportato...";
			}
			Toast.makeText(context, text, duration).show();
	}
	
	public void speak(String text, double speechSpeed){
		tts.setSpeechRate((float)speechSpeed);
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}
