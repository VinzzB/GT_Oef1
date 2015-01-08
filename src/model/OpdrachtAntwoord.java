package model;

import src.model.opdracht.Opdracht;
import model.QuizDeelname;
import model.QuizOpdracht;

/**
 * Klasse OpdrachtAntwoord: bestaat uit antwoordtijd, 
 * laatsteantwoord en aantal pogingen. Een opdrachtantwoord is
 * gelinkt aan 1 quizdeelname en aan 1 quizopdracht.
 *
 * @author Silvia
 * @author Natalia Dyubankova
 */
public class OpdrachtAntwoord implements Comparable<OpdrachtAntwoord>, Cloneable
{
	private QuizDeelname quizDeelname;
	private QuizOpdracht quizOpdracht;
	private int antwoordTijd;
	private String laatsteAntwoord;
	private int aantalPogingen;

	/**
	 * QuizDeelname waaraan het opdrachtantwoord gelinkt is
	 *
	 * @return the value of ownerQuizDeelname
	 */
	public QuizDeelname getQuizDeelname()
	{
		return quizDeelname;
	}

	/**
	 * Linkt het opdrachtantwoord aan de QuizDeelname
	 *
	 * @param newOwner
	 *            Quizdeelname
	 */
	public void setQuizDeelname(QuizDeelname quizDeelname)
	{
		if (this.quizDeelname != quizDeelname)
		{
			QuizDeelname old = this.quizDeelname;
			this.quizDeelname = quizDeelname;
			if (quizDeelname != null)
			{
				quizDeelname.addOpdrachtAntwoord(this);
			}
			if (old != null)
			{
				old.removeOpdrachtAntwoord(this);
			}
		}
	}


	/**
	 * Geeft de antwoordTijd in seconden
	 *
	 * @return int
	 */
	public int getAntwoordTijd()
	{
		return antwoordTijd;
	}

	/**
	 *
	 *
	 * @param antwoordTijd
	 *            int
	 */
	public void setAntwoordTijd(int antwoordTijd)
	{
		this.antwoordTijd = antwoordTijd;
	}

	/**
	 * Get the value of aantalPogingen
	 *
	 * @return the value of aantalPogingen
	 */
	public int getAantalPogingen()
	{
		return aantalPogingen;
	}

	/**
	 * Set the value of aantalPogingen
	 *
	 * @param aantalPogingen
	 *            new value of aantalPogingen
	 */
	public void setAantalPogingen(int aantalPogingen)
	{
		this.aantalPogingen = aantalPogingen;
	}

	/**
	 * Get the value of laatsteAntwoord
	 *
	 * @return the value of laatsteAntwoord
	 */
	public String getLaatsteAntwoord()
	{
		return laatsteAntwoord;
	}

	/**
	 * Set the value of laatsteAntwoord
	 *
	 * @param laatsteAntwoord
	 *            new value of laatsteAntwoord
	 */
	public void setLaatsteAntwoord(String laatsteAntwoord)
	{
		this.laatsteAntwoord = laatsteAntwoord;
	}
	
	/**
	 * @return the ownerQuizOpdracht
	 */
	public QuizOpdracht getQuizOpdracht()
	{
		return quizOpdracht;
	}

	/**
	 * @param ownerQuizOpdracht the ownerQuizOpdracht to set
	 */
	public void setQuizOpdracht(QuizOpdracht quizOpdracht)
	{
		this.quizOpdracht = quizOpdracht;
	}
	public Opdracht getOpdacht()
	{
		return this.quizOpdracht.getOpdracht();
	}

	public OpdrachtAntwoord(QuizDeelname quizDeelname, QuizOpdracht quizOpdracht, int antwoordTijd,
			String laatsteAntwoord, int aantalPogingen)
	{
		this.quizDeelname = quizDeelname;
		this.quizOpdracht = quizOpdracht;
		this.antwoordTijd = antwoordTijd;
		this.laatsteAntwoord = laatsteAntwoord;
		this.aantalPogingen = aantalPogingen;
	}

	@Override
	public int compareTo(OpdrachtAntwoord o)
	{
		if (o == null)
		{
			throw new NullPointerException("Object is null");
		}

		if (this.equals(o) == true)
		{
			return 0;
		}
		if (this.hashCode() > o.hashCode())
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	@Override
	public OpdrachtAntwoord clone() throws CloneNotSupportedException
	{
		return new OpdrachtAntwoord(this.quizDeelname.clone(), this.quizOpdracht.clone(), this.antwoordTijd,
				this.laatsteAntwoord, this.aantalPogingen);
	}

}
