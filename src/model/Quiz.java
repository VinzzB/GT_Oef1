package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz implements Comparable<Quiz>, Cloneable
{
	private String onderwerp;
	private int leerjaar;
	private boolean isTest;
	private boolean isUniek;
	private String status;
	
	private List <QuizOpdracht> quizOpdrachten;
	
	public Quiz()
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
	}
	
	public Quiz (String onderwerp)
	{
		this();
		setOnderwerp(onderwerp);
	}


	public Quiz(String onderwerp, int leerjaar, boolean isTest,
			boolean isUniek, String status) 
	{
		this();
		setOnderwerp(onderwerp);
		setLeerjaar(leerjaar);
		setIsTest(isTest);
		setIsUniek(isUniek);
		setStatus(status);
		
	}

	public Quiz(Quiz quiz)
	{
		this();
		setOnderwerp(quiz.onderwerp);
		setLeerjaar(quiz.leerjaar);
		setIsTest(quiz.isTest);
		setIsUniek(quiz.isUniek);
		setStatus(quiz.status);
		
		for (QuizOpdracht quizOpdracht : quiz.quizOpdrachten)
		{
			this.quizOpdrachten.add(quizOpdracht);
		}
	}

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


	public void setLeerjaar(int leerjaar) {
		this.leerjaar = leerjaar;
	}


	public boolean isTest() {
		return isTest;
	}


	public void setIsTest(boolean isTest) 
	{
		this.isTest = isTest;
	}


	public boolean isUniek() {
		return isUniek;
	}


	public void setIsUniek(boolean isUniek) 
	{
		this.isUniek = isUniek;
	}


	public String getStatus() 
	{
		return status;
	}


	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniek ? 1231 : 1237);
		result = prime * result + leerjaar;
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
		result = prime * result
				+ ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Quiz)) {
			return false;
		}
		Quiz other = (Quiz) obj;
		if (isTest != other.isTest) {
			return false;
		}
		if (isUniek != other.isUniek) {
			return false;
		}
		if (leerjaar != other.leerjaar) {
			return false;
		}
		if (onderwerp == null) {
			if (other.onderwerp != null) {
				return false;
			}
		} else if (!onderwerp.equals(other.onderwerp)) {
			return false;
		}
		if (quizOpdrachten == null) {
			if (other.quizOpdrachten != null) {
				return false;
			}
		} else if (!quizOpdrachten.equals(other.quizOpdrachten)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
	
	public int compareTo(Quiz quiz)
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Quiz [onderwerp=" + onderwerp + ", leerjaar=" + leerjaar
				+ ", isTest=" + isTest + ", isUniek=" + isUniek + ", status="
				+ status + ", quizOpdrachten=" + quizOpdrachten + "]";
	}
	
	@Override
	public Quiz clone() throws CloneNotSupportedException
	{
		return new Quiz(this);
	}

	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht)
	{
		quizOpdrachten.add(quizOpdracht);
	}
	
	protected void verwijderQuizOpdracht(QuizOpdracht quizOpdracht)
	{
		quizOpdrachten.remove(quizOpdracht);
	}
	
	public ArrayList <Opdracht> getOpdrachten()
	{
		ArrayList <Opdracht> opdrachten = new ArrayList <Opdracht>();
		
		for (QuizOpdracht quizOpdracht : quizOpdrachten)
		{
			opdrachten.add(quizOpdracht.getOpdracht());
		}
		return opdrachten;
	}
	
	public QuizOpdracht getOpdracht(int volgnr)
	{
		return quizOpdrachten.get(volgnr-1);
	}
}
