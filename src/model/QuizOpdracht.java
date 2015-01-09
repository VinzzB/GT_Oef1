package model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nathalie
 * @author Natalia Dyubankova <fornnd@gmail.com>
 * @version 1.0
 * @since 2014-11-12
 */
public class QuizOpdracht implements Cloneable
{
	private Quiz quiz;
	private Opdracht opdracht;
	private int maxScore;
	private Set<OpdrachtAntwoord> opdrachtantwoorden;

	/**
	 * Constructs a new instance of QuizOpdracht using instantie van quiz en opdracht en bijhorende maxScore.
	 *
	 * PRIVATE!
	 *
	 * @param quiz
	 * @param opdracht
	 * @param maxScore
	 */
	private QuizOpdracht(Quiz quiz, Opdracht opdracht, int maxScore)
	{
		this.quiz = quiz;
		this.opdracht = opdracht;
		this.maxScore = maxScore;
		this.opdrachtantwoorden = new HashSet<OpdrachtAntwoord>();
	}

	/**
	 * Copy contsructor. Constructs a new instance of QuizOpdracht using other QuizOpdracht as parameter.
	 *
	 * @param quizOpdracht
	 */
	private QuizOpdracht(QuizOpdracht quizOpdracht)
	{
		this.quiz = quizOpdracht.quiz;
		this.opdracht = quizOpdracht.opdracht;
		this.maxScore = quizOpdracht.maxScore;
		this.opdrachtantwoorden = quizOpdracht.opdrachtantwoorden;
	}

	// getters en setters
	public Quiz getQuiz()
	{
		return quiz;
	}

	public void setQuiz(Quiz quiz)
	{
		if (this.quiz != quiz) 
		{
			Quiz oldQuiz = this.quiz;
			this.quiz = quiz;
			if (quiz != null) 
			{
				quiz.getStatus().voegQuizOpdrachtToe(this);				
			}
			if (oldQuiz != null)
			{
				oldQuiz.getStatus().verwijderQuizOpdracht(this);
			}
		}
	}
	
	public Opdracht getOpdracht()
	{
		return opdracht;
	}
	
	public void setOpdracht (Opdracht opdracht)
	{
		//eerst checken of nog geen test
		if (this.opdracht != opdracht) 
		{
			Opdracht oldOpdracht = this.opdracht;
			this.opdracht = opdracht;
			if (opdracht != null)
			{
				opdracht.addQuizOpdracht(this);				
			}
			if (oldOpdracht != null)
			{
				oldOpdracht.removeQuizOpdracht(this);				
			}
		}
	}

	public int getMaxScore()
	{
		return maxScore;
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
		result = prime * result + maxScore;
		result = prime * result + ((opdracht == null) ? 0 : opdracht.hashCode());
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
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
		if (!(obj instanceof QuizOpdracht))
		{
			return false;
		}
		QuizOpdracht other = (QuizOpdracht) obj;
		if (maxScore != other.maxScore)
		{
			return false;
		}
		if (opdracht == null)
		{
			if (other.opdracht != null)
			{
				return false;
			}
		} else if (!opdracht.equals(other.opdracht))
		{
			return false;
		}
		if (quiz == null)
		{
			if (other.quiz != null)
			{
				return false;
			}
		} else if (!quiz.equals(other.quiz))
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "QuizOpdracht [maxScore=" + maxScore + "]";
	}

	public int compareTo(QuizOpdracht quizOpdracht)
	{
		return this.quiz.compareTo(quizOpdracht.getQuiz()) * 100000 + this.opdracht.compareTo(quizOpdracht.getOpdracht())
				+ (this.maxScore - quizOpdracht.getMaxScore());
	}

	@Override
	public QuizOpdracht clone()
	{
		return new QuizOpdracht(this);
	}

	/**
	 * Koppelt quiz met opdracht en bijhorende score.
	 *
	 * STATIC!!!
	 *
	 * @param quiz
	 * @param opdracht
	 * @param maxScore
	 */
	public static void koppelOpdrachtAanQuiz(Quiz quiz, Opdracht opdracht, int maxScore)
	{
		QuizOpdracht quizOpdracht = new QuizOpdracht(quiz, opdracht, maxScore);
		quiz.getStatus().voegQuizOpdrachtToe(quizOpdracht);
		opdracht.addQuizOpdracht(quizOpdracht);
	}

	/**
	 * Ontkoppelt quiz en opdracht
	 */
	public void ontKoppelOpdrachtVanQuiz()
	{
		quiz.getStatus().verwijderQuizOpdracht(this);
		opdracht.removeQuizOpdracht(this);
	}

	/**
	 * Methode om String samen te stellen om instantie naar TXT bestand weg te schrijven
	 *
	 * @return String met \t delimeters
	 */
	public String toBestand()
	{
		return this.quiz.getQuizID() + "\t" + opdracht.getOpdrachtID() + "\t" + maxScore;
	}

	public void addOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
	{
		opdrachtantwoorden.add(opdrachtAntwoord);
	}

	public void removeOpdrachtAntwoord(OpdrachtAntwoord opdrachtAntwoord)
	{
		opdrachtantwoorden.remove(opdrachtAntwoord);
	}
}
