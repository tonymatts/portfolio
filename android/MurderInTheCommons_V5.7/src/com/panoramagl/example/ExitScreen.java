//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ExitScreen extends Activity{

	String who,what,where;
	ImageView murderSolved,wrongWithKnife,wrongWithRevolver,genericWrong;
	View questionsView;
	RelativeLayout emptyContainerLayout, solutionRelativeLayout;
	RelativeLayout.LayoutParams actualParams;
	
	//Spinners are created here, three will be needed. One for suspects, weapons, and rooms
	//each spinner will be tied to a sting-array with the appropriate items in each.
	Spinner spinner1;
	Spinner spinner2;
	Spinner spinner3;
	
	//created the submit button and the goBackToWorkButton
	Button submitButton,goBackToWorkButton,creditsButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		///  the trick below is to use a fake layout that fills up the WHOLE screen so that you can then add an actual layout to the
		// the fake layout!
		// Fake empty container layout
		emptyContainerLayout = new RelativeLayout(this);
		emptyContainerLayout.setLayoutParams(new RelativeLayout.LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT ));
		emptyContainerLayout.setBackgroundColor(Color.parseColor("#EEEED1"));
		
		questionsView = getLayoutInflater().inflate(R.layout.exit_screen, null);
		
		actualParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		
		questionsView.setLayoutParams(actualParams);
		emptyContainerLayout.addView(questionsView);
		
		addContentView(emptyContainerLayout, new LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT ));

		//instantiates the spinners
		spinner1=(Spinner)questionsView.findViewById(R.id.spinner1);
		spinner2=(Spinner)questionsView.findViewById(R.id.spinner2);
		spinner3=(Spinner)questionsView.findViewById(R.id.spinner3);
		
		murderSolved = (ImageView)questionsView.findViewById(R.id.finalScreenImageView);
		wrongWithRevolver=(ImageView)questionsView.findViewById(R.id.wrongWithRevolver);
		wrongWithKnife=(ImageView)questionsView.findViewById(R.id.wrongWithKnife);
		genericWrong=(ImageView)questionsView.findViewById(R.id.genericWrong);
		
		submitButton=(Button)questionsView.findViewById(R.id.finalSubmitButton);
		submitButton.setOnClickListener(whoWhatWhereListener);
		goBackToWorkButton=(Button)questionsView.findViewById(R.id.solutionReturnToMapButton);
		goBackToWorkButton.setOnClickListener(goBackToWorkListener);
		creditsButton=(Button)questionsView.findViewById(R.id.creditsButton);
		creditsButton.setOnClickListener(creditsListener);
	}
	private OnClickListener whoWhatWhereListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	  		//this code should retrieve the spinners' answers 
	  		who = String.valueOf(spinner1.getSelectedItem());
	  		what = String.valueOf(spinner2.getSelectedItem());
	  		where= String.valueOf(spinner3.getSelectedItem());
	  		
	  		if(who.equalsIgnoreCase("Mr. Green")&&what.equals("Candlestick")&&where.equals("Study"))
	  		{
	    		  spinner1.setVisibility(View.INVISIBLE);
	    		  spinner2.setVisibility(View.INVISIBLE);
	    		  spinner3.setVisibility(View.INVISIBLE);
	    		  submitButton.setVisibility(View.INVISIBLE);
	    		  goBackToWorkButton.setVisibility(View.VISIBLE);
	    		  murderSolved.setVisibility(View.VISIBLE);
	    		  creditsButton.setVisibility(View.VISIBLE);
	  		}
	  		else
	  			if(who.equals("Mrs. White")&&what.equals("Knife")&&where.equals("Kitchen"))
		  		{
		    		  spinner1.setVisibility(View.INVISIBLE);
		    		  spinner2.setVisibility(View.INVISIBLE);
		    		  spinner3.setVisibility(View.INVISIBLE);
		    		  submitButton.setVisibility(View.INVISIBLE);
		    		  wrongWithKnife.setVisibility(View.VISIBLE);
		  		}
		  		else
		  			if(what.equals("Revolver"))
			  		{
			    		  spinner1.setVisibility(View.INVISIBLE);
			    		  spinner2.setVisibility(View.INVISIBLE);
			    		  spinner3.setVisibility(View.INVISIBLE);
			    		  submitButton.setVisibility(View.INVISIBLE);
			    		  wrongWithRevolver.setVisibility(View.VISIBLE);
			  		}
		  			else
		  			{
			    		  spinner1.setVisibility(View.INVISIBLE);
			    		  spinner2.setVisibility(View.INVISIBLE);
			    		  spinner3.setVisibility(View.INVISIBLE);
			    		  submitButton.setVisibility(View.INVISIBLE);
			    		  genericWrong.setVisibility(View.VISIBLE);
		  			}
	      }
	};
	private OnClickListener goBackToWorkListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	    	  finish();
	      }
	};	
	private OnClickListener creditsListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.Credits(MyApplication.getContext());
	      }
	};	
}