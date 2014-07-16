package it.d0ge01.cigarettecounter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {
	
	   Button bt1, bt2, bt3;
	   private int n = 0;
    
	   // interface for save data
	   private PrefsMan man;
	   
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
	      man = new PrefsMan(this.getActivity());
	      
	      n = man.getN();
          if ( !man.checkDay()) {
        	  n = 0;
        	  man.savePreferencesData(n);
          }
                    
	      // get button BTN1
	      bt1 = (Button)view.findViewById(R.id.bt1);
	      // button listener
	      bt1.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            justSmoked();
	         }
	      });
	      
	      // get button BTN2
	      bt2 = (Button)view.findViewById(R.id.bt2);
	      // button listener
	      bt2.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            //sendBodyTextToActivity(n);
	        	 untilNowCiga();
	         }
	      });
	      
	      // get button BTN3
	      bt3 = (Button)view.findViewById(R.id.bt3);
	      // Button listener
	      bt3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity().getApplicationContext(),BodyActivity.class);
				startActivity(intent);
			}
		});
	      return view;
	   }
	   
	   private void untilNowCiga() {
	      
	      BodyFragment fragment = (BodyFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.bodyFragment);
	      
	      man.updatePreferencesData();
	      n = man.getN();
	      
	      if ((fragment!=null)&&fragment.isInLayout()) {
	    	 fragment.setText(getString(R.string.reportString).replace("N", Integer.toString(n)));
	    	if ( n >= 10 && n <= 20 )
	    		fragment.setImage(2);
	    	if ( n < 10 )
		    	fragment.setImage(1);
		    if ( n > 20 )
		    	fragment.setImage(3);
	      } else {
	         Intent intent = new Intent(getActivity().getApplicationContext(),BodyActivity.class);
	         intent.putExtra("value",getString(R.string.reportString).replace("N", Integer.toString(n))); 
	         if ( n >= 10 && n <= 20 )
	        	 intent.putExtra("image", "2");
	         if ( n < 10 )
	        	 intent.putExtra("image", "1");
		     if ( n > 20 )
		    	 intent.putExtra("image", "3");
		     startActivity(intent);
	      }
	      
	   }
	   
	   private void justSmoked() {
		      
		      BodyFragment fragment = (BodyFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.bodyFragment);
		      n = man.getN();
		      n += 1;
		      man.savePreferencesData(n);
		      
		      if ((fragment!=null)&&fragment.isInLayout()) {
		         fragment.setText(getString(R.string.gsmook));
		         fragment.setImage(4);
		      } else {
		         Intent intent = new Intent(getActivity().getApplicationContext(),BodyActivity.class);
		         intent.putExtra("value",getString(R.string.gsmook));
		         intent.putExtra("image", "4");
		         startActivity(intent);
		      }
		      
	  }
}
