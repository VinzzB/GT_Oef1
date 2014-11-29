package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12          
 */
public class QuizCatalogus implements Iterable<Quiz>
{
	private ArrayList<Quiz> quizCatalogus;

	/**
	 * Sole constructor. 
	 */
	public QuizCatalogus()
	{
		quizCatalogus = new ArrayList<Quiz>();
	}
// getters en setters
	public ArrayList<Quiz> getQuizzen()
	{
		return quizCatalogus;
	}

/**
 * Verhoogt quizID met volgende nummer in catalogus
 * 
 * @return quizID
 */
	public int setQuizID()
	{
		int maxID = 0;
		for (Quiz quiz : quizCatalogus)
		{
			if(quiz.getQuizID() > maxID) maxID = quiz.getQuizID();
		}
	    return maxID + 1;
	}

/**
 * Methode om Quiz terug te vinden bij bekende quizID 
 * 
 * @param quizID
 * @return Quiz
 */
	public Quiz getQuiz(int quizID)
	{
		for(Quiz quiz : quizCatalogus)
		{
			if(quiz.getQuizID() == quizID)
				return quiz;
		}
		return null;
	}
	
/**
 * Voegt een quiz mij catalogus, maakt automatisch een nieuwe quizID voor een nieuwe quiz
 * 
 * @param quiz
 */
	public void voegQuizToe(Quiz quiz)
	{
		quiz.setQuizCatalogus(this);
		quiz.setQuizID(setQuizID());
		quizCatalogus.add(quiz);
	}
	
	public void voegQuizToe(int mapID, Quiz quiz)
	{
		quizCatalogus.add(quiz);		
	}
/**
 * Verwijdert een quiz va catalogus
 * 
 * @param quiz
 */
	public void verwijderQuiz(Quiz quiz)
	{
		List<QuizOpdracht> list = new CopyOnWriteArrayList<QuizOpdracht>();
	 
		for(QuizOpdracht quizOpdracht : quiz.getQuizOpdrachten())
		{
			list.add(quizOpdracht);
		}
		
		for(QuizOpdracht quizOpdracht : list)
		{
			quiz.getQuizOpdrachten().remove(quizOpdracht);
		}
		
		quizCatalogus.remove(quiz);
	}
	
	@Override
	public Iterator<Quiz> iterator()
	{
		Iterator<Quiz> quizzen = quizCatalogus.iterator(); 
		return quizzen;
	}
}
