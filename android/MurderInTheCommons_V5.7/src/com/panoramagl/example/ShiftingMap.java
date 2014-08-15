//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import java.util.Scanner;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShiftingMap extends Activity
{
	TextView mIntroductionTextView,mQTitleTextView,mQQuestion1TextView,mQQuestion2TextView,mQQuestion3TextView;
	TextView[] mQQ2SingleAnagramTextView;
	Button mIntroContinueButton,goToMQButton,returnToMapButton,mQEnterButton,mQReturnToMapButton;
	ImageView shiftingmImageView;
	TableLayout mQTableLayout;
	TableRow mQEditTextTableRow,mQAnagramTableRow;
	EditText mQQ1EditText,mQQ3EditText;
	EditText[] mQQ2SingleWordEditText;
	RelativeLayout emptyContainerLayout, mQRelativeLayout;
	RelativeLayout.LayoutParams actualParams;
	String mQQ2Answer,mQQ2Anagram;
	String[] mQQ2SingleWord,mQQ2SingleAnagram;
	int [] sFOFIYB;
	Scanner scan,scan2;
	View mIntroductionView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		///  the trick below is to use a fake layout that fills up the WHOLE screen so that you can then add an actual layout to the
		// the fake layout!
		// Fake empty container layout
		emptyContainerLayout = new RelativeLayout(this);
		emptyContainerLayout.setLayoutParams(new RelativeLayout.LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT ));
		emptyContainerLayout.setBackgroundColor(Color.parseColor("#EEEED1"));
		
		mIntroductionView = getLayoutInflater().inflate(R.layout.activity_shifting_map, null);
		
		// reference for TextView
		mIntroductionTextView=(TextView)mIntroductionView.findViewById(R.id.mIntroductionTextView);
		
	       // set up the button listener
        mIntroContinueButton = (Button)mIntroductionView.findViewById(R.id.mIntroContinueButton);     
        mIntroContinueButton.setOnClickListener(seeMapButtonListener);
        
        // find references for other button
        goToMQButton=(Button)mIntroductionView.findViewById(R.id.goToMQButton);
        
        // find reference for ImageView
        shiftingmImageView=(ImageView)mIntroductionView.findViewById(R.id.shiftingmImageView);
        
		actualParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		
		mIntroductionView.setLayoutParams(actualParams);
		emptyContainerLayout.addView(mIntroductionView);
		
		addContentView(emptyContainerLayout, new LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT ));
        
        mQRelativeLayout=(RelativeLayout)getLayoutInflater().inflate(R.layout.map_quiz_relative_layout, null);
        returnToMapButton=(Button)mQRelativeLayout.findViewById(R.id.mQReturnToMapButton);
        returnToMapButton.setOnClickListener(seeMapButtonListener);
        
        //find references for TextViews
        mQTitleTextView=(TextView)mQRelativeLayout.findViewById(R.id.mQTitleTextView);
        mQQuestion1TextView=(TextView)mQRelativeLayout.findViewById(R.id.mQQuestion1TextView);
        mQQuestion2TextView=(TextView)mQRelativeLayout.findViewById(R.id.mQQuestion2TextView);
        mQQuestion3TextView=(TextView)mQRelativeLayout.findViewById(R.id.mQQuestion3TextView);
        
        //references for EditTexts and Buttons
        mQQ1EditText = (EditText)mQRelativeLayout.findViewById(R.id.mQQ1EditText);
        mQEnterButton = (Button)mQRelativeLayout.findViewById(R.id.mQSubmitAnswersButton);
        mQReturnToMapButton =(Button)mQRelativeLayout.findViewById(R.id.mQReturnToMapButton);
        mQQ3EditText = (EditText)mQRelativeLayout.findViewById(R.id.mQQ3EditText);
        mQEnterButton.setOnClickListener(submitAnswersListener);
        mQReturnToMapButton.setOnClickListener(seeMapButtonListener);
        
        goToMQButton.setOnClickListener(mQListener);
        
        // prepare mQTableLayout so that it is only prepared once
        mQTableLayout = (TableLayout)mQRelativeLayout.findViewById(R.id.mQTableLayout);
        mQEditTextTableRow = (TableRow)mQRelativeLayout.findViewById(R.id.mQEditTextTableRow);
        mQAnagramTableRow = (TableRow)mQRelativeLayout.findViewById(R.id.mQAnagramTableRow);
        mQQ2Answer=(String) getResources().getString(R.string.m_q_q_2_answer);
        mQQ2Anagram=(String)getResources().getString(R.string.m_q_q_2_anagram);
        scan = new Scanner(mQQ2Answer);
        int count=0;
        while(scan.hasNext())
        {
        	//first, increment scan
        	scan.next();
        	count++;
        }
        mQQ2SingleWordEditText = new EditText[count];
        mQQ2SingleAnagramTextView = new TextView[count];
        mQQ2SingleWord = new String[count];
        mQQ2SingleAnagram= new String[count];
        sFOFIYB = new int[count];
        
        // must reset scanner since it has been used up
        scan = new Scanner(mQQ2Answer);
        scan2 = new Scanner(mQQ2Anagram);
        for(int i=0;i<count;i++)
        {
        	mQQ2SingleWord[i] = scan.next();
        	mQQ2SingleAnagram[i] = scan2.next();
        }
       
        // inflate separate EditTexts and Textviews
		for(int i=0;i<(mQQ2SingleWordEditText.length);i++)
		{
			inflateSingleWordEditText(i);
			inflateSingleAnagramTextView(i);
		}	
		
		PanoramaGLActivity.theCrumpledMap.setVisibility(View.VISIBLE);
        
	}
	
	private void inflateSingleWordEditText(int index)
	{
		// get a reference to the LayoutInflater service
		LayoutInflater inflater = (LayoutInflater) getSystemService(
		   Context.LAYOUT_INFLATER_SERVICE);
		
		// inflate
		mQQ2SingleWordEditText[index] = (EditText)inflater.inflate(R.layout.m_q_single_word_edit_text, null);
		mQQ2SingleWordEditText[index].setTextSize(10);
		
		mQEditTextTableRow.addView(mQQ2SingleWordEditText[index]);	
	}
	
	private void inflateSingleAnagramTextView(int index)
	{
		// get a reference to the LayoutInflater service
		LayoutInflater inflater = (LayoutInflater) getSystemService(
		   Context.LAYOUT_INFLATER_SERVICE);
		
		// inflate
		mQQ2SingleAnagramTextView[index] = (TextView)inflater.inflate(R.layout.m_q_single_anagram_text_view, null);
		
		mQQ2SingleAnagramTextView[index].setText(mQQ2SingleAnagram[index]);
		
		mQAnagramTableRow.addView(mQQ2SingleAnagramTextView[index]);	
	}
	
	private OnClickListener seeMapButtonListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	    	  emptyContainerLayout.removeAllViews();
	    	  emptyContainerLayout.addView(mIntroductionView);
	    	  mIntroContinueButton.setVisibility(View.GONE);
	    	  mIntroductionTextView.setVisibility(View.GONE);
	    	  shiftingmImageView.setVisibility(View.VISIBLE);
	    	  goToMQButton.setVisibility(View.VISIBLE);
	      }
	};
	
	private OnClickListener mQListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	    	  emptyContainerLayout.removeAllViews();
	    	  emptyContainerLayout.addView(mQRelativeLayout);
	    	  /*mQRelativeLayout.setVisibility(View.VISIBLE);
	    	  mIntroContinueButton.setVisibility(View.GONE);
	    	  shiftingmImageView.setVisibility(View.GONE);*/
	      }
	};
	private OnClickListener submitAnswersListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	    	  
	    	  String topLocation1,topLocation2, suspect;
	    	  int t=0,s=0;
	    	  topLocation1=(String)getResources().getString(R.string.top_map1);
	    	  topLocation2=(String)getResources().getString(R.string.top_map2);
	    	  suspect=(String)getResources().getString(R.string.suspect);
	    	  if(topLocation1.equalsIgnoreCase(mQQ1EditText.getText().toString().trim())||topLocation2.equalsIgnoreCase(mQQ1EditText.getText().toString().trim()))
	    	  {
	    		  //top location is correct
	    		  t=1;
	    		  mQQ1EditText.setTextColor(Color.parseColor("#006400"));
	    		  mQQ1EditText.setFocusable(false);
	    		  mQQ1EditText.setFocusableInTouchMode(false);
	    		  mQQ1EditText.setClickable(false);
	    	  }
	    	  
	    	  for(int i=0;i<sFOFIYB.length;i++)
	    	  {
	    		  // note that the regex in this next line will ignore the parentheses that are in the answer text but will still print them in the answer box.
	    		  if(mQQ2SingleWord[i].replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(mQQ2SingleWordEditText[i].getText().toString().replaceAll("[^a-zA-Z0-9]", "").trim()))
	    		  {
	    			  sFOFIYB[i]=1;
	    			  mQQ2SingleWordEditText[i].setTextColor(Color.parseColor("#006400"));
	    			  mQQ2SingleWordEditText[i].setFocusable(false);
					  mQQ2SingleWordEditText[i].setFocusableInTouchMode(false);
					  mQQ2SingleWordEditText[i].setClickable(false);
	    		  }
	    	  }
	    	  if(suspect.equalsIgnoreCase(mQQ3EditText.getText().toString().trim()))
	    	  {
	    		  s=1;
	    		  mQQ3EditText.setTextColor(Color.parseColor("#006400"));
	    		  mQQ3EditText.setFocusable(false);
	    		  mQQ3EditText.setFocusableInTouchMode(false);
	    		  mQQ3EditText.setClickable(false);
	    	  }
	    	  
	    	  // check to see if ALL of the answers are right
	    	  int totalSolved = 0,totalNeeded=0;
	    	  totalSolved = t;
	    	  totalNeeded = 1;
	    	  for(int i=0;i<sFOFIYB.length;i++)
	    	  {
	    		  totalSolved+=sFOFIYB[i];
	    		  totalNeeded++;		  
	    	  }
	    	  totalSolved+=s;
	    	  totalNeeded+=1;
	    	  
	    	  if(totalSolved==totalNeeded)
	    	  {
	    		  PanoramaGLActivity.theMap.setVisibility(View.VISIBLE);
	    		  finish();
	    	  }
	    	  
	      }
	 };
	
}
