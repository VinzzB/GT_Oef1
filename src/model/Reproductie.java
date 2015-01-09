package model;

import java.util.Arrays;
import java.util.HashSet;


/**
 * @author      Nathalie Mathieu   
 */

public class Reproductie extends Opdracht {
	
/* (non-Javadoc)
	 * @see model.Opdracht#getType()
	 */
	@Override
	public OpdrachtTypen getType()
	{
		// TODO Auto-generated method stub
		return OpdrachtTypen.REPRODUCTIE;
	}

private String juistAntwoord;
private HashSet<String> trefwoorden;
private int minAantalJuisteTrefwoorden = 0;
	
	public Reproductie (String vraag, String juisteAntwoord, int maxAantalPogingen, String antwoordHints, 
			int maxAntwoordTijdinSec, OpdrachtCategorie categorie, int minAantalJuisteTrefwoorden) throws Exception
	{
		super(vraag,juisteAntwoord,antwoordHints,maxAantalPogingen,maxAntwoordTijdinSec,categorie);
		this.juistAntwoord = juisteAntwoord;		
		this.minAantalJuisteTrefwoorden = minAantalJuisteTrefwoorden;
	}
	
	public Reproductie(String[] vanTXTBestand) throws NumberFormatException, Exception
	{
		super(vanTXTBestand);
		this.minAantalJuisteTrefwoorden = Integer.parseInt(vanTXTBestand[10]);
	}
	
	public void setTrefwoorden()
	{
		this.juistAntwoord.replace(".", " ");
		this.juistAntwoord.replace(",", " ");
		this.juistAntwoord.replace(";", " ");
		String[] arrayJuistAntwoord = this.juistAntwoord.toLowerCase().split(" ");
		this.trefwoorden = new HashSet<String>(Arrays.asList(arrayJuistAntwoord));	
	}
	
	public HashSet<String> checkAantalJuisteTrefwoorden(String antwoord)
	{
		antwoord.replace(".", " ");
		antwoord.replace(",", " ");
		antwoord.replace(";", " ");
		String[] arrayAntwoord = antwoord.toLowerCase().split(" ");
		HashSet<String> intersection = new HashSet<String>(Arrays.asList(arrayAntwoord));
		intersection.retainAll(trefwoorden);//retainAll: enkel trefwoorden die in beide lijsten voorkomen worden in lijst weerhouden
		return intersection;	
	}

	@Override
	public boolean isJuisteAntwoord(String antwoord) 
	{
		int aantalTrefwoorden = this.checkAantalJuisteTrefwoorden(antwoord).size();
		if (aantalTrefwoorden >= this.minAantalJuisteTrefwoorden) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getValideerTekst() 
	{
		return "Gebruik in uw antwoord geen speciale tekens. Enkel de tekens \".\", \";\" en \",\" zijn toegelaten." ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((juistAntwoord == null) ? 0 : juistAntwoord.hashCode());
		result = prime * result + minAantalJuisteTrefwoorden;
		result = prime * result
				+ ((trefwoorden == null) ? 0 : trefwoorden.hashCode());
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
		Reproductie other = (Reproductie) obj;
		if (juistAntwoord == null) {
			if (other.juistAntwoord != null)
				return false;
		} else if (!juistAntwoord.equals(other.juistAntwoord))
			return false;
		if (minAantalJuisteTrefwoorden != other.minAantalJuisteTrefwoorden)
			return false;
		if (trefwoorden == null) {
			if (other.trefwoorden != null)
				return false;
		} else if (!trefwoorden.equals(other.trefwoorden))
			return false;
		return true;
	}

	

	

}
