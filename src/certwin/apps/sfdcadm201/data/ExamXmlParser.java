package certwin.apps.sfdcadm201.data;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import certwin.apps.sfdcadm201.ADMExamModel;
import certwin.apps.sfdcadm201.ADMExamPreferences;

import certwin.apps.sfdcadm201.R;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExamXmlParser
{
	List <TestQuestion> testQuestionsList;
	TestQuestion testQuestion;
	TestQuestion [] testQuestions;
	TestAnswer [] testAnswers;
	DocumentBuilder db;
	Document doc;
	InputStream inputStream;
	Debug debug;
	String lpiTest;
	
	public ExamXmlParser()
	{
		testQuestionsList=new ArrayList <TestQuestion>();
		testAnswers=new TestAnswer [TestQuestion.MAX_ANSWERS];
		lpiTest=ADMExamPreferences.LPI_101;
	}
	
	public void setLpiTest(String lpiTest)
	{
		this.lpiTest=lpiTest;
	}

	public void getData(Context context, String lpiTest)
	{
		setLpiTest(lpiTest);
		getData(context);
	}
	
	public void getData(Context context)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			debug.out("QuizXmlParser.getData() starting");
			db = dbf.newDocumentBuilder();
			
			if (ADMExamPreferences.LPI_102.equals(lpiTest))
			{
				inputStream = context.getResources().openRawResource(R.raw.lpi_102_quiz);
			}
			else
			{
				inputStream = context.getResources().openRawResource(R.raw.adm_201_exam);
			}
			
			doc = db.parse(inputStream);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("TestQuestion");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
            	try
            	{
	            	Node node = nodeList.item(i);
	        		testQuestion = new TestQuestion();
	        		testAnswers=new TestAnswer [TestQuestion.MAX_ANSWERS];
	        		for (int j=0; j<TestQuestion.MAX_ANSWERS; j++)
	        		{
	        			testAnswers[j]=new TestAnswer();
	        		}
	        		
	            	if (node.getAttributes().getNamedItem("isMultiple").getNodeValue().equals("0"))
	            	{
	            		testQuestion.setMultipleChoice(false);
	            	}
	            	else
	            	{
	            		testQuestion.setMultipleChoice(true);
	            	}
	            	//debug.out("TestQuestion "+i+" isMultipl="+node.getAttributes().getNamedItem("isMultiple").getNodeValue());
	            	Element fstElmnt = (Element) node;
	            	
	            	NodeList questionList = fstElmnt.getElementsByTagName("Question");
	            	Element questionElement = (Element) questionList.item(0);
	            	questionList = questionElement.getChildNodes();
	            	testQuestion.setQuestion(((Node) questionList.item(0)).getNodeValue());
	            	
	            	//String test = testQuestion.getText().toString();
	            	//Log.d("All_Question_Number:",test);
	            	
	            	//debug.out("TestQuestion "+i+" Question=" + ((Node) questionList.item(0)).getNodeValue());
	            	
	            	NodeList answerList = fstElmnt.getElementsByTagName("Answer");
	            	Element answerElement;
	            	NodeList answerListChild;
	            	
	            	for (int j=0;j<4;j++)
	            	{
	            		answerElement = (Element) answerList.item(j);
	            		answerListChild = answerElement.getChildNodes();
	            		if (answerElement.getAttribute("isTrue").equals("0"))
	            		{
	            			testAnswers[j].setCorrectAnswer(false);
	            		}
	            		else
	            		{
	            			testAnswers[j].setCorrectAnswer(true);
	            		}
	            		testAnswers[j].setName(answerElement.getAttribute("name"));
	            		testAnswers[j].setAnswer(((Node) answerListChild.item(0)).getNodeValue());
	                	//debug.out("TestQuestion "+i+" name=" + answerElement.getAttribute("name"));
	                	//debug.out("TestQuestion "+i+" isTrue=" + answerElement.getAttribute("isTrue"));
	            		//debug.out("TestQuestion "+i+" Answer=" + ((Node) answerListChild.item(0)).getNodeValue());
	            	}
	            	testQuestion.setAnswers(testAnswers);
	            	
	            	NodeList explanationList = fstElmnt.getElementsByTagName("Explanation");
	            	Element explanationElement = (Element) explanationList.item(0);
	            	explanationList = explanationElement.getChildNodes();
	            	testQuestion.setExplanation(((Node) explanationList.item(0)).getNodeValue());
	            	
	            	testQuestionsList.add(testQuestion);
	            	
	            	//debug.out("TestQuestion "+i+" Explanation=" + ((Node) explanationList.item(0)).getNodeValue());
	            	/*
	            	debug.out(i+" "+testQuestion.getQuestion());
	            	for (int j=0;j<4;j++)
	            	{
	            		String isCorrect;
		            	if (testQuestion.isCorrectAnswer(j)==true)
		            	{
		            		isCorrect="+";
		            	}
		            	else
		            	{
		            		isCorrect="-";
		            	}
		            	debug.out(testQuestion.getAnswerName(j)+" "+isCorrect+" ) "+testQuestion.getAnswer(j));
	            	}
	            	debug.out(testQuestion.getExplanation());
	            	*/
	            	//debug.out("");
            	}
            	catch (NullPointerException nullException)
            	{
            		debug.out("TestQuestion "+i+"NullPointerException");
            		debug.out("");
            	}
            }
			
			debug.out("QuizXmlParser.getData() It's working");
		}
		catch (ParserConfigurationException perserE)
		{
			debug.out("QuizXmlParser.getData() ParserConfigurationException");
		}
		catch (SAXException sex)
		{
			debug.out("QuizXmlParser.getData() SAXException");
		}
		catch (IOException io)
		{
			debug.out("QuizXmlParser.getData() IOException");
		}
	}
	
	public TestQuestion [] getRandomTestQuestions()
	{
		List <Integer> indexList;
		indexList=new ArrayList <Integer>();
		Random random = new Random();
		int randomIndex;
		
		testQuestions=new TestQuestion [ADMExamModel.MAX_TEST_QUESTIONS];
		for (int j=0; j<ADMExamModel.MAX_TEST_QUESTIONS; j++)
		{
			testQuestions[j]=new TestQuestion();
			
		
			
			
		}
		
		for (int i=0; i<testQuestionsList.size(); i++)
		{
			indexList.add(new Integer(i));
			
			//Log.d("Number: ", indexList.toString());
		}
		
		for (int i=0; i<ADMExamModel.MAX_TEST_QUESTIONS; i++)
		{
			randomIndex=random.nextInt()%indexList.size();
			if (randomIndex<0)
			{
				randomIndex*=-1;
			}
			testQuestions[i]=testQuestionsList.get(indexList.get(randomIndex).intValue());
			indexList.remove(randomIndex);
			
			//Log.d("Number1: ", indexList.toString());
		}
		
		return testQuestions;
	
    	

	}
	
	public void printData()
	{
		if (testQuestionsList != null)
		{
			for (int i=0; i<testQuestionsList.size(); i++)
			{
				debug.out(i+". "+testQuestionsList.get(i).getQuestion());
            	for (int j=0;j<4;j++)
            	{
            		String isCorrect;
	            	if (testQuestionsList.get(i).isCorrectAnswer(j)==true)
	            	{
	            		isCorrect="+";
	            	}
	            	else
	            	{
	            		isCorrect="-";
	            	}
	            	debug.out(testQuestionsList.get(i).getAnswerName(j)+" "+isCorrect+" ) "+testQuestionsList.get(i).getAnswer(j));
            	}
            	debug.out(testQuestionsList.get(i).getExplanation());
            	debug.out("");
			}
		}
	}
}
