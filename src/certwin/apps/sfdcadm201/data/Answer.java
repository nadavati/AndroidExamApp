package certwin.apps.sfdcadm201.data;

public class Answer
{
	private boolean isCorrect;
	
	public Answer()
	{
		isCorrect=false;
	}
	
	public void setCorrectAnswer(boolean isCorrect)
	{
		this.isCorrect=isCorrect;
	}
	
	public boolean isCorrectAnswer()
	{
		return isCorrect;
	}
}
