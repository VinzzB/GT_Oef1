package model;

import java.util.Arrays;
import persistency.framework.DbOpdrachtMeerkeuze;

/**
 * @author      Nathalie   
 * @Revision 	Vincent (10/01/2015)
 */

public class Meerkeuze extends Opdracht implements Valideerbaar{
	
	/* (non-Javadoc)
	 * @see model.Opdracht#getType()
	 */
	@Override
	public OpdrachtTypen getType()
	{ return OpdrachtTypen.MEERKEUZE; }

	private String[] keuzen;

	/* constructor*/
	
	public Meerkeuze (String vraag, String juisteAntwoord,String antwoordHints, 
			int maxAantalPogingen, int maxAntwoordTijdinSec, 
			OpdrachtCategorie categorie, String[] keuzen) throws Exception
	{
		super(vraag,juisteAntwoord,antwoordHints,maxAantalPogingen,
				maxAntwoordTijdinSec,categorie);
		this.keuzen = keuzen; 
	}
	
	public Meerkeuze(DbOpdrachtMeerkeuze dbRow) throws NumberFormatException, Exception
	{
		super(dbRow);
		this.keuzen = dbRow.getKeuzen(); // vanTXTBestand[10].split(";");
	}
	
	public Meerkeuze(Meerkeuze fromObj) throws Exception
	{
		super(fromObj);
		this.keuzen = fromObj.getKeuzen().clone();		
	}
	
	/* getters and setters*/
	
	public String[] getKeuzen() {
		return keuzen;
	}
	public void setKeuzen(String[] keuzen)
	{ this.keuzen = keuzen; }
	
		
	/* override methods*/
	
	@Override
	public boolean isJuisteAntwoord(String antwoord)
	{
		return getJuisteAntwoord().equals(antwoord);
	}

	//Hoe kan gecheckt worden of antwoord valide is (m.a.w of er een juist nummer gekozen werd,
	//als er een String wordt doorgegeven en niet de Integer?)
	@Override
	public boolean isValide(String antwoord)
	{		                                          
		try		
		{
			int value = Integer.parseInt(antwoord); 
			return value > 0 && value <= keuzen.length;
		}
		catch(Exception e)
		{ return false;}
	}
		
	@Override
	public String getValideerTekst()
	{
		return "Geef een nummer tussen 1 t/m " + keuzen.length;
	}

	@Override
	public String getVraag()
	{
		String vraag = super.getVraag();
		for (int i = 0; i < keuzen.length; i++)
		{ vraag += " - " + (i+1) + ": " + keuzen[i] + "\r\n"; } 
		return vraag;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(keuzen);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Meerkeuze))
			return false;
		Meerkeuze other = (Meerkeuze) obj;
		if (!Arrays.equals(keuzen, other.keuzen))
			return false;
		return true;
	}

	/***
	 * Returns null wanneer het clonen faalt.
	 */
	@Override
	public Opdracht clone()
	{
		// TODO Auto-generated method stub
		try
		{ return new Meerkeuze(this); }
		catch (Exception e)
		{ return null; }
	}


	
	

	
	
}
