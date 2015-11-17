package certwin.apps.sfdcadm201;


import java.util.ArrayList;

import certwin.apps.sfdcadm201.data.ExamXmlParser;
import certwin.apps.sfdcadm201.data.TestAnswer;
import certwin.apps.sfdcadm201.data.TestQuestion;

import certwin.apps.sfdcadm201.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ADMExamActivity extends Activity
{
    Button previousButton;
    Button nextButton;
    ADMExamModel admexamModel;
    TextView testQuestion;
    TextView testExplenation;
    CheckBox answersCheckBox [];
    RadioButton answersRadioButton [];
    RadioGroup radioGroupSing;
    RadioGroup radioGroupMult;
    TextView text;
	 
    private Tracker mGaTracker;
    private GoogleAnalytics mGaInstance;
    
    
 RadioButton AnswerARadioButton,AnswerBRadioButton,AnswerCRadioButton,AnswerDRadioButton;
 CheckBox AnswerACheckBox, AnswerBCheckBox, AnswerCCheckBox, AnswerDCheckBox;
    
    ExamXmlParser quizXmlParser;
    boolean isCheckMode;
    boolean wronAnswersOnly;
	
    public ADMExamActivity()
    {
		admexamModel=new ADMExamModel();
		answersCheckBox = new CheckBox [TestQuestion.MAX_ANSWERS];
		answersRadioButton = new RadioButton [TestQuestion.MAX_ANSWERS];
		isCheckMode=false;
		wronAnswersOnly=false;
    }
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testquestion);
        
        
     	
     		
        
        mGaInstance = GoogleAnalytics.getInstance(this);

        // Use the GoogleAnalytics singleton to get a Tracker.
        mGaTracker = mGaInstance.getTracker("UA-42633838-3"); // Placeholder tracking ID.
        
        
        AnswerARadioButton = (RadioButton)findViewById(R.id.AnswerARadioButton);
        Typeface radioA=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerARadioButton.setTypeface(radioA);
        
        AnswerBRadioButton = (RadioButton)findViewById(R.id.AnswerBRadioButton);
        Typeface radioB=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerBRadioButton.setTypeface(radioB);
        
        AnswerCRadioButton = (RadioButton)findViewById(R.id.AnswerCRadioButton);
        Typeface radioC=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerCRadioButton.setTypeface(radioC);
       
        AnswerDRadioButton = (RadioButton)findViewById(R.id.AnswerDRadioButton);
        Typeface radioD=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerDRadioButton.setTypeface(radioD);
        
        
        AnswerACheckBox = (CheckBox)findViewById(R.id.AnswerACheckBox);
        Typeface checkA=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerACheckBox.setTypeface(checkA);
        
        AnswerBCheckBox = (CheckBox)findViewById(R.id.AnswerBCheckBox);
        Typeface checkB=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerBCheckBox.setTypeface(checkB);
	

        AnswerCCheckBox = (CheckBox)findViewById(R.id.AnswerCCheckBox);
        Typeface checkC=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerCCheckBox.setTypeface(checkC);
        
        
        AnswerDCheckBox = (CheckBox)findViewById(R.id.AnswerDCheckBox);
        Typeface checkD=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        AnswerDCheckBox.setTypeface(checkD);
        
        
		testQuestion = (TextView)findViewById(R.id.QuestionTextView);
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
	    testQuestion.setTypeface(typeFace);
	
	    testExplenation = (TextView)findViewById(R.id.ExplenationTextView);
		Typeface typeFace1=Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
	    testExplenation.setTypeface(typeFace1);
		
	    testExplenation.setVisibility(View.GONE);
		
	    radioGroupMult = (RadioGroup)findViewById(R.id.RadioGroupMult);
	    radioGroupSing = (RadioGroup)findViewById(R.id.RadioGroupSing);
		
		
        previousButton = (Button)findViewById(R.id.PreviousSing);
        nextButton = (Button)findViewById(R.id.NextSing);
        
       // text = (TextView)findViewById(R.id.textView);
       // ArrayList<Integer> numbers = new ArrayList<Integer>();
       // for(int i = 0; i < 4; i++)
        //{
         // numbers.add(i+1);
 
       //text.setText(String.valueOf(numbers));
    //  
       
        
        
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String lpicExameType = preferences.getString("list_lpic_exame", "101");
        wronAnswersOnly = preferences.getBoolean("wrong_answers_only", false);
        
		quizXmlParser = new ExamXmlParser();
		quizXmlParser.getData(getApplicationContext(),lpicExameType);
		admexamModel.setTestQuestions(quizXmlParser.getRandomTestQuestions());
		
		
		
		

        
    	if (admexamModel.isMultipleChoice())
    	{
    		setMultipleChoiceView();
    	}
    	else
    	{
    		setSingleChoiceView();
    	}
        
        previousButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	setUserAnswers();
            	if (isCheckMode && wronAnswersOnly==true)
            	{
            		admexamModel.setPreviousWrong();
            	}
            	else
            	{
            		admexamModel.setPrevious();
            	}
            	if (admexamModel.isMultipleChoice())
            	{
            		setMultipleChoiceView();
            	}
            	else
            	{
            		setSingleChoiceView();
            	}
            	if (isCheckMode)
            	{
            		testExplenation.setText(admexamModel.getExplanation());
            	}
            	nextButton.setText(R.string.next);
            }
        });
        
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	if (admexamModel.getIterator()<admexamModel.MAX_TEST_QUESTIONS-1)
            	{
            		setUserAnswers();
                	if (isCheckMode && wronAnswersOnly==true)
                	{
                		admexamModel.setNextWrong();
                		
                	}
                	else
                	{
                		admexamModel.setNext();
            		}
                	
                	
            		if (admexamModel.getIterator()==admexamModel.MAX_TEST_QUESTIONS-1)
            		{
            			nextButton.setText(R.string.finish);
            		}
	            	if (admexamModel.isMultipleChoice())
	            	{
	            		setMultipleChoiceView();
	            	}
	            	else
	            	{
	            		setSingleChoiceView();
	            	}
	            	if (isCheckMode)
	            	{
	            		testExplenation.setText(admexamModel.getExplanation());
	            	}
            	}
            	else
            	{
            		setUserAnswers();
        			admexamModel.checkAnswers();
        			int numberOfWrongAnswers = admexamModel.getWrongAnswersIndexList().size();
        			double result=(double)(admexamModel.MAX_TEST_QUESTIONS-numberOfWrongAnswers)/(double)admexamModel.MAX_TEST_QUESTIONS;
        			new AlertDialog.Builder(view.getContext())
        			.setTitle("Exam is finished")
        			
        			.setMessage("Result: "+(int)(result*100)+"%\n"+"Wrong answers: "+numberOfWrongAnswers+"\nRight answers: "+(admexamModel.MAX_TEST_QUESTIONS-numberOfWrongAnswers))
        			.setPositiveButton("Review answers", new DialogInterface.OnClickListener()
        			{
        				public void onClick(DialogInterface dialog, int whichButton)
        				{
                			if (wronAnswersOnly==true && admexamModel.getWrongAnswersIndexList().size()>0)
                			{
                				admexamModel.setFirstWrong();
                			}
                			else
                			{
            					admexamModel.setFirst();
                			}
        					// Review Start Here
                			isCheckMode=true;
                			testExplenation.setText(admexamModel.getExplanation());
                			testExplenation.setVisibility(View.VISIBLE);
                			nextButton.setText(R.string.next);
                			setRigtAnswers();
                			setNotEditable();
                			
                        	if (admexamModel.isMultipleChoice())
                        	{
                        		setMultipleChoiceView();
                        	}
                        	else
                        	{
                        		setSingleChoiceView();
                        	}
        				}
        			})
        			.setNegativeButton("Finish Exam", new DialogInterface.OnClickListener()
        			{
        				public void onClick(DialogInterface dialog, int whichButton)
        				{
                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            
                            finish();
        				}
        			})
        			.show();
        			
        			
            	}
            }
        });
    }
	
    private void setRigtAnswers()
    {
    	if (admexamModel.isMultipleChoice())
    	{
			for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
			{
				if (admexamModel.isCorrectAnswer(i))
				{
					answersCheckBox[i].setTextColor(android.graphics.Color.RED);
				}
				else
				{
					answersCheckBox[i].setTextColor(android.graphics.Color.BLACK);
				}
			}
    	}
    	else
    	{
			for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
			{
				if (admexamModel.isCorrectAnswer(i))
				{
					answersRadioButton[i].setTextColor(android.graphics.Color.RED);
				}
				else
				{
					answersRadioButton[i].setTextColor(android.graphics.Color.BLACK);
				}
			}
    	}
    }
    
    private void setNotEditable()
    {
    	for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
    	{
    		answersRadioButton[i].setEnabled(false);
    		answersCheckBox[i].setEnabled(false);
    	}
    }
    
    private void setSingleChoiceView()
    {
    	testQuestion.setText(Html.fromHtml((admexamModel.getIterator()+1)+". "+admexamModel.getQuestion()));
    	radioGroupSing = (RadioGroup)findViewById(R.id.RadioGroupSing);
    	radioGroupMult.setVisibility(View.GONE);
		radioGroupSing.setVisibility(View.VISIBLE);
		answersRadioButton[0] = (RadioButton)findViewById(R.id.AnswerARadioButton);
		answersRadioButton[1] = (RadioButton)findViewById(R.id.AnswerBRadioButton);
		answersRadioButton[2] = (RadioButton)findViewById(R.id.AnswerCRadioButton);
		answersRadioButton[3] = (RadioButton)findViewById(R.id.AnswerDRadioButton);
		radioGroupSing.clearCheck();
		
		for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
		{
			answersRadioButton[i].setText(admexamModel.getAnswerName(i) +") "+admexamModel.getAnswer(i));
		}
		
		for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
		{
			if (admexamModel.getUserAnswer(i)==true)
			{
				answersRadioButton[i].setChecked(true);
				break;
			}
			else
			{
				answersRadioButton[i].setChecked(false);
			}
		}
		
		if (isCheckMode)
		{
			setRigtAnswers();
		}
    }
    
    private void setMultipleChoiceView()
    {
    	testQuestion.setText(Html.fromHtml((admexamModel.getIterator()+1)+". "+admexamModel.getQuestion()));
    	radioGroupMult = (RadioGroup)findViewById(R.id.RadioGroupMult);
    	radioGroupSing.setVisibility(View.GONE);
		radioGroupMult.setVisibility(View.VISIBLE);
		answersCheckBox[0] = (CheckBox)findViewById(R.id.AnswerACheckBox);
		answersCheckBox[1] = (CheckBox)findViewById(R.id.AnswerBCheckBox);
		answersCheckBox[2] = (CheckBox)findViewById(R.id.AnswerCCheckBox);
		answersCheckBox[3] = (CheckBox)findViewById(R.id.AnswerDCheckBox);
		radioGroupMult.clearCheck();
		
		for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
		{
			answersCheckBox[i].setText(admexamModel.getAnswerName(i) +") "+admexamModel.getAnswer(i));
			if (admexamModel.getUserAnswer(i)==true)
			{
				answersCheckBox[i].setChecked(true);
			}
			else
			{
				answersCheckBox[i].setChecked(false);
			}
			
		}
		
		if (isCheckMode)
		{
			setRigtAnswers();
		}
    }
    
    private void setUserAnswers()
    {
    	if (admexamModel.isMultipleChoice())
    	{
	    	for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
	    	{
	    		admexamModel.setUserAnswer(i, answersCheckBox[i].isChecked());
	    	}
    	}
    	else
    	{
	    	for (int i=0; i<TestQuestion.MAX_ANSWERS; i++)
	    	{
	    		admexamModel.setUserAnswer(i, answersRadioButton[i].isChecked());
	    	}
    	}
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
