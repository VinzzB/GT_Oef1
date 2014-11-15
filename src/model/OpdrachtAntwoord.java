package model;

/**
 * Klasse OpdrachtAntwoord: bestaat uit antwoordtijd, laatsteantwoord en aantal pogingen. Een opdrachtantwoord is
 * gelinkt aan 1 quizdeelname en aan 1 quizopdracht.
 *
 * @author Silvia
 */
public class OpdrachtAntwoord implements Comparable<OpdrachtAntwoord>, Cloneable
{
	private QuizDeelname ownerQuizDeelname;
	private QuizOpdracht ownerQuizOpdracht;
	private int antwoordTijd;
	private String laatsteAntwoord;
	private int aantalPogingen;

	/**
	 * QuizDeelname waaraan het opdrachtantwoord gelinkt is
	 *
	 * @return the value of ownerQuizDeelname
	 */
	public QuizDeelname getOwner()
	{
		return ownerQuizDeelname;
	}

	/**
	 * Linkt het opdrachtantwoord aan de QuizDeelname
	 *
	 * @param newOwner
	 *            Quizdeelname
	 */
	public void setOwnerQuizDeelname(QuizDeelname newOwner)
	{
		if (ownerQuizDeelname != newOwner)
		{
			QuizDeelname old = ownerQuizDeelname;
			ownerQuizDeelname = newOwner;
			if (newOwner != null)
			{
				newOwner.addOpdrachtAntwoord(this);
			}
			if (old != null)
			{
				old.removeOpdrachtAntwoord(this);
			}
		}
	}

	/**
	 * Linkt opdrachtantwoord aan de QuizOpdracht
	 *
	 * @param newOwner
	 *            QuizOpdracht
	 */
	public void setOwnerQuizOpdracht(QuizOpdracht newOwner)
	{
		if (ownerQuizOpdracht != newOwner)
		{
			QuizOpdracht old = ownerQuizOpdracht;
			ownerQuizOpdracht = newOwner;
			if (newOwner != null)
			{
				newOwner.addOpdrachtAntwoord(this);
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

	public OpdrachtAntwoord(QuizDeelname ownerQuizDeelname, QuizOpdracht ownerQuizOpdracht, int antwoordTijd,
			String laatsteAntwoord, int aantalPogingen)
	{
		this.ownerQuizDeelname = ownerQuizDeelname;
		this.ownerQuizOpdracht = ownerQuizOpdracht;
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
		return new OpdrachtAntwoord(this.ownerQuizDeelname.clone(), this.ownerQuizOpdracht.clone(), this.antwoordTijd,
				this.laatsteAntwoord, this.aantalPogingen);
	}

}
