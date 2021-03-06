package it.d0ge01.cigarettecounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class CigaretteCounterActivity extends FragmentActivity implements MenuFragment.OnMenufragListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cigarette_counter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.cigarette_counter, menu);
		return true;
	}
	
	public void onMenufrag(String s) {
	    
	    BodyFragment fragment = (BodyFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentbody);

	    if ((fragment!=null)&&fragment.isInLayout()) {
	    	if ( s.equals(getString(R.string.gsmook))) {
	    		fragment.setText(s);
	    		fragment.setImage(4);
	    	} else {
		    	int n = Integer.valueOf(s);
		    	fragment.setText(getString(R.string.reportString).replace("N", Integer.toString(n)));
		    	if ( n >= 10 && n <= 20 )
		    		fragment.setImage(2);
		    	if ( n < 10 )
		    		fragment.setImage(1);
		    	if ( n > 20 )
		    		fragment.setImage(3);
	    	}
	    } else {
	    	if ( s.equals(getString(R.string.gsmook))) {
		        Intent intent = new Intent(this,BodyActivity.class);
		        intent.putExtra("value",s); // Another way to cast to String
		        intent.putExtra("image", "4");
		        startActivity(intent);
	    	} else {
		        Intent intent = new Intent(this,BodyActivity.class);
		        int n = Integer.valueOf(s);
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
	}

	@Override
	public void onMenufrag(int n) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch ( item.getItemId() ) {
            case R.id.action_settings:
                    createDialogReset();
                    return true;
            case R.id.credits_settings:
            		Toast.makeText(getApplicationContext(), "App interamente scritta da Salvatore Criscione", Toast.LENGTH_LONG).show();
            		return true;
            default:
                    return super.onOptionsItemSelected(item);
            }
    }
	
	private void createDialogReset() {
        Dialogo ob = new Dialogo();
        //MenuFragment fragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.menuFragment);
        ob.show(getFragmentManager(), UI_MODE_SERVICE);
}
}
