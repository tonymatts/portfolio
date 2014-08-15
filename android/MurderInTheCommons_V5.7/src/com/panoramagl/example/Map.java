//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

//import com.example.mapproto.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Map extends Activity {
	
	//ImageView gameBoard;
	private ImageView mapView;
	private Button closeMap;
	private Button fl1, fl2, fl3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
        mapView = (ImageView) findViewById(R.id.image);
        
        mapView.setImageResource(R.drawable.floor_2);
        
        closeMap = (Button) findViewById(R.id.buttonClose);
        closeMap.setOnClickListener(chooseFloor);
        
        fl1 = (Button) findViewById(R.id.imageF1);
        fl1.setOnClickListener(chooseFloor);
        
        fl2 = (Button) findViewById(R.id.imageF2);
        fl2.setOnClickListener(chooseFloor);
        
        fl3 = (Button) findViewById(R.id.imageF3);
        fl3.setOnClickListener(chooseFloor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //Listener for floor buttons and close map button
    private OnClickListener chooseFloor = new OnClickListener() {
    		
    		public void onClick(View v) {
    			
    			int id = v.getId();
    			
    			if (id == R.id.imageF1) {
    				mapView.setImageResource(R.drawable.floor_1);
    			}
    			else if (id == R.id.imageF2) {
    				mapView.setImageResource(R.drawable.floor_2);
    			}
    			else if (id == R.id.imageF3) {
    				mapView.setImageResource(R.drawable.floor_3);
    			}
    			else
    				finish();
    		}
    };
}