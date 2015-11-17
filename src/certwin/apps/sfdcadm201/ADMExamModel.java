package certwin.apps.sfdcadm201;

import java.util.ArrayList;
import java.util.List;

import certwin.apps.sfdcadm201.data.TestAnswer;
import certwin.apps.sfdcadm201.data.TestQuestion;
import certwin.apps.sfdcadm201.data.UserAnswer;

public class ADMExamModel
{
	public static final int MAX_TEST_QUESTIONS=70;
	private TestQuestion [] testQuestions;
	private UserAnswer [] userAnswers;
	List <Integer> wrongAnswersIndexList;
	private int questionIterator;
	private int wrongAnswerIndex;
	
	public ADMExamModel()
	{
		questionIterator=0;
		testQuestions = new TestQuestion [MAX_TEST_QUESTIONS];
		userAnswers = new UserAnswer[MAX_TEST_QUESTIONS];
		for (int i=0; i<MAX_TEST_QUESTIONS; i++)
		{
			testQuestions[i] = new TestQuestion();
			userAnswers[i] = new UserAnswer();
		}
		wrongAnswersIndexList=new ArrayList <Integer>();
		//generateTestQuestions();
	}
	
	public void setUserAnswer (int answerIndex, boolean isCorrect)
	{
		if (userAnswers!=null && userAnswers.length>questionIterator && UserAnswer.MAX_ANSWERS>answerIndex )
		{
			userAnswers[questionIterator].setAnswer(answerIndex, isCorrect);
		}
	}
	
	public void setNext()
	{
		if (questionIterator<MAX_TEST_QUESTIONS)
		{
			
			questionIterator++;
		}
	}
	
	public void setPrevious()
	{
		if (questionIterator>0)
		{
			questionIterator--;
		}
	}
	
	public void setFirst()
	{
		questionIterator=0;
	}
	
	public void setNextWrong()
	{
		if (wrongAnswerIndex<wrongAnswersIndexList.size()-1)
		{
			wrongAnswerIndex++;
			questionIterator=wrongAnswersIndexList.get(wrongAnswerIndex).intValue();
		}
	}
	
	public void setPreviousWrong()
	{
		if (wrongAnswerIndex>0)
		{
			wrongAnswerIndex--;
			questionIterator=wrongAnswersIndexList.get(wrongAnswerIndex).intValue();
		}
	}
	
	public void setFirstWrong()
	{
		if (wrongAnswersIndexList!=null && wrongAnswersIndexList.size()!=0)
		{
			wrongAnswerIndex=0;
			questionIterator=wrongAnswersIndexList.get(0).intValue();
		}
	}
	
	public void setTestQuestions(TestQuestion [] testQuestions)
	{
		if (testQuestions!=null)
		{
			this.testQuestions=testQuestions;
		}
	}
	
	public void checkAnswers()
	{
		wrongAnswersIndexList=new ArrayList <Integer>();
		for (int i=0; i<MAX_TEST_QUESTIONS ; i++)
		{
			for (int j=0; j<TestQuestion.MAX_ANSWERS ;j++)
			{
				if (testQuestions[i].isCorrectAnswer(j)!=userAnswers[i].getAnswer(j))
				{
					wrongAnswersIndexList.add(new Integer(i));
					break;
				}
			}
		}
	}
	
	public List <Integer> getWrongAnswersIndexList()
	{
		return this.wrongAnswersIndexList;
	}
	
	public boolean isCorrectAnswer(int index)
	{
		return testQuestions[questionIterator].isCorrectAnswer(index);
	}
	
	public int getIterator()
	{
		return questionIterator;
	}
	
	public String getQuestion()
	{
		String question="";
		if (testQuestions!=null && testQuestions.length>questionIterator)
		{
			question = testQuestions[questionIterator].getQuestion();
		}
		return question;
	}
	
	public String getAnswer(int answerIndex)
	{
		String answer="";
		if (testQuestions!=null && testQuestions.length>questionIterator && TestQuestion.MAX_ANSWERS>answerIndex)
		{
			answer = testQuestions[questionIterator].getAnswer(answerIndex);
		}
		return answer;
	}
	
	public String getAnswerName(int answerIndex)
	{
		String answerName="";
		if (testQuestions!=null && testQuestions.length>questionIterator && TestQuestion.MAX_ANSWERS>answerIndex)
		{
			answerName = testQuestions[questionIterator].getAnswerName(answerIndex);
		}
		return answerName;
	}
	
	public boolean getUserAnswer (int answerIndex)
	{
		boolean userAnswer=false;
		if (userAnswers!=null && userAnswers.length>questionIterator && UserAnswer.MAX_ANSWERS>answerIndex )
		{
			userAnswer=userAnswers[questionIterator].getAnswer(answerIndex);
		}
		return userAnswer;
	}
	
	public String getExplanation()
	{
		String explenation="";
		if (testQuestions!=null && testQuestions.length>questionIterator)
		{
			explenation=testQuestions[questionIterator].getExplanation();
		}
		return explenation;
	}
	
	public boolean isMultipleChoice()
	{
		boolean multipleChoice=false;
		if (testQuestions!=null && testQuestions.length>questionIterator)	
		{
			multipleChoice=testQuestions[questionIterator].isMultipleChoice();
		}
		return multipleChoice;
	}
	
	public boolean isUserAnswerSet()
	{
		boolean userAnserSet=false;
		if (userAnswers!=null && userAnswers.length>questionIterator)
		{
			for (int i=0; i<UserAnswer.MAX_ANSWERS; i++)
			{
				if (userAnswers[questionIterator].getAnswer(i)==true)
				{
					userAnserSet=true;
				}
			}
		}
		return userAnserSet;
	}
	
	public boolean isUserAnswerCorrect()
	{
		boolean userAnswerCorrect=true;
		if (wrongAnswersIndexList!=null)
		{
			for (int i=0; i<wrongAnswersIndexList.size(); i++)
			{
				if (questionIterator==wrongAnswersIndexList.get(i).intValue())
				{
					userAnswerCorrect=false;
				}
			}
		}
		return userAnswerCorrect;
	}
	
	/* for testing method */
    private void generateTestQuestions()
    {
    	TestAnswer [] testAnswers = new TestAnswer[TestQuestion.MAX_ANSWERS];
		for (int j=0; j<TestQuestion.MAX_ANSWERS; j++)
		{
			testAnswers[j] = new TestAnswer();
			testAnswers[j].setAnswer("Test answer "+(j+1)+".");
		}
		
    	for (int i=0; i<MAX_TEST_QUESTIONS; i++)
    	{
    		testQuestions[i].setQuestion("Test question "+(i+1)+".");
    		testQuestions[i].setTestAnswers(testAnswers);
    		testQuestions[i].setAnswerNames();
    		if (i%2==0)
    		{
    			testQuestions[i].setMultipleChoice(false);
    			testQuestions[i].getTestAnswer(0).setCorrectAnswer(true);
    		}
    		else
    		{
    			testQuestions[i].setMultipleChoice(true);
    			testQuestions[i].getTestAnswer(2).setCorrectAnswer(true);
    			testQuestions[i].getTestAnswer(3).setCorrectAnswer(true);
    		}
    	}
    }
}
