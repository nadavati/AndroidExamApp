package certwin.apps.sfdcadm201.data;

public class UserAnswer
{
	public static final int MAX_ANSWERS=4;
	private Answer [] answers;
	
	public UserAnswer()
	{
		answers = new Answer [MAX_ANSWERS];
		for (int i=0; i<MAX_ANSWERS ; i++)
		{
			answers[i]=new Answer();
		}
	}
	
	public void setAnswer (int index, boolean isCorrect)
	{
		if (answers!=null && answers.length>=index)
		{
			answers[index].setCorrectAnswer(isCorrect);
		}
	}
	
	public boolean getAnswer (int index)
	{
		boolean answer = false;
		if (answers!=null && answers.length>=index)
		{
			answer = answers[index].isCorrectAnswer();
		}
		return answer;
	}
}
