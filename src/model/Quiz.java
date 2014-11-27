package model;

import java.util.ArrayList;
import java.util.List;

import model.quizStatus.*;

/**
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version 1.0
 * @since 2014-11-12
 */
public class Quiz implements Comparable<Quiz>, Cloneable
{
	private int quizID;
	private String onderwerp;
	private int leerjaar;
	private boolean isTest;
	private boolean isUniek;
	private QuizStatus status;
	
	// Statussen:
	private Inconstructie statusInconstructie;
	private Afgewerkt statusAfgewerkt;
	private Opengesteld statusOpengesteld;
	private LaatsteKans statusLaatsteKans;
	private Afgesloten statusAfgesloten;

	private List<QuizOpdracht> quizOpdrachten;

	@SuppressWarnings("unused")
	private QuizCatalogus quizCatalogus;

	/**
	 * Sole constructor. (For invocation by constructors with parameters.)
	 */
	public Quiz()
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
		
		// create state instances
		statusInconstructie = new Inconstructie(this);
		statusAfgewerkt = new Afgewerkt(this);
		statusOpengesteld = new Opengesteld(this);
		statusLaatsteKans = new LaatsteKans(this);
		statusAfgesloten = new Afgesloten(this);
		status = statusInconstructie;
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
		setStatus(quiz.getStatus());

		for (QuizOpdracht quizOpdracht : quiz.getQuizOpdrachten())
		{
			this.quizOpdrachten.add(quizOpdracht);
		}
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
		if (leerjaar <= 0 || leerjaar > 6)
			throw new Exception ("Leerjaar moet tusse 1 en 6 zijn");
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
	
	public void setAfgesloten()
	{
		this.status = statusAfgesloten;
	}
	
	
	public QuizStatus getAfgesloten()
	{
		return this.statusAfgesloten;
	}
	
	public void setAfgewerkt()
	{
		this.status = statusAfgewerkt;
	}
	
	public QuizStatus getAfgewerkt()
	{
		return this.statusAfgewerkt;
	}
	 
	public void setInconstructie()
	{
		this.status = statusInconstructie;
	}
	
	public QuizStatus getInconstructie()
	{
		return this.statusInconstructie;
	}
	
	public void setLaatsteKans()
	{
		this.status = statusLaatsteKans;
	}
	
	public QuizStatus getLaatsteKans()
	{
		return this.statusLaatsteKans;
	}
	
	public void setOpengesteld()
	{
		this.status = statusOpengesteld;
	}

	public QuizStatus getOpengesteld()
	{
		return this.statusOpengesteld;
	}
	
	public void setQuizID(int quizID)
	{
		this.quizID = quizID;
	}

	public int getQuizID()
	{
		return quizID;
	}

	public void setQuizCatalogus(QuizCatalogus quizCatalogus)
	{
		this.quizCatalogus = quizCatalogus;
	}

	public List<QuizOpdracht> getQuizOpdrachten()
	{
		return this.quizOpdrachten;
	}

	public ArrayList<Opdracht> getOpdrachten()
	{
		ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();

		for (QuizOpdracht quizOpdracht : quizOpdrachten)
		{
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}

	public QuizOpdracht getOpdracht(int volgnr)
	{
		return quizOpdrachten.get(volgnr - 1);
	}
	

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniek ? 1231 : 1237);
		result = prime * result + leerjaar;
		result = prime * result + ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result + ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Quiz))
		{
			return false;
		}
		Quiz other = (Quiz) obj;
		if (isTest != other.isTest)
		{
			return false;
		}
		if (isUniek != other.isUniek)
		{
			return false;
		}
		if (leerjaar != other.leerjaar)
		{
			return false;
		}
		if (onderwerp == null)
		{
			if (other.onderwerp != null)
			{
				return false;
			}
		} else if (!onderwerp.equals(other.onderwerp))
		{
			return false;
		}
		if (quizOpdrachten == null)
		{
			if (other.quizOpdrachten != null)
			{
				return false;
			}
		} else if (!quizOpdrachten.equals(other.quizOpdrachten))
		{
			return false;
		}
		if (status == null)
		{
			if (other.status != null)
			{
				return false;
			}
		} else if (!status.equals(other.status))
		{
			return false;
		}
		return true;
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
		return "Quiz [onderwerp=" + onderwerp + ", leerjaar=" + leerjaar + ", isTest=" + isTest + ", isUniek=" + isUniek
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

	/**
	 * Methode om String samen te stellen om instantie naar TXT bestand weg te schrijven
	 *
	 * @return String met \t delimeters
	 */
	public String toBestand()
	{
		return quizID + "\t" + onderwerp + "\t" + leerjaar + "\t" + isTest + "\t" + isUniek + "\t" + status;
	}

}
