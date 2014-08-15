//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GhostLeg  extends Activity
{
	private Button doneGhostLeg;
	private ImageButton leftButton,rightButton;
	ImageView ghostLegImageView;
	TextView sayings;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghost_leg_main);  
        
        ghostLegImageView=(ImageView) findViewById(R.id.ghostLegImageView);
        sayings=(TextView)findViewById(R.id.sayings);
        //setting image resource
        loadCorrectImage();
        
        PanoramaGLActivity.theGhostLeg.setVisibility(View.VISIBLE);
        PanoramaGLActivity.theSolution.setVisibility(View.VISIBLE);
        
        doneGhostLeg = (Button) findViewById(R.id.doneGhostLegButton);
        doneGhostLeg.setOnClickListener(doneGhostLegListener);
        leftButton=(ImageButton)findViewById(R.id.prevFlier);
        leftButton.setOnClickListener(leftListener);
        rightButton=(ImageButton)findViewById(R.id.nextFlier);
        rightButton.setOnClickListener(rightListener);
    }
    private void loadCorrectImage()
    {
	    switch(PanoramaGLActivity.flierNumber)
	    {
	    	case 0:
	    		ghostLegImageView.setImageResource(R.drawable.ghost_leg);
	    		sayings.setText("");
	    		break;
	    	case 1:
	    		ghostLegImageView.setImageResource(R.drawable.zombie);
	    		sayings.setText(getResources().getString(R.string.zombie_sayings));
	    		break;
	    	case 2:
	    		ghostLegImageView.setImageResource(R.drawable.archery);
	    		sayings.setText(getResources().getString(R.string.archery_sayings));
	    		break;
	    	case 3:
	    		ghostLegImageView.setImageResource(R.drawable.undie);
	    		sayings.setText(getResources().getString(R.string.undie_sayings));
	    		break;
	    }
    }
    private OnClickListener doneGhostLegListener = new OnClickListener() {
		
		public void onClick(View v) {
			finish();
		}
    };
    private OnClickListener leftListener = new OnClickListener() {
		
		public void onClick(View v) {
			PanoramaGLActivity.flierNumber=((PanoramaGLActivity.flierNumber-1+PanoramaGLActivity.flierOpen.length)%PanoramaGLActivity.flierOpen.length);
			while(!PanoramaGLActivity.flierOpen[PanoramaGLActivity.flierNumber])
			{
				PanoramaGLActivity.flierNumber=((PanoramaGLActivity.flierNumber-1+PanoramaGLActivity.flierOpen.length)%PanoramaGLActivity.flierOpen.length);
			}
			loadCorrectImage();
		}
    };
    private OnClickListener rightListener = new OnClickListener() {
		
		public void onClick(View v) {
			PanoramaGLActivity.flierNumber=(PanoramaGLActivity.flierNumber+1)%PanoramaGLActivity.flierOpen.length;
			while(!PanoramaGLActivity.flierOpen[PanoramaGLActivity.flierNumber])
			{
				PanoramaGLActivity.flierNumber=(PanoramaGLActivity.flierNumber+1)%PanoramaGLActivity.flierOpen.length;
			}
			loadCorrectImage();
		}
    };
}
