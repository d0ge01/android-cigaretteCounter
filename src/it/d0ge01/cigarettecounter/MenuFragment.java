package it.d0ge01.cigarettecounter;


import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {
	
	Button bt1, bt2;
	
	private int n = 0;
	
	
	private final static String TEXT_DATA_KEY = "textData";
    private final static String INT_DATA_KEY = "dayData";
    
    private Calendar calendar;
    private Resources res;
    private static int day;
    
	// activity listener
	   private OnMenufragListener menufragListener;

	   // interface for communication with activity
	   public interface OnMenufragListener {
	      public void onMenufrag(int n);

		void onMenufrag(String s);
	   }
	   
	   // onAttach
	   @Override
	   public void onAttach(Activity activity) {
	      super.onAttach(activity);
	      try {
	         menufragListener = (OnMenufragListener) activity;
	      } catch (ClassCastException e) {
	         throw new ClassCastException(activity.toString()+" must implement OnMenufragListener");
	      }
	   }
	   
	   // onCreate
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	   }

	   // onActivityCreated
	   @Override
	   public void onActivityCreated(Bundle savedInstanceState) {
	      super.onActivityCreated(savedInstanceState);
	   }
	   
	   // onCreateView
	   @Override
	   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
	      View view = inflater.inflate(R.layout.fragment_menu,container,false);
	      res = getResources();
          
          calendar = Calendar.getInstance();
          day = calendar.get(Calendar.DAY_OF_YEAR);
          
	      // get button BTN1
	      bt1 = (Button)view.findViewById(R.id.bt1);
	      // button listener
	      bt1.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	        	n++;
	            justSmoked();
	         }
	      });
	      
	      // get button BTN2
	      bt2 = (Button)view.findViewById(R.id.bt2);
	      // button listener
	      bt2.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            sendBodyTextToActivity(n);
	         }
	      });
	      
	      return view;
	   }
	   
	   // (recommended) method to send command to activity
	   private void sendBodyTextToActivity(int n) {
	      menufragListener.onMenufrag(""+n);
	   }
	   
	   // alternate (not recommended) method with direct access to fragment
	   private void justSmoked() {
	      
	      // get body fragment (native method is getFragmentManager)
	      BodyFragment fragment = (BodyFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.bodyFragment);
	      
	      // if fragment is not null and in layout, set text, else launch BodyActivity
	      if ((fragment!=null)&&fragment.isInLayout()) {
	         fragment.setText("Buona fumata :)");
	         fragment.setImage(4);
	      } else {
	         Intent intent = new Intent(getActivity().getApplicationContext(),BodyActivity.class);
	         intent.putExtra("value","Buona fumata :)");
	         intent.putExtra("image", "4");
	         startActivity(intent);
	      }
	      
	   }
		
		public void setZero() {
			n = 0;
		}
	}
