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
	private static OpdrachtCatalogus instanceOpdrachten;
	private static QuizCatalogus instanceQuizzen;
	//private OpdrachtCatalogus opdrachten; 
	//private QuizCatalogus quizzen;
//	private ArrayList<QuizOpdracht> quizOpdrachten;
	
	private Catalogi() 	{ }
	
	public static OpdrachtCatalogus getOpdrachten()
	{
		if(instanceOpdrachten == null)
			instanceOpdrachten = new OpdrachtCatalogus();
		return instanceOpdrachten;
	}
	
	public static QuizCatalogus getQuizzen()
	{
		if(instanceQuizzen == null)
			instanceQuizzen = new QuizCatalogus();
		return instanceQuizzen;
	}	
}
