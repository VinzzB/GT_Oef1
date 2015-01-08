package persistency;

import java.util.ArrayList;

import model.QuizCatalogus;
import model.QuizOpdracht;
import model.OpdrachtCatalogus;

/**
 * Singleton pattern implementeerd om een instantie van elke catalog te hebben
 * 
 * @author Natalia
 *
 */
public class Catalogi
{
	private static Catalogi instance;
	
	private OpdrachtCatalogus opdrachten; 
	private QuizCatalogus quizzen;
	private ArrayList<QuizOpdracht> quizOpdrachten;
	
	private Catalogi(){}
	
	public static Catalogi get()
	{
		if(instance == null)
			instance = new Catalogi();
		return instance;
	}
	public OpdrachtCatalogus getOpdrachten()
	{
		return opdrachten;
	}
	public void setOpdrachten(OpdrachtCatalogus opdrachten)
	{
		this.opdrachten = opdrachten;
	}
	public QuizCatalogus getQuizzen()
	{
		return quizzen;
	}
	public void setQuizzen(QuizCatalogus quizzen)
	{
		this.quizzen = quizzen;
	}
	public ArrayList<QuizOpdracht> getQuizOpdrachten()
	{
		return quizOpdrachten;
	}
	public void setQuizOpdrachten(ArrayList<QuizOpdracht> quizOpdrachten)
	{
		this.quizOpdrachten = quizOpdrachten;
	}
	
	
}
