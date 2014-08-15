//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import com.panoramagl.PLImage;
import com.panoramagl.PLSpherical2Panorama;
import com.panoramagl.PLView;
import com.panoramagl.loaders.PLILoader;
import com.panoramagl.loaders.PLJSONLoader;
import com.panoramagl.transitions.PLTransitionBlend;
import com.panoramagl.utils.PLUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PanoramaGLActivity extends PLView {
	
	View introductionView,userManualView,fortuneCookieView,buttonsDuringPanoramaView;
	RelativeLayout emptyContainerLayout;
	RelativeLayout.LayoutParams actualParams;
	TableLayout fortuneCookieTable;
	int numOfRows = 5,numOfColors=2,lettersRevealed=0;
	String completedMessageString,lettersOnlyOfMessageString="",waitressReturnsString;
	TableRow [] fortuneTableRow;
	Button[] upButton;
	Button[] downButton;
	int[] rankOfCurrentWord;
	int [] correctOrdering;
	LinearLayout [] holderOfLettersLinearLayout;
	LinearLayout [][] singleCharacterHolder;
	EditText [][] sCET;
	EditText introductionEditText,userManualEditText,fortuneCookieHintEditText,solutionEditText,panMessageRevealed;
	Button enterFortuneCookie,fortuneCookieHintButton,transcribeMessage,waitressReturnsButton,enterUCButton,panFortuneButton,userManual;
	static public Button panPhoneBookButton,theMap,theLetter,theCrumpledMap,theGhostLeg,theSolution,theNotes;
	static public boolean[] flierOpen = new boolean[4];
	static public int flierNumber=0;
	static public String keptNotes="";
	ImageView fortuneCookieImageView;
	Timer timer = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		PLILoader loader = null;
		loader = new PLJSONLoader("res://raw/js_fl2_10");
		this.load(loader, true, new PLTransitionBlend(2.0f));
		
		///  the trick below is to use a fake layout that fills up the WHOLE screen so that you can then add an actual layout to the
		// the fake layout!
		// Fake empty container layout
		emptyContainerLayout = new RelativeLayout(this);
		emptyContainerLayout.setLayoutParams(new RelativeLayout.LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT ));
		emptyContainerLayout.setBackgroundColor(Color.parseColor("#EEEED1"));
		
		// Custom view
		introductionView = getLayoutInflater().inflate(R.layout.introduction_main, null);
		
		actualParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
		
		introductionView.setLayoutParams(actualParams);
		emptyContainerLayout.addView(introductionView);
		
		addContentView(emptyContainerLayout, new LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT ));
		
		introductionEditText=(EditText)findViewById(R.id.introductionEditText);
		userManual= (Button)introductionView.findViewById(R.id.userManual);
		userManual.setOnClickListener(userManualListener);
	}
		
   public OnClickListener userManualListener = new OnClickListener() 
   {
      @Override
      public void onClick(View v) 
      {
		userManualEditText = (EditText)findViewById(R.id.userManualEditText);
		userManualEditText.setVisibility(View.VISIBLE);
		introductionEditText.setVisibility(View.INVISIBLE);
		userManual.setVisibility(View.INVISIBLE);
		enterFortuneCookie= (Button)introductionView.findViewById(R.id.enterFortuneCookie);
		enterFortuneCookie.setOnClickListener(enterFortuneCookieListener);
		enterFortuneCookie.setVisibility(View.VISIBLE);
      }
		
	};
		
   public OnClickListener enterFortuneCookieListener = new OnClickListener() 
   {
      @Override
      public void onClick(View v) 
      {
			emptyContainerLayout.removeAllViews();
			// Custom view
			fortuneCookieView = getLayoutInflater().inflate(R.layout.activity_fortune_cookie_main, null);
			
			actualParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
			
			fortuneCookieView.setLayoutParams(actualParams);
			emptyContainerLayout.addView(fortuneCookieView);
			///////////////////////////////////////////////////////////////////////
	
			
			fortuneCookieImageView = (ImageView) findViewById(R.id.fortuneCookieImageView);
			
			fortuneCookieTable = (TableLayout) findViewById(R.id.fortuneCookieTable);
			
			completedMessageString = getResources().getString(R.string.completed_message);
			
			fortuneCookieHintButton=(Button)findViewById(R.id.hintButton);
			fortuneCookieHintButton.setOnClickListener(fortuneCookieHintListener);
			fortuneCookieHintEditText=(EditText)findViewById(R.id.fortuneCookieHintEditText);
			
			// how many letters are in the completedMessage so that you can declare the rankOfCurrentWord array
			int count=0;
			for(int i=0;i<completedMessageString.length();i++)
			{
				if(completedMessageString.substring(i,i+1).matches("\\p{L}"))
				{
					count++;
				}
			}
			rankOfCurrentWord = new int[count];
			rankOfCurrentWord[0]=0;
			
			// now extract only the letters from the completed message string
			if(completedMessageString.substring(0,1).matches("\\p{L}"))
			{
				lettersOnlyOfMessageString += completedMessageString.substring(0,1).toUpperCase();
			}
			for(int i=1;i<completedMessageString.length();i++)
			{
				if(completedMessageString.substring(i,i+1).matches("\\p{L}"))
				{
					lettersOnlyOfMessageString += completedMessageString.substring(i,i+1).toUpperCase();
				}
				if(lettersOnlyOfMessageString.length()>1&&lettersOnlyOfMessageString.length()<rankOfCurrentWord.length)
				{
					if(completedMessageString.substring(i,i+1).matches(" "))
					{
						rankOfCurrentWord[lettersOnlyOfMessageString.length()]=rankOfCurrentWord[lettersOnlyOfMessageString.length()-1]+1;
					}
					else
					{
						rankOfCurrentWord[lettersOnlyOfMessageString.length()]=rankOfCurrentWord[lettersOnlyOfMessageString.length()-1];
					}
				}
			}
			
			fortuneTableRow = new TableRow[numOfRows];
			upButton = new Button[numOfRows];
			downButton = new Button[numOfRows];
			correctOrdering = new int[numOfRows];
			holderOfLettersLinearLayout = new LinearLayout[numOfRows];
			// note the complicated "-1 and +1" accounts for integer division and is correct
			singleCharacterHolder = new LinearLayout[numOfRows][((lettersOnlyOfMessageString.length()-1)/numOfRows)+1];
			sCET = new EditText[numOfRows][((lettersOnlyOfMessageString.length()-1)/numOfRows)+1];
			
			// these are magic numbers here but the workaround would take considerable time and add confusion
			correctOrdering[0]=2;
			correctOrdering[1]=3;
			correctOrdering[2]=0;
			correctOrdering[3]=1;
			correctOrdering[4]=4;
			
			for(int i=0;i<numOfRows;i++)
			{
				inflateTableRow(i);
			}
			
			// add empty EditText to hold the Solution when the puzzle is solved
			solutionEditText = (EditText)findViewById(R.id.solutionEditText);
			
			for(int i=0;i<(lettersOnlyOfMessageString.length());i++)
			{
				inflateSingleCharacterHolder(i);
			}
				
			upButton[0].setVisibility(View.INVISIBLE);
			for(int i=1; i<numOfRows;i++)
			{
				upButton[i].setOnClickListener(new SwitchOnClickListener(i-1));
				upButton[i].setVisibility(View.VISIBLE);
			}
			
			downButton[numOfRows-1].setVisibility(View.INVISIBLE);
			for(int i=0;i<(numOfRows-1);i++)
			{
				downButton[i].setOnClickListener(new SwitchOnClickListener(i));
				downButton[i].setVisibility(View.VISIBLE);
			}
					
		}
   };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fortune_cookie_main, menu);
		return true;
	}
	
	private void inflateTableRow(int index)
	{
	      // get a reference to the LayoutInflater service
	      LayoutInflater inflater = (LayoutInflater) getSystemService(
	         Context.LAYOUT_INFLATER_SERVICE);

	      // inflate
	      fortuneTableRow[index] = (TableRow)inflater.inflate(R.layout.fortune_table_row, null);
	      
	      //get reference to holderOfLettersLinearLayout
	      holderOfLettersLinearLayout[index] = (LinearLayout)fortuneTableRow[index].findViewById(R.id.holderOfLettersLinearLayout);
	      	      
          // get upButton and downButton
          upButton[index] = (Button) fortuneTableRow[index].findViewById(R.id.upButton);
          downButton[index] = (Button) fortuneTableRow[index].findViewById(R.id.downButton);

          fortuneCookieTable.addView(fortuneTableRow[index]);
	}
	
	private void inflateSingleCharacterHolder(int whichChar)
	{
		int charN = (whichChar/numOfRows);
		int lineN = whichChar%numOfRows;
		
	      // get a reference to the LayoutInflater service
	      LayoutInflater inflater = (LayoutInflater) getSystemService(
	         Context.LAYOUT_INFLATER_SERVICE);
	      
	      // inflate
	      singleCharacterHolder[lineN][charN] = (LinearLayout)inflater.inflate(R.layout.fortune_cookie_single_character_linearlayout,null);
	      
	      // sCET doesn't need to be ADDED to singleCharacterHolder because it ALREADY BELONGS!  (this tripped me up for a while)
	      sCET[lineN][charN] = (EditText)singleCharacterHolder[lineN][charN].findViewById(R.id.singleCharacterEditText);
	      sCET[lineN][charN].setText(lettersOnlyOfMessageString.substring(lettersOnlyOfMessageString.length()-whichChar-1,lettersOnlyOfMessageString.length()-whichChar));
	      sCET[lineN][charN].setFreezesText(true);
	      if(rankOfCurrentWord[lettersOnlyOfMessageString.length()-whichChar-1]%numOfColors==0)
	      {
	    	  sCET[lineN][charN].setBackgroundColor(Color.parseColor("#EEE8AA"));
	      }
	      
		holderOfLettersLinearLayout[correctOrdering[lineN]].addView(singleCharacterHolder[lineN][charN]);
	}
	
	public class SwitchOnClickListener implements OnClickListener
	   {
		int ii;
	     LinearLayout temp;
	     int tempInt;
	     public SwitchOnClickListener(int i) {
	          ii=i;
	     }

	     @Override
	     public void onClick(View v)
	     {
	         tempInt=correctOrdering[ii];
	         correctOrdering[ii]=correctOrdering[ii+1];
	         correctOrdering[ii+1]=tempInt;
	         
	         // the following switches the lines... but it is somewhat slow.  I think calling the inflater 5 X 20 times is the problem.
	         LinearLayout temp = (LinearLayout)(fortuneTableRow[ii].getChildAt(1));
	         LinearLayout temp2 = (LinearLayout)(fortuneTableRow[ii+1].getChildAt(1));
	    	 fortuneTableRow[ii].removeAllViews();
	    	 fortuneTableRow[ii+1].removeAllViews();
	    	 
	    	 fortuneTableRow[ii].addView(upButton[ii]);
	    	 fortuneTableRow[ii].addView(temp2);
	    	 fortuneTableRow[ii].addView(downButton[ii]);
	    	 
	    	 fortuneTableRow[ii+1].addView(upButton[ii+1]);
	    	 fortuneTableRow[ii+1].addView(temp);
	    	 fortuneTableRow[ii+1].addView(downButton[ii+1]);
	         
	         int count=0;
	         while(count<numOfRows && count==(numOfRows-1-correctOrdering[count]))
	         {
	        	 count++;
	         }
	         
	         // check if solution has been achieved by solver
	         if(count==numOfRows)
	         {
	        	 //disable all the upArrow and downArrow buttons
	        	 for(int i=0;i<numOfRows;i++)
	        	 {
	        		 upButton[i].setEnabled(false);
	        		 downButton[i].setEnabled(false);
	        	 }
	        	 
	        	 transcribeMessage = (Button) findViewById(R.id.transcribeMessage);
	        	 transcribeMessage.setVisibility(View.VISIBLE);
	        	 transcribeMessage.setBackgroundColor(Color.parseColor("#AAFFAA"));
	        	 transcribeMessage.setOnClickListener(transcribeListener);
	         }
	     }

	  };
	  
	   public OnClickListener fortuneCookieHintListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  if(fortuneCookieHintButton.getText().equals("Get\nHint"))
	    	  {
	    		  fortuneCookieHintButton.setText("Hide\nHint");
	    		  fortuneCookieHintEditText.setVisibility(View.VISIBLE);
	    	  }
	    	  else
	    	  {
	    		  fortuneCookieHintButton.setText("Get\nHint");
	    		  fortuneCookieHintEditText.setVisibility(View.INVISIBLE);
	    	  }
	      }
	      
	   };
	  
	   public OnClickListener transcribeListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  transcribeMessage.setVisibility(View.INVISIBLE);
	    	  solutionEditText.setVisibility(View.VISIBLE);
	    	  solutionEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));
	    	  
	    	  // the following block was all taken out of TimerTask for testing (and maybe should be replaced.)
	    	  solutionEditText.setText(completedMessageString);
	    	  fortuneCookieImageView.setVisibility(View.INVISIBLE);
	    	  timer.cancel();
	    	  waitressReturnsButton = (Button)findViewById(R.id.enterUC);
	    	  waitressReturnsButton.setVisibility(View.VISIBLE);
	    	  waitressReturnsButton.setOnClickListener(waitressReturnsListener);
	    	  waitressReturnsButton.setBackgroundColor(Color.parseColor("#FFFF00"));

			   /*TimerTask updateMessage = new UpdateMessageTask();
			   timer.scheduleAtFixedRate(updateMessage, 0, 200);*/
	      }
	   };
	   
	   public OnClickListener waitressReturnsListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  fortuneCookieTable.setVisibility(View.INVISIBLE);
	    	  EditText confuciusEditText=(EditText)findViewById(R.id.confuciusEditText);
	    	  confuciusEditText.setVisibility(View.INVISIBLE);
	    	  fortuneCookieHintButton.setVisibility(View.INVISIBLE);
	    	  waitressReturnsString = getResources().getString(R.string.waitress_returns_string);
	    	  solutionEditText.setText(waitressReturnsString);
	    	  enterUCButton = waitressReturnsButton;
	    	  enterUCButton.setOnClickListener(goToPanoramaListener);
	      }
	   };
	   class UpdateMessageTask extends TimerTask {

		   public void run() {
			   /*if(lettersRevealed<completedMessageString.length())
			   {
				   solutionEditText.setText(completedMessageString.substring(0,lettersRevealed));
				   lettersRevealed++;
			   }
			   else
			   {*/

			   }
			   
		   /*}*/
		}
	   public OnClickListener goToPanoramaListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  emptyContainerLayout.removeAllViews();
	    	  emptyContainerLayout.setBackgroundColor(Color.TRANSPARENT);
	    	  buttonsDuringPanoramaView = getLayoutInflater().inflate(R.layout.buttons_during_panorama_table_layout,null);
	    	  RelativeLayout.LayoutParams actualParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
	    	  actualParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	    	  buttonsDuringPanoramaView.setLayoutParams(actualParams);
	    	  emptyContainerLayout.addView(buttonsDuringPanoramaView);
	    	  panFortuneButton = (Button)findViewById(R.id.panFortune);
	    	  panFortuneButton.setOnClickListener(revealFortuneListener);
	    	  panPhoneBookButton = (Button)findViewById(R.id.panPhoneBookButton);
	    	  panPhoneBookButton.setOnClickListener(activatePhoneBookListener);
	    	  panMessageRevealed=(EditText)findViewById(R.id.panMessageRevealed);
	    	  theCrumpledMap=(Button)findViewById(R.id.panCrumpledMap);
	    	  theCrumpledMap.setOnClickListener(crumpledListener);
	    	  theMap = (Button)findViewById(R.id.panMap);
	  		  theMap.setOnClickListener(activateMap);
	  		  theLetter=(Button)findViewById(R.id.panLoungeLetter);
	  		  theLetter.setOnClickListener(seeLetter);
	  		  theGhostLeg=(Button)findViewById(R.id.panGhostLeg);
	  		  theGhostLeg.setOnClickListener(ghostLegListener);
	  		  theSolution=(Button)findViewById(R.id.panEnterSolution);
	  		  theSolution.setOnClickListener(theSolutionListener);
	  		  theNotes=(Button)findViewById(R.id.panWriteNotes);
	  		  theNotes.setOnClickListener(theNotesListener);
	      }
	      
	   };
	   public OnClickListener revealFortuneListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  if(panFortuneButton.getText().equals("Show\nCookie\nMessage"))
	    	  {
		    	  panMessageRevealed.setVisibility(View.VISIBLE);
		    	  panFortuneButton.setText("Hide\nMessage");
	    	  }
	    	  else
	    	  {
	    		  panMessageRevealed.setVisibility(View.INVISIBLE);
	    		  panFortuneButton.setText("Show\nCookie\nMessage");
	    	  }
	      }
	   };
	   
	   public OnClickListener activatePhoneBookListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.PhoneBookMain(MyApplication.getContext());
	      }      
	   };
	   
	   public OnClickListener crumpledListener = new OnClickListener()
	   {
		      @Override
		      public void onClick(View v) 
		      {
		    	  PuzzleMethods.CrumpledMap(MyApplication.getContext());
		      }
	   };
	   
	   public OnClickListener activateMap = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.Map(MyApplication.getContext());
	      }      
	   };
	   
	   public OnClickListener seeLetter = new OnClickListener()
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.Envelope(MyApplication.getContext());
	      }      
	   };
	   public OnClickListener ghostLegListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.GhostLeg(MyApplication.getContext());
	      }      
	   };
	   public OnClickListener theSolutionListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.ExitScreen(MyApplication.getContext());
	      }      
	   };
	   public OnClickListener theNotesListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  PuzzleMethods.Notes(MyApplication.getContext());
	      }      
	   };
	 
	 public void startNewActivity(Intent intent) {
         startActivity(intent);
	 }
 }
	 
	