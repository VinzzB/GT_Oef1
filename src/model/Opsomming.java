package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * @author      Nathalie   
 */

public class Opsomming extends Opdracht implements Valideerbaar {

	String[] arrayAntwoord;
	
	public Opsomming(String vraag, String juisteAntwoord, int maxAantalPogingen, String antwoordHints, 
			int maxAntwoordTijdinSec, OpdrachtCategorie categorie) throws Exception
	{
		super(vraag,juisteAntwoord,antwoordHints,maxAantalPogingen,maxAntwoordTijdinSec,categorie);
		this.arrayAntwoord = juisteAntwoord.split(";");
	}		
	

	/** Override */
	
	@Override
	protected boolean isJuisteAntwoord(String antwoord)
	{
		//(linkedlist -> equals method houdt geen rekening met volgorde)
		String[] antwoorden = antwoord.split(";");
		if (this.arrayAntwoord.equals(antwoorden)) 
		{
			return true;
		}
		else return false;
	}
	
	/** Kijk na of format gegeven antwoord valide is. De antwoorden moeten gescheiden zijn door ;*/
	@Override
	public boolean isValide(String antwoord)
	{		
		if (antwoord != null) 
		{
			String[] antwoorden = antwoord.split(";");
			if (antwoorden.length > 1) 
			{
				for(String s : antwoorden)
				{ 
					if (s.contains(",") || s.contains(".") || s.contains("/"))
					{
						 return false;
					}					
				}				
			}
		}	
		return true;		
	}

	@Override
	public String getValideerTekst() 
	{		
		return "Typ je antwoorden achter elkaar gescheiden door \";\"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((arrayAntwoord == null) ? 0 : arrayAntwoord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opsomming other = (Opsomming) obj;
		if (arrayAntwoord == null) {
			if (other.arrayAntwoord != null)
				return false;
		} else if (!arrayAntwoord.equals(other.arrayAntwoord))
			return false;
		return true;
	}
	
	

}
