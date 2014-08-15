//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CrumpledMap extends Activity
{
	private Button foldMap;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crumpled_map_main);       
        
        foldMap = (Button) findViewById(R.id.foldMapButton);
        foldMap.setOnClickListener(foldMapListener);
    }
    private OnClickListener foldMapListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
		}
    };
}
