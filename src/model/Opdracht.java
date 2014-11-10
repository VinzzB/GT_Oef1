package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Opdracht 
{
	private int opdrachtID;
	private String vraag;
	private String antwoord;
	private String antwoordHints;
	private int maxAantalPogingen;
	private Time maxAntwoordTijd = null;
	
	private List <QuizOpdracht> quizOpdrachten;
	
	@SuppressWarnings("unused")
	private OpdrachtCatalogus opdrachtCatalogus;	
	
	public Opdracht()
	{
		quizOpdrachten = new ArrayList<QuizOpdracht>();
	}
	
	public Opdracht(String vraag, String antwoord)
	{
		this();
		setVraag(vraag);
		setAntwoord(antwoord);
	}
	
	public Opdracht(String vraag, String antwoord, String antwoordHints,
			int maxAantalPogingen, Time maxAntwoordTijd) 
	{
		this();
		setVraag(vraag);
		setAntwoord(antwoord);
		setAntwoordHints(antwoordHints);
		setMaxAantalPogingen(maxAantalPogingen);
		setMaxAntwoordTijd(maxAntwoordTijd);
	}
	
	public Opdracht(int opdrachtID, String vraag, String antwoord, String antwoordHints,
			int maxAantalPogingen, Time maxAntwoordTijd)
	{
		this(vraag, antwoord, antwoordHints, maxAantalPogingen, maxAntwoordTijd);
		this.opdrachtID = opdrachtID;
	}

	public String getVraag() {
		return vraag;
	}

	public void setVraag(String vraag) {
		this.vraag = vraag;
	}

	public String getAntwoord() {
		return antwoord;
	}

	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}

	public String getAntwoordHints() {
		return antwoordHints;
	}

	public void setAntwoordHints(String antwoordHints) {
		this.antwoordHints = antwoordHints;
	}

	public int getMaxAantalPogingen() {
		return maxAantalPogingen;
	}

	public void setMaxAantalPogingen(int maxAantalPogingen) {
		this.maxAantalPogingen = maxAantalPogingen;
	}

	public Time getMaxAntwoordTijd() {
		return maxAntwoordTijd;
	}

	public void setMaxAntwoordTijd(Time maxAntwoordTijd) {
		this.maxAntwoordTijd = maxAntwoordTijd;
	}

	public boolean isJuisteAntwoord(String antwoord)
	{
		return false;
	}
	
	public  void setOpdrachtID(int opdrachtID)
	{
		this.opdrachtID = opdrachtID;
	}
	
	public int getOpdrachtID()
	{
		return opdrachtID;
	}
	
	public void setOpdrachtCatalogus(OpdrachtCatalogus opdrachtCatalogus)
	{
		this.opdrachtCatalogus = opdrachtCatalogus;
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
				+ ((maxAntwoordTijd == null) ? 0 : maxAntwoordTijd.hashCode());
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
		if (maxAntwoordTijd == null) {
			if (other.maxAntwoordTijd != null) {
				return false;
			}
		} else if (!maxAntwoordTijd.equals(other.maxAntwoordTijd)) {
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
				+ maxAantalPogingen + ", maxAntwoordTijd=" + maxAntwoordTijd 
				+ ", quizOpdrachten=" + quizOpdrachten + "]";
	}

	public int compareTo(Opdracht opdracht)
	{
		return 0;
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
		return quizOpdrachten.get(volgnr - 1);
	}
	
	public String toBestand()
	{
		return  opdrachtID + "\t" +	vraag + "\t" + antwoord + "\t" + antwoordHints + "\t" +
				maxAantalPogingen + "\t" + maxAntwoordTijd;
	}
}
