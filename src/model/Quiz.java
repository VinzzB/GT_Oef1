package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import persistency.framework.DbQuiz;
import persistency.framework.DbQuizOpdracht;
import model.Opdracht;
import model.catalogi.Catalogi;
import model.quizStatus.*;
import utils.date.gregorian.*;

/**
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version 1.0
 * @since 2014-11-12
 */
public class Quiz implements Comparable<Quiz>, Cloneable
{
	private int quizID;  //Comment Vinzz: Is hier niet nodig, zou in catalogus kunnen blijven staan...
	private String onderwerp;
	private int leerjaar;
	private boolean isTest;
	private boolean isUniek;
	private QuizStatus status;
	private Leraar auteur;
	private Datum datumVanCreatie;

	private List<QuizOpdracht> quizOpdrachten;

	/**
	 * Sole constructor. (For invocation by constructors with parameters.)
	 */
	public Quiz()
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		
		// create state instances
		status = Statussen.InConstructie.Instance(); //  Inconstructie.instance();
		setDatumVanCreatie(new Datum());
	}

	/**
	 * Constructs a new instance of Quiz using String as an onderwerp. All other fields default to null.
	 *
	 * @param onderwerp
	 */
	public Quiz(String onderwerp)
	{
		this();
		setOnderwerp(onderwerp);
	}
	
	public Quiz(int quizID, String onderwerp, int leerjaar, boolean isTest, boolean isUniek) throws Exception
	{
		this();
		setOnderwerp(onderwerp);
		setLeerjaar(leerjaar);
		setIsTest(isTest);
		setIsUniek(isUniek);
		this.quizID = quizID;
	}

	/**
	 * Constructs a new instance of Quiz using String as an onderwerp, Int as leerjaar, Boolean as isTest, Boolean as
	 * isUniek, String as status. All other fields default to null.
	 *
	 * @param onderwerp
	 * @param leerjaar
	 * @param isTest
	 * @param isUniek
	 * @param status
	 * @throws Exception 
	 */
	public Quiz(String onderwerp, int leerjaar, boolean isTest, boolean isUniek, QuizStatus status) throws Exception
	{
		this();
		setOnderwerp(onderwerp);
		setLeerjaar(leerjaar);
		setIsTest(isTest);
		setIsUniek(isUniek);
		setStatus(status);
	}

	/**
	 * Constructs a new instance of Quiz using Int as QuizID, String as an onderwerp, Int as leerjaar, Boolean as
	 * isTest, Boolean as isUniek, String as status. All other fields default to null.
	 *
	 * @param quizID
	 * @param onderwerp
	 * @param leerjaar
	 * @param isTest
	 * @param isUniek
	 * @param status
	 * @throws Exception 
	 */
	public Quiz(int quizID, String onderwerp, int leerjaar, boolean isTest, boolean isUniek, QuizStatus status) throws Exception
	{
		this(onderwerp, leerjaar, isTest, isUniek, status);
		this.quizID = quizID;
	}
	
	public Quiz(int quizID, String onderwerp, int leerjaar, boolean isTest, boolean isUniek, QuizStatus status, Leraar leraar) throws Exception
	{
		this(quizID, onderwerp, leerjaar, isTest, isUniek, status);
		setAuteur(leraar);
	}
	
	public Quiz(int quizID, String onderwerp, int leerjaar, boolean isTest, boolean isUniek, QuizStatus status, Leraar leraar, Datum datum) throws Exception
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		this.quizID = quizID;
		setOnderwerp(onderwerp);
		setLeerjaar(leerjaar);
		setIsTest(isTest);
		setIsUniek(isUniek);
		setAuteur(leraar);
		setStatus(status);
		setDatumVanCreatie(datum);
	}

	/**
	 * Costructor om istantie Quiz te crieen van TXTdatabase
	 * 
	 * String array fields:
	 * 0: quizID
	 * 1: Onderwerp
	 * 2: Is test
	 * 3: Is uniek
	 * 4: Status
	 * 5: Auteur
	 * 6: Creatie datum
	 * 
	 *  @return String met \t delimeters
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public Quiz(DbQuiz vanTXTbestand) //throws NumberFormatException, Exception
	{
		this();
		quizID = vanTXTbestand.getId();
		onderwerp = vanTXTbestand.getOnderwerp();
		isTest = vanTXTbestand.isTest();
		isUniek = vanTXTbestand.isUniekeDeelname();
		auteur = vanTXTbestand.getAuteur();
		datumVanCreatie = vanTXTbestand.getDatumRegistratie();
		
		for (DbQuizOpdracht dbo : vanTXTbestand.getQuizOpdrachten())
		{
			Opdracht o = Catalogi.getOpdrachten().getOpdracht(dbo.getOpdrachtIndex());
			QuizOpdracht.koppelOpdrachtAanQuiz(this, o, dbo.getMaxScore());
		}		
		status = vanTXTbestand.getStatus().Instance();				
	}
	
	/**
	 * Copy constructor. Constructs a new instance of Quiz using other Quiz as parameter.
	 *
	 * @param quiz
	 * @throws Exception 
	 */
	public Quiz(Quiz quiz) throws Exception
	{
		this();
		setQuizID(quiz.getQuizID());
		setOnderwerp(quiz.getOnderwerp());
		setLeerjaar(quiz.getLeerjaar());
		setIsTest(quiz.isTest());
		setIsUniek(quiz.isUniek());

		for (QuizOpdracht quizOpdracht : quiz.getQuizOpdrachten())
		{
			QuizOpdracht.koppelOpdrachtAanQuiz(this, quizOpdracht.getOpdracht(), quizOpdracht.getMaxScore());
			//this.quizOpdrachten.add(quizOpdracht);
		}
		setStatus(quiz.getStatus());
	}

	// getters en setters

	public String getOnderwerp()
	{
		return onderwerp;
	}

	public void setOnderwerp(String onderwerp)
	{
		this.onderwerp = onderwerp;
	}

	public int getLeerjaar()
	{
		return leerjaar;
	}

	public void setLeerjaar(int leerjaar) throws Exception
	{
		this.leerjaar = leerjaar;
	}
	

	public boolean isTest()
	{
		return isTest;
	}

	public void setIsTest(boolean isTest)
	{
		this.isTest = isTest;
	}

	public boolean isUniek()
	{
		return isUniek;
	}

	public void setIsUniek(boolean isUniek)
	{
		this.isUniek = isUniek;
	}

	public QuizStatus getStatus()
	{
		return status;
	}

	public void setStatus(QuizStatus status)
	{
		this.status = status;
	}
	
	public void setQuizID(int quizID)
	{
		this.quizID = quizID;
	}

	public int getQuizID()
	{
		return quizID;
	}

//	public void setQuizCatalogus(QuizCatalogus quizCatalogus)
//	{
//		this.quizCatalogus = quizCatalogus;
//	}

	public List<QuizOpdracht> getQuizOpdrachten()
	{
		return this.quizOpdrachten;
	}

	public TreeMap<Integer, Opdracht> getOpdrachten()
	{
		TreeMap<Integer, Opdracht> opdrachten = new TreeMap<Integer, Opdracht>();

		for (QuizOpdracht quizOpdracht : quizOpdrachten)
		{
			opdrachten.put(opdrachten.size(), quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}
	
	public int getOpdrachtIndex(Opdracht opdracht)
	{
		for( Map.Entry<Integer, Opdracht> entry : getOpdrachten().entrySet())
		{
			if (entry.getValue() == opdracht)
				return entry.getKey();
		}
		return -1;
	}

	public QuizOpdracht getQuizOpdracht(int volgnr)
	{
		if(quizOpdrachten.size() >= volgnr)
			return quizOpdrachten.get(volgnr - 1);
		else
			return null;
	}

	public Leraar getAuteur()
	{
		return auteur;
	}

	public void setAuteur(Leraar auteur)
	{
		this.auteur = auteur;
	}

	public Datum getDatumVanCreatie()
	{
		return datumVanCreatie;
	}

	public void setDatumVanCreatie(Datum datumVanCreatie)
	{
		this.datumVanCreatie = datumVanCreatie;
	}	



	@Override
	public int compareTo(Quiz quiz)
	{
		return this.quizID - quiz.quizID;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Quiz "+ this.quizID + " [onderwerp: " + onderwerp + ", leerjaar: " + leerjaar + 
				", isTest: " + isTest + ", isUniek: " + isUniek	+ ", status: " + 
				status + "] auteur: " + auteur + " datum: " + datumVanCreatie + ", vragen: " + quizOpdrachten.size();
	}
	
	public String fullDescription()
	{
		return "Quiz "+ this.quizID + " [onderwerp=" + onderwerp + ", leerjaar=" + leerjaar + ", isTest=" + isTest + ", isUniek=" + isUniek
				+ ", status=" + status + ", quizOpdrachten=" + quizOpdrachten + "]";
	}

	@Override
	public Quiz clone() throws CloneNotSupportedException
	{
		try
		{
			return new Quiz(this);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Voeg quizOpdracht instantie to List van quizOpdrachten als Quiz instantie werd gekoppeld met Opdracht
	 *
	 * @param quizOpdracht
	 */
	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht)
	{
		quizOpdrachten.add(quizOpdracht);
	}

	/**
	 * Verwijdert quizOpdracht instrantie van List van quizOpdrachten bij verwijderen van koppeling van instantie van
	 * Quiz met Opdracht
	 *
	 * @param quizOpdracht
	 */
	protected void verwijderQuizOpdracht(QuizOpdracht quizOpdracht)
	{
		quizOpdrachten.remove(quizOpdracht);
	}
	
//	public static QuizStatus vanStringNaarQuizStatus(String quizStatusInString)
//	{
//		switch(quizStatusInString)
//		{
//		case "Afgesloten":
//			return Afgesloten.instance();
//		case "Afgewerkt":
//			return Afgewerkt.instance();
//		case "In constructie":
//			return Inconstructie.instance();
//		case "Laatste Kans":
//			return LaatsteKans.instance();
//		case "Opengesteld":
//			return Opengesteld.instance();
//		default:
//			return null;
//		}	
//	}

	/**
	 * Methode om String samen te stellen om instantie naar TXT bestand weg te schrijven
	 *	
	 * String array fields:
	 * 0: quizID
	 * 1: Onderwerp
	 * 2: is test
	 * 3: is uniek
	 * 4: status
	 * 5: Auteur
	 * 6: Creatie datum
	 * 
	 *  @return String met \t delimeters
	 */
	public String toBestand()
	{
		return this.quizID + "\t" + this.onderwerp + "\t" + this.leerjaar + "\t" + 
				this.isTest + "\t" + this.isUniek + "\t" + this.status.getEnumType() + "\t" + 
				this.auteur.name() + "\t" + datumVanCreatie.getEuropeanFormat();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result
				+ ((datumVanCreatie == null) ? 0 : datumVanCreatie.hashCode());
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniek ? 1231 : 1237);
		result = prime * result + leerjaar;
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result + quizID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Quiz))
			return false;
		Quiz other = (Quiz) obj;
		if (auteur != other.auteur)
			return false;
		if (datumVanCreatie == null)
		{
			if (other.datumVanCreatie != null)
				return false;
		}
		else if (!datumVanCreatie.equals(other.datumVanCreatie))
			return false;
		if (isTest != other.isTest)
			return false;
		if (isUniek != other.isUniek)
			return false;
		if (leerjaar != other.leerjaar)
			return false;
		if (onderwerp == null)
		{
			if (other.onderwerp != null)
				return false;
		}
		else if (!onderwerp.equals(other.onderwerp))
			return false;
		if (quizID != other.quizID)
			return false;
		return true;
	}

}
