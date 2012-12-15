package follow.tutorial.nativeapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Menu_Screen extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_menu__screen);
	
	    ImageButton attract = (ImageButton) findViewById(R.id.btnNextScreen);
	    ImageButton teleport = (ImageButton) findViewById(R.id.btnNextScreen2);
	    ImageButton repel = (ImageButton) findViewById(R.id.btnNextScreen3);
	    ImageButton orbital = (ImageButton) findViewById(R.id.btnNextScreen4);
	    
        //Listening to button event
        attract.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                
                nextScreen.putExtra("strategy", 1);
               
                Log.e("creating game panel screen", "fat");
                
                startActivity(nextScreen);
 
            }
        });
        
        teleport.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                
                nextScreen.putExtra("strategy", 2);
               
                Log.e("creating game panel screen", "fat");
                
                startActivity(nextScreen);
 
            }
        });
        
        repel.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                
                nextScreen.putExtra("strategy", 3);
               
                Log.e("creating game panel screen", "fat");
                
                startActivity(nextScreen);
 
            }
        });
        
        orbital.setOnClickListener(new View.OnClickListener() {
          	 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                
                nextScreen.putExtra("strategy", 4);
               
                Log.e("creating game panel screen", "fat");
                
                startActivity(nextScreen);
 
            }
        });
	}
	
	@Override
 	protected void onDestroy() {
		Log.e("destroying menu", "fat");
    	super.onDestroy();
    }

    @Override
    protected void onStop() {
    	Log.e("stopping menu", "fat");
    	super.onStop();
    }


}
