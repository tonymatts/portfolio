//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Notes extends Activity
{
	private Button foldNotes;
	EditText notesEditText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_main); 
        
        notesEditText=(EditText)findViewById(R.id.notesEditText);
        notesEditText.setText(PanoramaGLActivity.keptNotes);
        
        foldNotes = (Button) findViewById(R.id.foldNotesButton);
        foldNotes.setOnClickListener(foldNotesListener);
    }
    private OnClickListener foldNotesListener = new OnClickListener() {
		
		public void onClick(View v) {
			PanoramaGLActivity.keptNotes = notesEditText.getText().toString();
			finish();
		}
    };
}
