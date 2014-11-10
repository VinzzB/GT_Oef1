package model;

import java.util.ArrayList;

public class QuizCatalogus
{
	private ArrayList<Quiz> quizCatalogus;
	
	public QuizCatalogus()
	{
		quizCatalogus = new ArrayList<Quiz>();
	}
	
	public ArrayList <Quiz> getQuizzen()
	{
		return quizCatalogus;
	}
	
	public void voegQuizToe(Quiz quiz)
	{
		quiz.setQuizCatalogus(this);
		quiz.setQuizID(setQuizID());
		quizCatalogus.add(quiz);
	}
	
	protected void verwijderQuiz(Quiz quiz)
	{
		quizCatalogus.remove(quiz);
	}
	
	public int setQuizID()
	{
	    return quizCatalogus.size() + 1;
	}
	
	public Quiz getQuiz(int quizID)
	{
		for(Quiz quiz : quizCatalogus)
		{
			if(quiz.getQuizID() == quizID)
				return quiz;
		}
		return null;
	}
}
