package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import model.Quiz;

/**
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12          
 */
public class QuizCatalogus implements Iterable<Entry<Integer, Quiz>>
{
	private HashMap<Integer, Quiz> quizCatalogus;

	/**
	 * Sole constructor. 
	 */
	public QuizCatalogus()
	{
		quizCatalogus = new HashMap<Integer, Quiz>();
	}
// getters en setters
	public List<Quiz> getQuizzen()
	{
		return (List<Quiz>) quizCatalogus.values();
	}

/**
 * Verhoogt quizID met volgende nummer in catalogus
 * 
 * @return quizID
 */
	public int getLastQuizID()
	{
		int maxID = 0;
		for (Entry<Integer, Quiz> quiz : quizCatalogus.entrySet())
		{
			if(quiz.getKey() > maxID) maxID = quiz.getKey();
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
		return quizCatalogus.get(quizID);		
	}
	
/**
 * Voegt een quiz mij catalogus, maakt automatisch een nieuwe quizID voor een nieuwe quiz
 * 
 * @param quiz
 */
	public void voegQuizToe(Quiz quiz)
	{
		quiz.setQuizCatalogus(this);
		int newId = getLastQuizID();
		quiz.setQuizID(newId);
		quizCatalogus.put(newId, quiz);
	}
	
	public void voegQuizToe(int mapID, Quiz quiz)
	{
		quizCatalogus.put(mapID, quiz);		
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
	public Iterator<Entry<Integer, Quiz>> iterator()
	{ 
		return quizCatalogus.entrySet().iterator();
	}
	
	public ArrayList<Quiz> getQuizzenPerLeerjaar(int leerjaar)
	{
		ArrayList<Quiz> perLeerjaar = new ArrayList<Quiz>();
		for(Entry<Integer, Quiz> quiz : quizCatalogus.entrySet())
		{
			if (quiz.getValue().isValidLeerjaar(leerjaar))
				perLeerjaar.add(quiz.getValue());
		}
		return perLeerjaar;
	}
}
