package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author      Nathalie   
 */

public class Meerkeuze extends Opdracht implements Valideerbaar{
	
	/* (non-Javadoc)
	 * @see model.Opdracht#getType()
	 */
	@Override
	public OpdrachtTypen getType()
	{
		// TODO Auto-generated method stub
		return OpdrachtTypen.MEERKEUZE;
	}


	private String keuzen = "";
	private ArrayList<String> keuzelijst = new ArrayList<String>();
	
	/* constructor*/
	
	public Meerkeuze(){}
	
	public Meerkeuze (String vraag, String juisteAntwoord,String antwoordHints, 
			int maxAantalPogingen, int maxAntwoordTijdinSec, 
			OpdrachtCategorie categorie, String keuzen) throws Exception
	{
		super(vraag,juisteAntwoord,antwoordHints,maxAantalPogingen,
				maxAntwoordTijdinSec,categorie);
		this.keuzen = keuzen; 
	}
	
	/* getters and setters*/
	
	public String getkeuze()
	{
		// in willekeurige volgorde
		this.keuzelijst = super.splitTekst(this.keuzen);
		Collections.shuffle(this.keuzelijst);
		this.keuzen = this.setListToString(this.keuzelijst);
		return this.keuzen;
	}
	
	public void setKeuze(String keuze)
	{
		//enkel als nog geen test afgelegd***************
		this.keuzen = keuze;
	}
	
	/* methods */
	
	public void addKeuze(String keuze)
	{
		//enkel als nog geen test afgelegd***************
		this.keuzelijst = super.splitTekst(this.keuzen);
		this.keuzelijst.add(keuze);
		this.keuzen = this.setListToString(this.keuzelijst);	
	} 
	
	public void removeKeuze(String keuze)
	{
		//enkel als nog geen test afgelegd***************
		this.keuzelijst = super.splitTekst(this.keuzen);
		this.keuzelijst.remove(keuze);
		this.keuzen = this.setListToString(this.keuzelijst);
	}
	
	public String setListToString(ArrayList<String> list)
	{
		String string = "";
		for (String keuze:list)
		{
			int i = 0;
			if (i == 0)
			{
				string += keuze;
				i++;
			}		
			else
			{
				string += ";" + keuze;
				i++;
			}
		}
		return string;
	}	
	
	/* override methods*/
	
	@Override
	protected boolean isJuisteAntwoord(String antwoord)
	{
		if (super.getJuisteAntwoord() == antwoord)
		{
			return true;
		}
		return false;
	}

	//Hoe kan gecheckt worden of antwoord valide is (m.a.w of er een juist nummer gekozen werd,
	//als er een String wordt doorgegeven en niet de Integer?)
	@Override
	public boolean isValide(String antwoord)
	{		                                          
		/*if (super.getJuisteAntwoord() == antwoord)
		{
			return true;
		}*/
		return false;
	}
	
	@Override
	public String getValideerTekst()
	{
		return "Het antwoord moet een nummer zijn dat overeenkomt met één van de nummers uit de mogelijke oplossingen.";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((keuzelijst == null) ? 0 : keuzelijst.hashCode());
		result = prime * result + ((keuzen == null) ? 0 : keuzen.hashCode());
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
		Meerkeuze other = (Meerkeuze) obj;
		if (keuzelijst == null) {
			if (other.keuzelijst != null)
				return false;
		} else if (!keuzelijst.equals(other.keuzelijst))
			return false;
		if (keuzen == null) {
			if (other.keuzen != null)
				return false;
		} else if (!keuzen.equals(other.keuzen))
			return false;
		return true;
	}

	
	
}
