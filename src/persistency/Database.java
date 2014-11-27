package persistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.*;
import model.quizStatus.*;

/**
 * Abstracte klasse om objecten te lesen en weg te schrijven
 * 
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-11 
 */
public abstract class Database implements IDatabaseStrategy
{
	protected File opdrachtenDB;
	protected File quizzenDB;
	protected File quizOpdrachtDB;
	protected static OpdrachtCatalogus opdrachten; 
	protected static QuizCatalogus quizzen;
	protected ArrayList<QuizOpdracht> quizOpdrachten;
	
	public Database() 
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		if (quizzen == null)	quizzen = new QuizCatalogus();
		if (opdrachten == null) opdrachten = new OpdrachtCatalogus();
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
			opdrachten.voegOpdrachtToe(new Opdracht(Integer.parseInt(object[0]), 
					object[1], object[2], this.vanStringNaarOpdrachtCategorie(object[3]), object[4],
					Integer.parseInt(object[5]), Integer.parseInt(object[6])));
		}
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
			quizzen.voegQuizToe(new Quiz(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), 
					Boolean.parseBoolean(s[3]), Boolean.parseBoolean(s[4]),	this.vanStringNaarQuizStatus(s[5])));
		}
	}

	/**
	 * Leest quizOpdracht file en koppelt quizzen met opdrachten
	 */
	@Override
	public void kopelQuizOpdrachten() throws FileNotFoundException, IOException
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
 */
	@Override
	public void safeOpdrachten()
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
	 */
	@Override
	public void safeQuizen()
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
	 */
	@Override
	public void safeQuizOpdrachten()
	{
		ArrayList<String> objecten = new ArrayList<String>();
		for(Quiz quiz : quizzen.getQuizzen())
		{
			for(Opdracht opdracht : quiz.getOpdrachten())
			{
				objecten.add(opdracht.getOpdracht(1).toBestand());
			}
		}
		schrijfNaarBestand(objecten.toArray(new String[objecten.size()]), quizOpdrachtDB);
	}
	
	private QuizStatus vanStringNaarQuizStatus(String quizStatusInString)
	{
		switch(quizStatusInString)
		{
		case "Afgesloten":
			return new Afgesloten();
		case "Afgewerkt":
			return new Afgewerkt();
		case "In constructie":
			return new Inconstructie();
		case "Laatste Kans":
			return new LaatsteKans();
		case "Opengesteld":
			return new Opengesteld();
		default:
			return null;
		}	
	}
	
	private OpdrachtCategorie vanStringNaarOpdrachtCategorie(String opdrachtCategorieInString)
	{
		switch(opdrachtCategorieInString)
		{
		case "Aardrijkskunde":
			return OpdrachtCategorie.Aardrijkskunde;
		case "Wiskunde":
			return OpdrachtCategorie.Wiskunde;
		case "Nederlands":
			return OpdrachtCategorie.Nederlands;
		case "Wetenschappen":
			return OpdrachtCategorie.Wetenschappen;
		default:
			return null;
		}
	}
	
	public static QuizCatalogus getQuizCatalogus()
	{
		return quizzen;
	}
	
	public static OpdrachtCatalogus getOpdrachtCatalogus()
	{
		return opdrachten;
	}

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
	 */
	abstract String[][] leesVanBestand(File file) throws FileNotFoundException, IOException;
	
/**
 * Abstracte methode om file weg te schrijven
*  Moet geimplementeert worden in respectievelijke concrete klasse
 * @param objecten
 * @param file
 */
	abstract void schrijfNaarBestand(String[] objecten, File file);
	
}
