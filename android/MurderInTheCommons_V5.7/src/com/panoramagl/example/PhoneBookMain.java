//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhoneBookMain extends Activity implements OnTouchListener{
	private ImageView phoneBookImageView;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Button b7;
	private Button b8;
	
	private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f,MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phonebookmain);
		phoneBookImageView = (ImageView) findViewById(R.id.phoneBookImageView);
        //setting image resource
        phoneBookImageView.setImageResource(R.drawable.phonebookcover);
        
        // ZOOM LISTENER  (I am not sure about the "this" in the next line)
        phoneBookImageView.setOnTouchListener(this);
        
        // set up the button listeners
        b1 = (Button)findViewById(R.id.prevFlier);
        b2 = (Button)findViewById(R.id.nextFlier);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        b7 = (Button)findViewById(R.id.button7);
        b8 = (Button)findViewById(R.id.button8);
        
        b1.setOnClickListener(choosePageButtonListener);
        b2.setOnClickListener(choosePageButtonListener);
        b3.setOnClickListener(choosePageButtonListener);
        b4.setOnClickListener(choosePageButtonListener);
        b5.setOnClickListener(choosePageButtonListener);
        b6.setOnClickListener(choosePageButtonListener);
        b7.setOnClickListener(choosePageButtonListener);
        b8.setOnClickListener(choosePageButtonListener);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private OnClickListener choosePageButtonListener = new OnClickListener()
	{
	      @Override
	      public void onClick(View v) 
	      {
	    	  String newlyChosenPage = ((Button)v).getText().toString();
	    	  System.out.println(newlyChosenPage+" "+newlyChosenPage.substring(0,1));
	    	  if(newlyChosenPage.substring(0,1).equals("A"))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookanagram);
	    	  if(newlyChosenPage.substring(0,1).equals("B"))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookbailbonds);
	    	  if(newlyChosenPage.substring(0,1).equals("C"))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookcover);
	    	  if(newlyChosenPage.substring(0,1).equals("D"))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookdiagram);
	    	  if(newlyChosenPage.substring(0,1).equals("F"))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookfish);
	    	  if(newlyChosenPage.substring(0,1).equals("P"))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookpalindrome);
	    	  if(newlyChosenPage.substring(0,2).equals("R "))
	    		  phoneBookImageView.setImageResource(R.drawable.phonebookrhetorical);
	    	  if(newlyChosenPage.substring(0,2).equals("Re"))
	    	  {
	    		  PanoramaGLActivity.panPhoneBookButton.setVisibility(View.VISIBLE);
	    		  finish();
	    	  }
	      }
	};
	
	// ZOOM ACTIONS BELOW.  THE REMAINDER OF THIS CODE IS ZOOM-RELATED
    @Override
    public boolean onTouch(View v, MotionEvent event) 
    {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        dumpEvent(event);
        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK) 
        {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                                                savedMatrix.set(matrix);
                                                start.set(event.getX(), event.getY());
                                                Log.d(TAG, "mode=DRAG"); // write to LogCat
                                                mode = DRAG;
                                                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                                                mode = NONE;
                                                Log.d(TAG, "mode=NONE");
                                                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                                                oldDist = spacing(event);
                                                Log.d(TAG, "oldDist=" + oldDist);
                                                if (oldDist > 5f) {
                                                    savedMatrix.set(matrix);
                                                    midPoint(mid, event);
                                                    mode = ZOOM;
                                                    Log.d(TAG, "mode=ZOOM");
                                                }
                                                break;

            case MotionEvent.ACTION_MOVE:

                                                if (mode == DRAG) 
                                                { 
                                                    matrix.set(savedMatrix);
                                                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
                                                } 
                                                else if (mode == ZOOM) 
                                                { 
                                                    // pinch zooming
                                                    float newDist = spacing(event);
                                                    Log.d(TAG, "newDist=" + newDist);
                                                    if (newDist > 5f) 
                                                    {
                                                        matrix.set(savedMatrix);
                                                        scale = newDist / oldDist; // setting the scaling of the
                                                                                    // matrix...if scale > 1 means
                                                                                    // zoom in...if scale < 1 means
                                                                                    // zoom out
                                                        matrix.postScale(scale, scale, mid.x, mid.y);
                                                    }
                                                }
                                                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }

    /*
     * --------------------------------------------------------------------------
     * Method: spacing Parameters: MotionEvent Returns: float Description:
     * checks the spacing between the two fingers on touch
     * ----------------------------------------------------
     */

    private float spacing(MotionEvent event) 
    {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return FloatMath.sqrt(x * x + y * y);
    }

    /*
     * --------------------------------------------------------------------------
     * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
     * Description: calculates the midpoint between the two fingers
     * ------------------------------------------------------------
     */

    private void midPoint(PointF point, MotionEvent event) 
    {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /** Show an event in the LogCat view, for debugging */
    private void dumpEvent(MotionEvent event) 
    {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE","POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) 
        {
            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }

        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) 
        {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }

        sb.append("]");
        Log.d("Touch Events ---------", sb.toString());
    }


}
