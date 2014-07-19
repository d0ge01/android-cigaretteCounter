package it.d0ge01.cigarettecounter;


import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BodyFragment extends Fragment{

	   // onCreate
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	   }

	   // onActivityCreated
	   public void onActivityCreated(Bundle savedInstanceState) {
	      super.onActivityCreated(savedInstanceState);
	   }
	   
	   // onCreateView
	   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
	      View view = inflater.inflate(R.layout.fragment_body,container,false);
	      return view;
	   }
	   
	   // set text
	   public void setText(String item) {
	      TextView view = (TextView) getView().findViewById(R.id.detailsText);
	      view.setText(item);
	   } 
	   
	   public void setImage(int i) {
		   // i: 1  good
		   // i: 2  med
		   // i: 3  bad
		   
		   ImageView view = (ImageView) getView().findViewById(R.id.imageReport);
		   
		   Drawable life = getResources().getDrawable(R.drawable.life);
		   final ColorFilter filter;
		   
		   switch(i) {
		   case 1:
			   filter = new LightingColorFilter(Color.GREEN, 0);
			   life.mutate().setColorFilter(filter);
			   view.setImageDrawable(life);
			   break;
		   case 2:
			   filter = new LightingColorFilter(Color.YELLOW, 0);
			   life.mutate().setColorFilter(filter);
			   view.setImageDrawable(life);
			   break;
		   case 3:
			   filter = new LightingColorFilter(Color.RED, 0);
			   life.mutate().setColorFilter(filter);
			   view.setImageDrawable(life);
			   break;
		   default:
				view.setImageResource(R.drawable.smok);;
		   }
	   }
	   
	}