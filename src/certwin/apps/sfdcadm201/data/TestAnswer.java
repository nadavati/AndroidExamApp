package certwin.apps.sfdcadm201.data;

public class TestAnswer extends Answer
{
	private String answerName;
	private String answer;
	
	public TestAnswer()
	{
		setCorrectAnswer(false);
		answerName="";
		answer="";
	}
	
	public TestAnswer(boolean isCorrect, String name, String answer)
	{
		setCorrectAnswer(isCorrect);
		this.answerName=name;
		this.answer=answer;
	}
	
	public void setAnswer(String answer)
	{
		this.answer=answer;
	}
	
	public void setName(String name)
	{
		this.answerName=name;
	}
	
	public String getAnswer()
	{
		return answer;
	}
	
	public String getAnswerName()
	{
		return answerName;
	}
}
