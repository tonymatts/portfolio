//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Envelope extends Activity
{
	private Button readNextPage,readPrevPage,doneReading;
	TextView readAPageTextView;
	String [] currentPage;
	int currentPageIndex=0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.envelope_main); 
        
        currentPage = getResources().getStringArray(R.array.read_a_page);
        readAPageTextView=(TextView)findViewById(R.id.readAPageTextView);
        readAPageTextView.setText(currentPage[currentPageIndex]);
        
        doneReading = (Button) findViewById(R.id.doneReadingButton);
        doneReading.setOnClickListener(doneReadingListener);
        readNextPage=(Button)findViewById(R.id.readNextPageButton);
        readNextPage.setOnClickListener(readNextPageListener);
        readPrevPage=(Button)findViewById(R.id.readPrevPageButton);
        readPrevPage.setOnClickListener(readPrevPageListener);
    }
    private OnClickListener readNextPageListener = new OnClickListener() {
		
		public void onClick(View v) {
			currentPageIndex++;
			readPrevPage.setVisibility(View.VISIBLE);
			if(currentPageIndex==currentPage.length-1)
			{
				readNextPage.setVisibility(View.INVISIBLE);
			}
	        readAPageTextView.setText(currentPage[currentPageIndex]);
		}
    };
    private OnClickListener readPrevPageListener = new OnClickListener() {
		
		public void onClick(View v) {
			currentPageIndex--;
			readNextPage.setVisibility(View.VISIBLE);
			if(currentPageIndex==0)
			{
				readPrevPage.setVisibility(View.INVISIBLE);
			}
	        readAPageTextView.setText(currentPage[currentPageIndex]);
		}
    };
    private OnClickListener doneReadingListener = new OnClickListener() {
		
		public void onClick(View v) {
			PanoramaGLActivity.theLetter.setVisibility(View.VISIBLE);
			finish();
		}
    };
}