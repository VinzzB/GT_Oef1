package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author      Nathalie Mathieu
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public class Opdracht implements Comparable<Opdracht>, Cloneable
{
	private int opdrachtID = 0;
	private String vraag;
	private String juisteAntwoord;
	private int maxAantalPogingen = 1;
	private int maxAntwoordTijdInSec = 0;
	private final String editErrorMessage = "De opdracht kan niet meer gewijzigd "
									+ "	worden, want ze is reeds gelinkt aan "
									+ "een Quiz";
	private OpdrachtCategorie categorie;
	private List <QuizOpdracht> quizOpdrachten;
	private List <String> antwoordHints; //meerdere hints per vraag mogelijk
	
	/* Constructor*/
	
	public Opdracht() {}
	
	public Opdracht(String vraag, String juistAntwoord) throws Exception
	{
		this.vraag = vraag;
		this.juisteAntwoord = juistAntwoord;
	}
	
	public Opdracht(String vraag, String juistAntwoord, String antwoordHints,
			int maxAantalPogingen, int maxAntwoordTijdInSec, 
			OpdrachtCategorie categorie) throws Exception 
	{
		this.vraag = vraag;
		this.juisteAntwoord = juistAntwoord;
		this.addAntwoordHints(antwoordHints);
		this.maxAantalPogingen = maxAantalPogingen;
		this.maxAntwoordTijdInSec = maxAntwoordTijdInSec;
		this.categorie = categorie;
	}

	public Opdracht(Opdracht opdracht) throws Exception
	{	
		setVraag(opdracht.getVraag());
		setJuisteAntwoord(opdracht.getJuisteAntwoord());
		setMaxAantalPogingen(opdracht.getMaxAantalPogingen());
		setMaxAntwoordTijdinSec(opdracht.getMaxAntwoordTijdinSec());		
		for(QuizOpdracht quizOpdracht : opdracht.getQuizOpdrachten())
		{
			this.quizOpdrachten.add(quizOpdracht);
		}
		for(String antwoordHint : opdracht.getAntwoordHints())
		{
			this.antwoordHints.add(antwoordHint);
		}
	}
	
	/* getters en setters */
	
	public int getOpdrachtID()
	{
		return this.opdrachtID;
	}
	
	public void setOpdrachtID(int id) 
	{
		this.opdrachtID = id;		
	}
	
	public String getVraag()
	{
		return this.vraag;
	}
			
	private void setVraag(String vraag) 
	{
			this.vraag = vraag;
	}
	
	public OpdrachtCategorie getCategorie()
	{
		return this.categorie;
	}
	
	public void setCategorie(OpdrachtCategorie categorie)
	{
		this.categorie = categorie;
	}
	
	public String getJuisteAntwoord()
	{
		return juisteAntwoord;
	}

	private void setJuisteAntwoord(String juisteAntwoord) throws Exception
	{
			this.juisteAntwoord = juisteAntwoord;
	}

	public int getMaxAantalPogingen()
	{
		return maxAantalPogingen;
	}

	private void setMaxAantalPogingen(int maxAantalPogingen) throws Exception
	{
			this.maxAantalPogingen = maxAantalPogingen;
	}

	public List<String> getAntwoordHints() 
	{
		return antwoordHints;
	}

	public int getMaxAntwoordTijdinSec() 		
	{
		return this.maxAntwoordTijdInSec;
	}

	private void setMaxAntwoordTijdinSec(int maxAntwoordTijdinSec) throws Exception
	{
		this.maxAntwoordTijdInSec = maxAntwoordTijdinSec;
	}

	public List<QuizOpdracht> getQuizOpdrachten()
	{
		return quizOpdrachten;
	}
	
	/* methods */
	
	public void editOpdracht(String vraag, String juisteAntwoord, 
			int maxAantalPogingen, int maxAntwoordTijdinSec) throws Exception
	{
		//if//eerst checken of opdracht mag gewijzigd worden (indien nog geen test werd afgelegd) anders exception
		{
			this.setVraag(vraag);		
			this.setJuisteAntwoord(juisteAntwoord);
			this.setMaxAantalPogingen(maxAantalPogingen);
			this.setMaxAntwoordTijdinSec(maxAntwoordTijdinSec);		
		}
		//else throw exception
	}

	protected void addQuizOpdracht(QuizOpdracht quizOpdracht)
	{
		this.quizOpdrachten.add(quizOpdracht);
		/*(Opdracht -> QuizOpdracht: 1 to many 
		 * koppel nieuwe quizopdracht in klasse Quizopdracht aan deze opdracht)*/
	}
	
	protected void removeQuizOpdracht(QuizOpdracht quizopdracht)
	{
		//mag enkel verwijderd worden indien nog niet gekoppeld aan test, anders gaat band verloren.
		this.quizOpdrachten.remove(quizopdracht);
	}
		
	protected void addAntwoordHints(String hints)
	{
		//mag wel gewijzigd worden indien reeds gekoppeld aan test
		this.antwoordHints.add(hints);
	}
	
	protected void removeAntwoordHints(String hints)
	{
		//mag wel verwijderd worden indien reeds gekoppeld aan test
		this.antwoordHints.remove(hints);
	}

	protected boolean isJuisteAntwoord(String antwoord)
	{
		if (this.juisteAntwoord == antwoord)
		{
			return true;
		}
		else return false;
	}	
	
	public QuizOpdracht getOpdracht(int volgnr)
	{
		return quizOpdrachten.get(volgnr - 1);
	}
	
	public String toBestand()
	{
		return  this.opdrachtID + "\t" + this.vraag + "\t" + this.juisteAntwoord + "\t" + this.antwoordHints + "\t" +
				maxAantalPogingen + "\t" + this.maxAntwoordTijdInSec;
	}
	
	public ArrayList<String> splitTekst(String tekst)
	{
		String[] array = tekst.split(";");
		ArrayList<String> list = 
				new ArrayList<String>(Arrays.asList(array));
		return list;
	}
	
	/* override methods */

	@Override
	public String toString()
	{
		return vraag+"("+juisteAntwoord+")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((juisteAntwoord == null) ? 0 : juisteAntwoord.hashCode());
		result = prime * result
				+ ((antwoordHints == null) ? 0 : antwoordHints.hashCode());
		result = prime * result + maxAantalPogingen;
				result = prime * result
				+ ((quizOpdrachten == null) ? 0 : quizOpdrachten.hashCode());
		result = prime * result + ((vraag == null) ? 0 : vraag.hashCode());
		return result;
	}

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
		if (juisteAntwoord == null) {
			if (other.juisteAntwoord != null) {
				return false;
			}
		} else if (!juisteAntwoord.equals(other.juisteAntwoord)) {
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

	@Override
	public int compareTo(Opdracht opdracht)
	{
		int o1 = this.opdrachtID;
		int o2 = opdracht.opdrachtID;
		if (o1 < o2) 
		{
			return -1;
		}
		else if (o1 > o2)
		{
			return 1;
		}
		else
		{
			return this.vraag.compareTo(opdracht.vraag);
		}
	}
	

}
