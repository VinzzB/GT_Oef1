package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Nathalie Mathieu <natmathieu@gmail.com>
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public class Opdracht implements Comparable<Opdracht>, Cloneable
{
	private int opdrachtID;
	private String vraag;
	private String antwoord;
	private int maxAantalPogingen;
	private int maxAntwoordTijdInSec;
	private boolean gekoppeldAanQuiz = false;
	private final String editErrorMessage = "De opdracht kan niet meer gewijzigd "
									+ "	worden, want ze is reeds gelinkt aan "
									+ "een Quiz";
	
	private List <QuizOpdracht> quizOpdrachten;
	private List <String> antwoordHints; //meerdere hints per vraag mogelijk
	
	private OpdrachtCatalogus opdrachtCatalogus;	
	
	public Opdracht()
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
	}
	
	public Opdracht(String vraag, String antwoord) throws Exception
	{
		this();
		setVraag(vraag);
		setAntwoord(antwoord);
	}
	
	public Opdracht(String vraag, String antwoord, String antwoordHints,
			int maxAantalPogingen, int maxAntwoordTijdInSec) throws Exception 
	{
		this();
		setVraag(vraag);
		setAntwoord(antwoord);
		this.voegAntwoordHintsToe(antwoordHints);
		setMaxAantalPogingen(maxAantalPogingen);
		setMaxAntwoordTijdInSec(maxAntwoordTijdInSec);
	}
	
	public Opdracht(int opdrachtID, String vraag, String antwoord, String antwoordHints,
			int maxAantalPogingen, int maxAntwoordTijdInSec) throws Exception
	{
		this(vraag, antwoord, antwoordHints, maxAantalPogingen, maxAntwoordTijdInSec);
		this.opdrachtID = opdrachtID;
	}

	public Opdracht(Opdracht opdracht) throws Exception
	{
		this();
		setOpdrachtID(opdracht.getOpdrachtID());
		setVraag(opdracht.getVraag());
		setAntwoord(opdracht.getAntwoord());
		setMaxAantalPogingen(opdracht.getMaxAantalPogingen());
		setMaxAntwoordTijdInSec(opdracht.getMaxAntwoordTijdInSec());
		setOpdrachtCatalogus(opdracht.getOpdrachtCatalogus());
		
		for(QuizOpdracht quizOpdracht : opdracht.getQuizOpdrachten())
		{
			this.quizOpdrachten.add(quizOpdracht);
		}

		for(String antwoordHint : opdracht.getAntwoordHints())
		{
			this.antwoordHints.add(antwoordHint);
		}
		
		
	}
	public String getVraag() {
		return vraag;
	}

	public void setVraag(String vraag) throws Exception 
	{
		//opdracht mag enkel gewijzigd worden indien nog niet gekoppeld aan een quiz
		if (!this.gekoppeldAanQuiz)
		{
			this.vraag = vraag;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public String getAntwoord() 
	{
		return antwoord;
	}

	public void setAntwoord(String antwoord) throws Exception 
	{
		if (!this.gekoppeldAanQuiz)
		{
			this.antwoord = antwoord;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public List<String> getAntwoordHints() 
	{
		return antwoordHints;
	}

	public int getMaxAantalPogingen() 
	{
		return maxAantalPogingen;
	}

	public void setMaxAantalPogingen(int maxAantalPogingen) throws Exception 
	{
		if (!this.gekoppeldAanQuiz)
		{
			this.maxAantalPogingen = maxAantalPogingen;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public int getMaxAntwoordTijdInSec() 
	{
		return maxAntwoordTijdInSec;
	}

	public void setMaxAntwoordTijdInSec(int maxAntwoordTijdInSec) throws Exception 
	{
		if (!this.gekoppeldAanQuiz)
		{
			this.maxAntwoordTijdInSec = maxAntwoordTijdInSec;
		}
		else throw new Exception(this.editErrorMessage);
	}

	public boolean isJuisteAntwoord(String antwoord)
	{
		if (this.antwoord == antwoord)
		{
			return true;
		}
		else return false;
	}
	
	public  void setOpdrachtID(int opdrachtID)
	{
		this.opdrachtID = opdrachtID;
	}
	
	public int getOpdrachtID()
	{
		return opdrachtID;
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
	
	public void setOpdrachtCatalogus(OpdrachtCatalogus opdrachtCatalogus)
	{
		this.opdrachtCatalogus = opdrachtCatalogus;
	}
	
	public OpdrachtCatalogus getOpdrachtCatalogus()
	{
		return this.opdrachtCatalogus;
	}
	
	public boolean isGekoppeldAanQuiz()
	{
		return gekoppeldAanQuiz;
	}

	public void setGekoppeldAanQuiz(boolean gekoppeldAanQuiz)
	{
		this.gekoppeldAanQuiz = gekoppeldAanQuiz;
	}
	
	public List <QuizOpdracht> getQuizOpdrachten()
	{
		return this.quizOpdrachten;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((antwoord == null) ? 0 : antwoord.hashCode());
		result = prime * result
				+ ((antwoordHints == null) ? 0 : antwoordHints.hashCode());
		result = prime * result + maxAantalPogingen;
				result = prime * result
				+ ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
		result = prime * result + ((vraag == null) ? 0 : vraag.hashCode());
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
		if (!(obj instanceof Opdracht)) {
			return false;
		}
		Opdracht other = (Opdracht) obj;
		if (antwoord == null) {
			if (other.antwoord != null) {
				return false;
			}
		} else if (!antwoord.equals(other.antwoord)) {
			return false;
		}
		if (antwoordHints == null) {
			if (other.antwoordHints != null) {
				return false;
			}
		} else if (!antwoordHints.equals(other.antwoordHints)) {
			return false;
		}
		if (maxAantalPogingen != other.maxAantalPogingen) {
			return false;
		}
		if (quizOpdrachten == null) {
			if (other.quizOpdrachten != null) {
				return false;
			}
		} else if (!quizOpdrachten.equals(other.quizOpdrachten)) {
			return false;
		}
		if (vraag == null) {
			if (other.vraag != null) {
				return false;
			}
		} else if (!vraag.equals(other.vraag)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Opdracht [vraag=" + vraag + ", antwoord=" + antwoord
				+ ", antwoordHints=" + antwoordHints + ", maxAantalPogingen="
				+ maxAantalPogingen + ", maxAntwoordTijd=" + maxAntwoordTijdInSec 
				+ ", quizOpdrachten=" + quizOpdrachten + "]";
	}

	public int compareTo(Opdracht opdracht)
	{
		return this.opdrachtID - opdracht.getOpdrachtID();
	}
	
	@Override
	public Opdracht clone() throws CloneNotSupportedException
	{
		Opdracht opdracht;
		try
		{
			opdracht = new Opdracht(this);
			return opdracht;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**(Opdracht -> QuizOpdracht: 1 to many 
	 * koppel nieuwe quizopdracht in klasse Quizopdracht aan deze opdracht)
	 */
	protected void voegQuizOpdrachtToe(QuizOpdracht quizOpdracht)
	{
		this.quizOpdrachten.add(quizOpdracht);
		this.gekoppeldAanQuiz = true;
	}
	
	protected void verwijderQuizOpdracht(QuizOpdracht quizOpdracht)
	{
		quizOpdrachten.remove(quizOpdracht);
	}
	
	
	protected void voegAntwoordHintsToe(String hints)
	{
		this.antwoordHints.add(hints);
	}
	
	protected void verwijderAntwoordHints(String hints)
	{
		this.antwoordHints.remove(hints);
	}
	
	public QuizOpdracht getOpdracht(int volgnr)
	{
		return quizOpdrachten.get(volgnr - 1);
	}
	
	public String toBestand()
	{
		return  this.opdrachtID + "\t" + this.vraag + "\t" + this.antwoord + "\t" + this.antwoordHints + "\t" +
				maxAantalPogingen + "\t" + this.maxAntwoordTijdInSec;
	}
}
