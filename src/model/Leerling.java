package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Klasse Leerling: Een leerling heeft een naam en een leerjaar. De leerling moet in de leerlingcontainer zitten. Een
 * leerling kan gelinkt worden aan een quiz- deelname
 *
 * @author Silvia
 */
public class Leerling implements Comparable<Leerling>, Cloneable
{
	private String leerlingNaam;
	private int leerjaar;
	private Set<QuizDeelname> quizDeelnames;

	/**
	 *
	 * @param leerjaar
	 *            int leerjaar
	 * @throws IllegalArgumentException
	 *             indien leerjaar niet toegelaten voor de quiz
	 */
	private void setLeerjaar(int leerjaar) throws IllegalArgumentException
	{
		if (leerjaar < 0 || leerjaar > 6)
		{
			throw new IllegalArgumentException("Deze quiz is bedoeld voor leerjaar 1 tot en met 6");
		} else
		{
			this.leerjaar = leerjaar;
		}
	}

	private void setLeerlingNaam(String leerlingnaam)
	{
		this.leerlingNaam = leerlingnaam;
	}

	public String getLeerlingNaam()
	{
		return leerlingNaam;
	}

	public int getLeerjaar()
	{
		return leerjaar;
	}

	public void setQuizDeelnames(Set<QuizDeelname> quizDeelnames)
	{
		this.quizDeelnames = quizDeelnames;
	}

	public Set<QuizDeelname> getQuizDeelnames()
	{
		return quizDeelnames;
	}

	/**
	 * Constructor met 2 parameters
	 *
	 * @param leerlingNaam
	 *            naam van de leerling
	 * @param leerjaar
	 *            leerjaar van de leerling
	 */
	public Leerling(String leerlingNaam, int leerjaar)
	{
		setLeerlingNaam(leerlingNaam);
		setLeerjaar(leerjaar);
		this.quizDeelnames = new HashSet<QuizDeelname>();
	}

	/**
	 * Constructor met 3 parameters
	 *
	 * @param leerlingNaam
	 *            String naam van de leerling
	 * @param leerjaar
	 *            int leerjaar van de leerling
	 * @param quizDeelnames
	 *            Set met quizdeelnames van de leerling
	 */
	public Leerling(String leerlingNaam, int leerjaar, Set<QuizDeelname> quizDeelnames)
	{
		setLeerlingNaam(leerlingNaam);
		setLeerjaar(leerjaar);
		this.quizDeelnames = quizDeelnames;
	}

	/**
	 * @param o
	 *            Leerling object waarmee wordt vergeleken
	 * @return -1 indien kleiner dan param 0 indien gelijk aan param 1 indien groter dan param
	 *
	 * @throws NullPointerException
	 *             if the specified object is null
	 * @throws ClassCastException
	 *             if the specified object's type prevents it from being compared to this object.
	 */
	@Override
	public int compareTo(Leerling l) throws ClassCastException, NullPointerException
	{
		if (l == null)
		{
			throw new NullPointerException("Object is null");
		}

		if (this.equals(l) == true)
		{
			return 0;
		}
		if (this.hashCode() > l.hashCode())
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * @return String: "Leerling: leerlingnaam Leerjaar: leerjaar"
	 */
	@Override
	public String toString()
	{
		return "Leerling: " + this.leerlingNaam + " Leerjaar: " + this.leerjaar;
	}

	/**
	 *
	 * @return Leerling object
	 * @throws CloneNotSupportedException
	 */
	@Override
	protected Leerling clone() throws CloneNotSupportedException
	{
		return (Leerling) super.clone();
	}

	/**
	 *
	 * @return int
	 */
	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 53 * hash + Objects.hashCode(this.leerlingNaam);
		hash = 53 * hash + this.leerjaar;
		hash = 53 * hash + Objects.hashCode(this.quizDeelnames);
		return hash;
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Leerling other = (Leerling) obj;
		if (!Objects.equals(this.leerlingNaam, other.leerlingNaam))
		{
			return false;
		}
		if (this.leerjaar != other.leerjaar)
		{
			return false;
		}
		return Objects.equals(this.quizDeelnames, other.quizDeelnames);
	}

	/**
	 * Voegt een quizdeelname toe aan de Set met quizdeelnames van deze leerling
	 *
	 * @param q
	 *            Quizdeelname
	 */
	public void addQuizDeelname(QuizDeelname q)
	{
		quizDeelnames.add(q);
		q.setOwnerLeerling(this);
	}

	/**
	 * Verwijdert een quizdeelname van de Set met quizdeelnames van deze leerling
	 *
	 * @param q
	 *            Quizdeelname
	 */
	public void removeQuizDeelname(QuizDeelname q)
	{
		quizDeelnames.remove(q);
		q.setOwnerLeerling(null);
	}
}
