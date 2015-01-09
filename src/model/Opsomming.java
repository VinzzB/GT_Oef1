package model;

import java.util.Arrays;

/**
 * @author      Nathalie   
 */

public class Opsomming extends Opdracht implements Valideerbaar {

	/* (non-Javadoc)
	 * @see model.Opdracht#getType()
	 */
	@Override
	public OpdrachtTypen getType()
	{
		return OpdrachtTypen.OPSOMMING;
	}

	private boolean inJuisteVolgorde;
	
	public Opsomming(String vraag, String juisteAntwoord, int maxAantalPogingen, String antwoordHints, 
			int maxAntwoordTijdinSec, OpdrachtCategorie categorie) throws Exception
	{
		super(vraag,juisteAntwoord,antwoordHints,maxAantalPogingen,maxAntwoordTijdinSec,categorie);
		//this.arrayAntwoord = juisteAntwoord.split(";");
	}		
	

	/** Override */
	
	@Override
	protected boolean isJuisteAntwoord(String antwoord)
	{
		//split de antwoorden
		String[] antwArray = antwoord.split(";");
		String[] juistAntwArray = getJuisteAntwoord().split(";");
		//Antwoorden moeten niet in dezelfde volgorde gegeven worden? -> Sorteer beide lijsten.		
		if (!inJuisteVolgorde)
		{
			Arrays.sort(antwArray);
			Arrays.sort(juistAntwArray);
		}		
		// Controleer de juiste antwoorden tegenover de gegeven antwoorden. 
		//Arrays.equals is hoofdlettergevoelig en de aantallen moeten overeenkomen.
		return Arrays.equals(antwArray, juistAntwArray);		
	}
	
	/** Kijk na of format gegeven antwoord valide is. De antwoorden moeten gescheiden zijn door ;*/
	@Override
	public boolean isValide(String antwoord)
	{		
		try
		{
			String[] answer = antwoord.split(";");		
			return answer.length > 0;
		}
		catch(Exception e)
		{ return false; }		
	}

	@Override
	public String getValideerTekst() 
	{		
		return "Typ je antwoorden " 
				+ (inJuisteVolgorde ? "in de juiste volgorde" : "achter elkaar") 
				+ " gescheiden door een ';' (zonder aanhalingstekens)";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (inJuisteVolgorde ? 1231 : 1237);
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
		if (!(obj instanceof Opsomming))
			return false;
		Opsomming other = (Opsomming) obj;
		if (inJuisteVolgorde != other.inJuisteVolgorde)
			return false;
		return true;
	}
	

}
