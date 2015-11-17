package certwin.apps.sfdcadm201;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

import certwin.apps.sfdcadm201.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class ADMExam extends Activity
{
	
	EasyTracker easyTracker;

	
    private Tracker mGaTracker;
    private GoogleAnalytics mGaInstance;

	
	ADMExamActivity admexamActivity;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      
        mGaInstance = GoogleAnalytics.getInstance(this);

        // Use the GoogleAnalytics singleton to get a Tracker.
        mGaTracker = mGaInstance.getTracker("UA-42633838-3"); // Placeholder tracking ID.
        
        
        TextView myTextView=(TextView)findViewById(R.id.TextView01);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
         myTextView.setTypeface(typeFace);
        
         
        Button startQuizButton = (Button) findViewById(R.id.startQuizButton);
        Button optionQuizButton = (Button) findViewById(R.id.optionsButton);
        Button exitQuizButton = (Button) findViewById(R.id.exitButton);
        
        
        
        startQuizButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View view)
        	{
        		
        		

        		
            	Intent myIntent = new Intent(view.getContext(), ADMExamActivity.class);
            	
            	startActivityForResult(myIntent, 0);
            	
           
      		  
        	}
        });
        
        optionQuizButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                    Intent settingsActivity = new Intent(getBaseContext(),ADMExamPreferences.class);
                    startActivity(settingsActivity);
            }
        });
        
        exitQuizButton.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View view)
        	{
        		
                Intent intent = new Intent();
                onStop();
                setResult(RESULT_OK, intent);
               
                finish();
        	}
        });
        
        
        
   

    }
    
    
    @Override
    public void onStart() {
      super.onStart();
    
      EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
      super.onStop();
      
      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
  
}
