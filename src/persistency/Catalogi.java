package persistency;

import model.QuizCatalogus;
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
	
	public static void LoadData() throws Exception
	{
		DatabaseHandler.instance().leesOpdrachten();
		DatabaseHandler.instance().leesQuizen();
	}
	
	public static void SaveData() throws Exception
	{
		DatabaseHandler.instance().safeOpdrachten();
		DatabaseHandler.instance().safeQuizen();
	}
	
}
