package model;

import java.util.HashSet;
import java.util.Set;

import model.scoreStrategy.IScoreStrategy;
import model.scoreStrategy.QuizScoreRegelsFactory;
import model.OpdrachtAntwoord;
import utils.date.normal.*;

/**
 * Klasse Quizdeelname: Een quizdeelname is gelinkt aan 1 leerling en bevat 1 of 
 * meerdere opdrachtantwoorden en is ook
 * gelinkt aan 1 quiz en bevat de datum van deelname.
 *
 * @author Silvia
 * @author Natalia Dyubankova (score strategy)
 */
public class QuizDeelname implements Cloneable, Comparable<QuizDeelname>
{
	private Leerling ownerLeerling;
	private Set<OpdrachtAntwoord> opdrachtAntwoorden;
	private Datum datumDeelname;
	private Quiz ownerQuiz;
	private IScoreStrategy scoreStrategy;

	/**
	 * Linkt de leerling aan de quizdeelname
	 *
	 * @param newOwner
	 *            Leerling die bij deze quizdeelname hoort
	 */
	public void setOwnerLeerling(Leerling newOwner)
	{
		if (ownerLeerling != newOwner)
		{
			Leerling old = ownerLeerling;
			ownerLeerling = newOwner;
			if (newOwner != null)
			{
				newOwner.addQuizDeelname(this);
			}
			if (old != null)
			{
				old.removeQuizDeelname(this);
			}
		}
	}

	/**
	 *
	 * @return Set met opdrachtantwoorden
	 */
	public Set<OpdrachtAntwoord> getOpdrachtAntwoorden()
	{
		return opdrachtAntwoorden;
	}

	/**
	 *
	 * @return Datum deelnamedatum
	 */
	public Datum getDatumDeelname()
	{
		return datumDeelname;
	}

	/**
	 *
	 * @param datumDeelname
	 *            Datum
	 */
	public void setDatumDeelname(Datum datumDeelname)
	{
		this.datumDeelname = datumDeelname;
	}

	/**
	 *
	 * @return Quiz
	 */
	public Quiz getQuiz()
	{
		return ownerQuiz;
	}

	public IScoreStrategy getScoreStrategy()
	{
		return scoreStrategy;
	}

	public void setScoreStrategy(IScoreStrategy scoreStrategy)
	{
		this.scoreStrategy = scoreStrategy;
	}

	/**
	 * Default Constructor
	 */
	public QuizDeelname()
	{
		this.opdrachtAntwoorden = new HashSet<OpdrachtAntwoord>();
		this.datumDeelname = new Datum();
		this.ownerQuiz = new Quiz();
	}

	/**
	 * Constructor met 4 parameters
	 *
	 * @param newOwnerLeerling
	 *            Leerling waaraan de quizdeelname zal gelinkt worden
	 * @param opdrachtAntwoord
	 *            Set met 1 of meer opdrachtantwoorden
	 * @param datumDeelname
	 *            Datum van deelname
	 * @param newOwnerQuiz
	 *            Quiz waaraan de quizdeelname zal gelinkt worden
	 */
	public QuizDeelname(Leerling newOwnerLeerling, Set<OpdrachtAntwoord> opdrachtAntwoord, Datum datumDeelname, Quiz newOwnerQuiz)
	{
		this.ownerLeerling = newOwnerLeerling;
		this.opdrachtAntwoorden = opdrachtAntwoord;
		this.datumDeelname = datumDeelname;
		this.ownerQuiz = newOwnerQuiz;
	}

	/**
	 * Voegt een opdrachtantwoord toe aan de Set met opdrachtantwoorden en linkt deze met de quizdeelname
	 *
	 * @param opdrachtAntwoord
	 */
	public void addOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
	{
		this.opdrachtAntwoorden.add(opdrachtAntwoord);
		opdrachtAntwoord.setQuizDeelname(this);
	}

	/**
	 * Verwijdert een opdrachtantwoord van de Set met opdrachtantwoorden en verwijdert de link met de quizdeelname
	 *
	 * @param opdrAntw
	 */
	public void removeOpdrachtAntwoord(OpdrachtAntwoord opdrAntw)
	{
		this.opdrachtAntwoorden.remove(opdrAntw);
		opdrAntw.setQuizDeelname(null);
	}

	@Override
	public int compareTo(QuizDeelname o)
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
	public QuizDeelname clone() throws CloneNotSupportedException
	{
		Leerling l = ownerLeerling.clone();
		Set<OpdrachtAntwoord> o = new HashSet<OpdrachtAntwoord>();
		for (OpdrachtAntwoord item : opdrachtAntwoorden)
		{
			o.add(item);
		}
		Datum d = new Datum(this.datumDeelname);
		Quiz q = new Quiz();
		try
		{
			q = new Quiz(this.ownerQuiz);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new QuizDeelname(l, o, d, q);
	}
	
	public int scoreBerekenen()
	{
		int score = 0;
		for (OpdrachtAntwoord opdrachtAntwoord : opdrachtAntwoorden)
		{
			scoreStrategy = QuizScoreRegelsFactory.getScoreStrategy(opdrachtAntwoord);
			score += scoreStrategy.scoreBerekenen(opdrachtAntwoord);
		}
		return score;
	}

}
