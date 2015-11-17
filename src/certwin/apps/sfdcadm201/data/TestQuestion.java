package certwin.apps.sfdcadm201.data;

public class TestQuestion
{
	public static final int MAX_ANSWERS = 4;
	private boolean isMultiple;
	private TestAnswer [] testAnswers;
	private String question;
	private String explenation;
	
	public TestQuestion ()
	{
		isMultiple=false;
		explenation="";
		question="";
		testAnswers=new TestAnswer [MAX_ANSWERS];
		for (int i=0; i<MAX_ANSWERS; i++)
		{
			testAnswers[i]=new TestAnswer();
		}
		setAnswerNames();
	}
	
	public void setMultipleChoice (boolean isMultiple)
	{
		this.isMultiple=isMultiple;
	}
	
	public void setQuestion (String question)
	{
		this.question=question;
	}
	
	public void setExplanation (String explenation)
	{
		this.explenation=explenation;
	}
	
	public boolean isMultipleChoice()
	{
		return isMultiple;
	}	

	public boolean isCorrectAnswer(int index)
	{
		boolean isCorrect = false;
		if (testAnswers.length>index)
		{
			isCorrect=testAnswers[index].isCorrectAnswer();
		}
		return isCorrect;
	}
	
	public void setAnswers (int index, String answer)
	{
		if (testAnswers!=null && testAnswers.length>index)
		{
			testAnswers[index].setAnswer(answer);
		}
	}
	
	public void setAnswers (TestAnswer [] testAnswers)
	{
		if (testAnswers!=null && testAnswers.length==this.testAnswers.length)
		{
			this.testAnswers=testAnswers;
		}
	}
	
	public void setTestAnswer (int index, TestAnswer testAnswer)
	{
		if (testAnswer!=null && testAnswers.length>index)
		{
			testAnswers[index]=testAnswer;
		}
	}
	
	public void setTestAnswers (TestAnswer [] testAnswers)
	{
		this.testAnswers=testAnswers;
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public String getExplanation()
	{
		return explenation;
	}
	
	public String getAnswer (int index)
	{
		String answer="";
		if (testAnswers!=null && testAnswers.length>index)
		{
			answer = testAnswers[index].getAnswer();
		}
		return answer;
	}
	
	public String getAnswerName (int index)
	{
		String answerName="";
		if (testAnswers!=null && testAnswers.length>index)
		{
			answerName = testAnswers[index].getAnswerName();
		}
		return answerName;
	}
	
	public TestAnswer getTestAnswer(int index)
	{
		TestAnswer testAnswer = new TestAnswer();
		if (testAnswers!=null && testAnswers.length>index)
		{
			testAnswer = testAnswers[index];
		}
		return testAnswer;
	}
	
	public TestAnswer [] getTestAnswers()
	{
		return testAnswers;
	}
	
	public void setAnswerNames()
	{
		if (testAnswers!=null && testAnswers.length>=MAX_ANSWERS)
		{
			testAnswers[0].setName("A");
			testAnswers[1].setName("B");
			testAnswers[2].setName("C");
			testAnswers[3].setName("D");
		}
	}
	
	public void setAnswerName(int index, String name)
	{
		if (testAnswers!=null && testAnswers.length>index)
		{
			testAnswers[index].setName(name);
		}
	}

	
}
