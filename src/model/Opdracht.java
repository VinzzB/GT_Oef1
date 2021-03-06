package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.opdrachten.OpdrachtCategorie;
import model.opdrachten.OpdrachtTypen;
import persistency.framework.DbOpdrachtBase;
import utils.date.gregorian.Datum;

/**
 * @author      Nathalie Mathieu
 * @author      Natalia Dyubankova <fornnd@gmail.com>
 * @version     1.0                 
 * @since       2014-11-12  
 */
public abstract class Opdracht implements Comparable<Opdracht>, Cloneable
{
	private int opdrachtID = 0; //is niet nodig ... Zou uit Catalogi gehaald kunnen worden. ( getIndex() method)
	private String vraag;
	private String juisteAntwoord;
	private int maxAantalPogingen = 1;
	private int maxAntwoordTijdInSec = 0;
//	private final String editErrorMessage = "De opdracht kan niet meer gewijzigd "
//									+ "	worden, want ze is reeds gelinkt aan "
//									+ "een Quiz";
	private OpdrachtCategorie categorie;
	private List <QuizOpdracht> quizOpdrachten = new ArrayList<QuizOpdracht>();
	private String antwoordHint; 
	private Leraar auteur;
	private Datum registratie;
	
	public abstract OpdrachtTypen getType();
	
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
		this.antwoordHint = antwoordHints;
		this.maxAantalPogingen = maxAantalPogingen;
		this.maxAntwoordTijdInSec = maxAntwoordTijdInSec;
		this.categorie = categorie;
	}

	public Opdracht(Opdracht opdracht) throws Exception
	{	
		setVraag(opdracht.getVraag());
		setJuisteAntwoord(opdracht.getJuisteAntwoord());
		setMaxAantalPogingen(opdracht.getMaxAantalPogingen());
		setMaxAntwoordTijdinSec(opdracht.getMaxAntwoordTijdInSec());		
		for(QuizOpdracht quizOpdracht : opdracht.getQuizOpdrachten())
		{
			this.quizOpdrachten.add(quizOpdracht.clone());
		}
		setAntwoordHint(opdracht.getAntwoordHint());
	}
	
		/**
	* Constructor for Opdracht instance from TXTdatabase
	* String array fields:
	* 0: opdrachtID
	* 1: Vraag
	* 2: Antwoord
	* 3: Categorie
	* 4: antwoordHint
	* 5: maxAantalPogingen
	* 6: maxAntwoordTijdInSec
	* 7: Creatie datum
	* 8: Auteur
	* 9: OpdrachtType
	* @throws Exception
	* @throws NumberFormatException
	*/
	public Opdracht(DbOpdrachtBase dbRow) throws NumberFormatException, Exception
	{
		opdrachtID = dbRow.getId();// Integer.parseInt(vanTXTBestand[0]);
		vraag = dbRow.getVraag(); // vanTXTBestand[1];
		juisteAntwoord = dbRow.getJuisteAntwoord(); // vanTXTBestand[2];
		categorie = dbRow.getCategorie(); // OpdrachtCategorie.valueOf(vanTXTBestand[3]);
		antwoordHint = dbRow.getHint(); // vanTXTBestand[4];
		maxAantalPogingen = dbRow.getMaxAantalPogingen(); // Integer.parseInt(vanTXTBestand[5]);
		maxAntwoordTijdInSec = dbRow.getMaxAntwoordTijd(); // Integer.parseInt(vanTXTBestand[6]);
		registratie = dbRow.getDatumRegistratie(); //  new Datum(vanTXTBestand[7]);
		auteur = dbRow.getAuteur(); // Leraar.valueOf(vanTXTBestand[8]);
		// OpdrachtTypen.valueOf(vanTXTBestand[9]));
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
	
	public Leraar getAuteur()
	{
		return auteur;
	}
	
	public void setAuteur(Leraar auteur)
	{
		this.auteur = auteur;
	}
	
	public void setAuteur(String auteur)
	{ 
		this.auteur = Leraar.valueOf(auteur);
	}	
	
	public Datum getRegistratie()
	{
		return this.registratie;
	}
	
	public void setRegistratie(Datum registratie)
	{
		this.registratie = registratie;
	}
	
	public String getVraag()
	{
		return this.vraag;
	}
			
	public void setVraag(String vraag) 
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

	public void setMaxAantalPogingen(int maxAantalPogingen) throws Exception
	{
			this.maxAantalPogingen = maxAantalPogingen;
	}

	public String getAntwoordHint() 
	{
		return this.antwoordHint;
	}
	
	public void setAntwoordHint(String hint)
	{
		this.antwoordHint = hint;
	}

	public int getMaxAntwoordTijdInSec() 		
	{
		return this.maxAntwoordTijdInSec;
	}

	public void setMaxAntwoordTijdinSec(int maxAntwoordTijdinSec) throws Exception
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

	public boolean isJuisteAntwoord(String antwoord)
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
		return  this.opdrachtID + "\t" + this.vraag + "\t" + this.juisteAntwoord + "\t" + this.antwoordHint + "\t" +
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
				+ ((antwoordHint == null) ? 0 : antwoordHint.hashCode());
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
		if (antwoordHint == null) {
			if (other.antwoordHint != null) {
				return false;
			}
		} else if (!antwoordHint.equals(other.antwoordHint)) {
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
	public abstract Opdracht clone() throws CloneNotSupportedException;	
	
	//temp disabled -> Class is now abstract
//	@Override
//	public Opdracht clone() throws CloneNotSupportedException
//	{
//		Opdracht opdracht;
//		try
//		{
//			opdracht = new this.getClass().. Opdracht(this);
//			return opdracht;
//		} 
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}

	public int compareTo(Opdracht opdracht)
	{
		return this.opdrachtID - opdracht.getOpdrachtID();
	}
	public int compareVraag(Opdracht opdracht)
	{
		return this.vraag.compareTo(opdracht.getVraag());
	}
	public int compareCategorie(Opdracht opdracht)
	{
		return this.categorie.compareTo(opdracht.getCategorie());
	}

	public String getAntwoord()
	{
		// TODO Auto-generated method stub
		return juisteAntwoord;
	}
	

}
