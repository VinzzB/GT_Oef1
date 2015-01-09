package persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;
import utils.Constants;
import utils.LoadProperties;
/**
 * Abstracte klasse om objecten te lesen en weg te schrijven
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 */
public abstract class Database implements IDatabaseStrategy
{
	protected static Database instance;
	
	protected File opdrachtenDB;
	protected File quizzenDB;
	protected File quizOpdrachtDB;

	protected OpdrachtCatalogus opdrachten; 
	protected QuizCatalogus quizzen;
	protected ArrayList<QuizOpdracht> quizOpdrachten;
	
	protected static LoadProperties properties;
	
	public Database() throws IOException 
	{
		
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		if (quizzen == null)	quizzen = new QuizCatalogus();
		if (opdrachten == null) opdrachten = new OpdrachtCatalogus();
		
		properties = new LoadProperties(new File(Constants.SETTINGS_PATH + 
				Constants.SETTINGS_FILE));
	}

/**
 * Konverteert gelezen String naar Opdracht object
 * @throws Exception 
 * @throws NumberFormatException 
 */
	@Override
	public void leesOpdrachten() throws NumberFormatException, Exception
	{
		String [][] objecten = leesVanBestand(opdrachtenDB);
		for (String[] object : objecten)
		{
			opdrachten.voegOpdrachtToe(OpdrachtFactory.getOpdracht(OpdrachtTypen.valueOf(object[9]), object));
//			opdrachten.voegOpdrachtToe(new Opdracht(object));
		}
		Catalogi.get().setOpdrachten(opdrachten);
	}

	/**
	 * Konverteert gelezen String naar Quiz object
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@Override
	public void leesQuzen() throws NumberFormatException, Exception
	{
		String [][] objecten  = leesVanBestand(quizzenDB);
		for (String[] object : objecten)
		{
			String[] s = (String[]) object;
			quizzen.voegQuizToe(Integer.parseInt(s[0]), new Quiz(s));
		}
		Catalogi.get().setQuizzen(quizzen);
	}

	/**
	 * Leest quizOpdracht file en koppelt quizzen met opdrachten
	 * @throws SQLException 
	 */
	@Override
	public void kopelQuizOpdrachten() throws FileNotFoundException, IOException, SQLException
	{
		String[][] objecten = leesVanBestand(quizOpdrachtDB);
		for (String[] object : objecten)
		{
			String[] s = (String[]) object;
			QuizOpdracht.koppelOpdrachtAanQuiz(quizzen.getQuiz(Integer.parseInt(s[0])), 
												opdrachten.getOpdracht(Integer.parseInt(s[1])),
												Integer.parseInt(s[2]));
		}
	}

/**
 * Schrijft array van opdrahten naar bestand
 * @throws SQLException 
 * @throws IOException 
 * @throws FileNotFoundException 
 */
	@Override
	public void safeOpdrachten() throws SQLException, FileNotFoundException, IOException
	{
		int i = 0;
		String[] objecten = new String[opdrachten.getOpdrachten().size()];
		for (Opdracht opdracht : opdrachten.getOpdrachten().values())
		{
			objecten[i] = opdracht.toBestand();
			i ++;
		}
		schrijfNaarBestand(objecten, opdrachtenDB);
		
	}

	/**
	 * Schrijft array van quizzen naar bestand
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Override
	public void safeQuizen() throws SQLException, FileNotFoundException, IOException
	{
		int i = 0;
		String[] objecten = new String[quizzen.getQuizzen().size()];
		for (Quiz quiz : quizzen.getQuizzen())
		{
			objecten[i] = quiz.toBestand();
			i ++;
		}
		schrijfNaarBestand(objecten, quizzenDB);
	}

	/**
	 * Schrijft array van koppelings quiz-opdracht naar bestand
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Override
	public void safeQuizOpdrachten() throws SQLException, FileNotFoundException, IOException
	{
		ArrayList<String> objecten = new ArrayList<String>();
		for(Quiz quiz : quizzen.getQuizzen())
		{
			for(QuizOpdracht quizopdracht : quiz.getQuizOpdrachten())
			{
				objecten.add(quizopdracht.toBestand());
			}
		}
		schrijfNaarBestand(objecten.toArray(new String[objecten.size()]), quizOpdrachtDB);
	}
	

	
//	public QuizCatalogus getQuizCatalogus()
//	{
//		return quizzen;
//	}
//	
//	public OpdrachtCatalogus getOpdrachtCatalogus()
//	{
//		return opdrachten;
//	}
//	
//	public ArrayList<QuizOpdracht> getQuizOpdrachten()
//	{
//		return quizOpdrachten;
//	}

/**
 * Leest bestande catalogus
 */
	public void setCatalogus(OpdrachtCatalogus opdrahten, QuizCatalogus quizzen)
	{
		this.opdrachten = opdrahten;
		this.quizzen = quizzen;
	}
	
	/**
	 * Abstracte methode om file te lezen
	 * Moet geimplementeert worden in respectievelijke concrete klasse
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SQLException 
	 */
	abstract String[][] leesVanBestand(File file) throws FileNotFoundException, IOException, SQLException;
	
/**
 * Abstracte methode om file weg te schrijven
*  Moet geimplementeert worden in respectievelijke concrete klasse
 * @param objecten
 * @param file
 * @throws SQLException 
 * @throws IOException 
 * @throws FileNotFoundException 
 */
	abstract void schrijfNaarBestand(String[] objecten, File file) throws SQLException, FileNotFoundException, IOException;
	
}
